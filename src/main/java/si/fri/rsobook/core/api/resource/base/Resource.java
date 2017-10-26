package si.fri.rsobook.core.api.resource.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import si.fri.rsobook.core.api.ApiCore;
import si.fri.rsobook.core.api.client.authorization.provider.base.ApiAuthProvider;
import si.fri.rsobook.core.api.data.RequestException;
import si.fri.rsobook.core.api.data.request.base.ApiBaseRequest;
import si.fri.rsobook.core.api.data.response.EntityResponse;
import si.fri.rsobook.core.api.data.response.PagingResponse;
import si.fri.rsobook.core.api.exception.ApiException;
import si.fri.rsobook.core.entities.impl.BaseEntityImpl;

import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public abstract class Resource<T extends BaseEntityImpl> {

    private final ObjectMapper mapper = new ObjectMapper();

    protected final Class<T> type;
    protected JavaType typeReference;
    protected JavaType listTypeReference;

    protected ApiCore core;
    protected String endpointUrl;

    protected boolean defaultContentHeader = false;

    public Resource(ApiCore core, Class<T> type) {
        this.type = type;
        this.core = core;
        this.endpointUrl = core.getConfiguration().hostName + "/" + type.getSimpleName();

        typeReference = mapper.getTypeFactory().constructType(type);
        listTypeReference = mapper.getTypeFactory().constructCollectionType(List.class, type);
    }

    public T getEmptyItem() throws ApiException {
        try {
            return type.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiException("Could not create new instance");
        }
    }

    private String buildContent(HttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
        StringBuffer sb = new StringBuffer();

        String inputLine;
        while ((inputLine = br.readLine()) != null) {
            sb.append(inputLine + "\r\n");
        }

        return sb.toString();
    }

    private <T> void getEntityResponseHeaders(HttpResponse response, EntityResponse<T> entityData) {
        Header[] locationHeader = response.getHeaders("Location");
        if(locationHeader.length == 1) {
            entityData.setLocationHeader(locationHeader[0].getValue());
        }

        Header[] eTagHeader = response.getHeaders("ETag");
        if(eTagHeader.length == 1) {
            entityData.seteTagHeader(eTagHeader[0].getValue());
        }
    }

    private <T> void getPagingResponseHeaders(HttpResponse response, PagingResponse<T> pagingData){
        Header[] xCountHeader = response.getHeaders("X-Count");
        if(xCountHeader.length == 1){
            int count = Integer.parseInt(xCountHeader[0].getValue());
            pagingData.setCount(count);
        }
    }

    protected <P> EntityResponse<P> buildVcgEntityByClass(HttpResponse response, Class<P> classType){
        return buildVcgEntityByClass(response, mapper.getTypeFactory().constructType(classType));
    }

    protected <P> EntityResponse<P> buildVcgEntityByClass(HttpResponse response, JavaType classType){
        Response.Status status = Response.Status.fromStatusCode(response.getStatusLine().getStatusCode());
        EntityResponse<P> entityData = new EntityResponse<P>(status);

        getEntityResponseHeaders(response, entityData);

        try {
            String content = buildContent(response);

            switch (status){
                case CREATED:
                case OK:
                    P item = mapper.readValue(content, classType);
                    entityData.setItem(item);
                    break;
                default:
                    RequestException exception = mapper.readValue(content, RequestException.class);
                    entityData.setIsApiException(exception);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entityData;
    }

    protected PagingResponse<T> buildVcgPaging(HttpResponse response) throws ApiException {
        Response.Status status = Response.Status.fromStatusCode(response.getStatusLine().getStatusCode());
        PagingResponse<T> pagingData = new PagingResponse<T>(status);

        try {
            String content = buildContent(response);

            switch (status){
                case OK:
                    getPagingResponseHeaders(response, pagingData);
                    ArrayList<T> items = mapper.readValue(content, listTypeReference);
                    pagingData.setItems(items);
                    break;
                default:
                    RequestException exception = mapper.readValue(content, RequestException.class);
                    pagingData.setIsApiException(exception);
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pagingData;
    }

    protected EntityResponse<T> buildVcgEntity(HttpResponse response) throws ApiException {
        Response.Status status = Response.Status.fromStatusCode(response.getStatusLine().getStatusCode());
        EntityResponse<T> entityData = new EntityResponse<T>(status);

        getEntityResponseHeaders(response, entityData);

        try {
            String content = buildContent(response);

            switch (status){
                case CREATED:
                case OK:
                    if(content.length() > 0){
                        JavaType typeReference = mapper.getTypeFactory().constructType(getType());
                        T item = mapper.readValue(content, typeReference);
                        entityData.setItem(item);
                    }
                case NO_CONTENT:
                    break;
                default:
                    RequestException exception = mapper.readValue(content, RequestException.class);
                    entityData.setIsApiException(exception);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entityData;
    }

    protected void setHeaders(HttpRequest request, ApiBaseRequest apiRequest){
        request.addHeader("Accept", "application/json");
        request.addHeader("Content-Type", "application/json; charset=utf-8");

        ApiAuthProvider provider = core.getApiAuthProvider();
        if(provider != null){
            request.addHeader("Authorization", "Bearer " + provider.getAuthorizationToken());
        }

        if(apiRequest.isxContentHeader()){
            request.addHeader("X-Content", "true");
        }

        if(apiRequest.geteTagHeader() != null){
            request.addHeader("ETag", apiRequest.geteTagHeader());
        }
    }

    protected boolean isContentHeaderAdded(){
        return defaultContentHeader || core.getDefaultCoreContentHeader();
    }

    protected StringEntity buildJsonContent(T item) throws ApiException {
        try {
            return new StringEntity(mapper.writeValueAsString(item), "UTF-8");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        throw new ApiException("Could not write object as JSON");
    }

    protected StringEntity buildJsonContent(List<T> item) throws ApiException {
        try {
            return new StringEntity(mapper.writeValueAsString(item), "UTF-8");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        throw new ApiException("Could not write object as JSON");
    }

    public ApiException buildNotImplemented(){
        return new ApiException("Not implemented.");
    }


    public Class<T> getType() {
        return type;
    }

    public ApiCore getCore() {
        return core;
    }

    public void setCore(ApiCore core) {
        this.core = core;
    }

    public String getEndpointUrl() {
        return endpointUrl;
    }

    public void setEndpointUrl(String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }

    public boolean isDefaultContentHeader() {
        return defaultContentHeader;
    }

    public void setDefaultContentHeader(boolean defaultContentHeader) {
        this.defaultContentHeader = defaultContentHeader;
    }

    protected void checkEntity(T entity) throws ApiException{
        checkEntity(entity, false);
    }

    protected void checkEntity(T entity, boolean idNullable) throws ApiException {
        if(entity == null){
            throw new ApiException("Entity can not be null");
        } else {
            if(!idNullable && entity.getId() == null) {
                throw new ApiException("Entity id can not be null");
            }
        }
    }
}
