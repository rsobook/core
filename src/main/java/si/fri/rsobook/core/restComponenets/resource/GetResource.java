package si.fri.rsobook.core.restComponenets.resource;

import com.github.tfaga.lynx.beans.QueryParameters;
import si.fri.rsobook.core.database.dto.Paging;
import si.fri.rsobook.core.database.exceptions.BusinessLogicTransactionException;
import si.fri.rsobook.core.database.manager.AuthorizationManager;
import si.fri.rsobook.core.database.manager.ValidationManager;
import si.fri.rsobook.core.entities.impl.BaseEntityImpl;
import si.fri.rsobook.core.restComponenets.enums.CacheControlType;
import si.fri.rsobook.core.restComponenets.managers.ETagValidationManager;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

public abstract class GetResource<I, T extends BaseEntityImpl<I, T>> extends BaseResource {

    protected int defaultMaxLimit = 50;

    protected CacheControlType listCacheControl = CacheControlType.NONE;
    protected int listCacheControlMaxAge = 60;

    protected CacheControlType getCacheControl = CacheControlType.NONE;
    protected int getCacheControlMaxAge = 180;


    protected Class<T> type;

    protected AuthorizationManager<T> authorizationManager = null;
    protected ValidationManager<T> validationManager = null;

    public GetResource(Class<T> type) {
        this.type = type;
    }

    @PostConstruct
    private void init(){
        validationManager = initValidationManager();
        authorizationManager = initAuthorizationManager();
    }

    @GET
    public Response getList() throws BusinessLogicTransactionException {
        QueryParameters param = QueryParameters.query(uriInfo.getRequestUri().getQuery())
                .maxLimit(defaultMaxLimit).defaultLimit(defaultMaxLimit).defaultOffset(0).build();

        Paging<T> paging = getDatabaseService().getList(type, param, authorizationManager);

        Response.ResponseBuilder rb = buildResponse(paging);

        if(!listCacheControl.equals(CacheControlType.NONE)){
            rb.cacheControl(buildCacheControl(listCacheControlMaxAge, listCacheControl));
        }

        return rb.build();
    }

    protected abstract I parseId(String string);

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") String rawId) throws BusinessLogicTransactionException {

        T dbEntity = getDatabaseService().get(type, parseId(rawId), authorizationManager);

        EntityTag tag = dbEntity.getEntityTag();

        Response.ResponseBuilder rb = request.evaluatePreconditions(tag);
        if(rb == null){
            rb = buildResponse(dbEntity, true, false);
            rb.tag(tag);
        }

        if(!getCacheControl.equals(CacheControlType.NONE)){
            rb.cacheControl(buildCacheControl(getCacheControlMaxAge, getCacheControl));
        }
        return rb.build();
    }


    protected CacheControl buildCacheControl(int maxAge, CacheControlType cacheControlType){
        CacheControl cc = new CacheControl();
        cc.setMaxAge(maxAge);

        switch (cacheControlType){
            case PUBLIC:
                cc.setPrivate(false);
                break;
            case PRIVATE:
                cc.setPrivate(true);
                break;

        }
        return cc;
    }

    protected Response.ResponseBuilder buildResponse(T dbEntity) {
        return buildResponse(dbEntity, false, false, null);
    }

    protected Response.ResponseBuilder buildResponse(T dbEntity, Boolean isContentReturned) {
        return buildResponse(dbEntity, isContentReturned, false, null);
    }

    protected Response.ResponseBuilder buildResponse(T dbEntity, Boolean isContentReturned, boolean locationHeader) {
        return buildResponse(dbEntity, isContentReturned, locationHeader, null);
    }

    protected Response.ResponseBuilder buildResponse(T dbEntity, Boolean isContentReturned, boolean locationHeader, Response.Status status) {

        Response.ResponseBuilder responseBuilder = Response.status(Response.Status.NO_CONTENT);

        if(isContentReturned != null && isContentReturned){
            responseBuilder.entity(dbEntity);
            responseBuilder.status(Response.Status.OK);
        }

        if(status != null){
            responseBuilder.status(status);
        }

        if(locationHeader){
            String locationValue = type.getSimpleName() + "/" + dbEntity.getId();
            responseBuilder.header("Location", locationValue);
        }

        return responseBuilder;
    }

    protected Response.ResponseBuilder buildResponse(Paging<T> paging){

        Response.ResponseBuilder responseBuilder = Response.status(Response.Status.OK);
        responseBuilder.header("X-Count", paging.getCount());
        responseBuilder.entity(paging.getItems());

        return responseBuilder;
    }

    protected AuthorizationManager<T> initAuthorizationManager() { return null; }
    protected ValidationManager<T> initValidationManager() {
        return new ETagValidationManager<T>() {
            @Override
            protected Request getRequest() {
                return request;
            }
        };
    }

    public AuthorizationManager<T> getAuthorizationManager() {
        return authorizationManager;
    }

    public ValidationManager<T> getValidationManager() {
        return validationManager;
    }
}
