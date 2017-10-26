package si.fri.rsobook.core.api.resource.base;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import si.fri.rsobook.core.api.ApiCore;
import si.fri.rsobook.core.api.data.request.IdRequest;
import si.fri.rsobook.core.api.data.request.base.ApiBaseRequest;
import si.fri.rsobook.core.api.data.response.EntityResponse;
import si.fri.rsobook.core.api.data.response.PagingResponse;
import si.fri.rsobook.core.api.exception.ApiException;
import si.fri.rsobook.core.entities.impl.BaseEntityImpl;


public class GetResource<T extends BaseEntityImpl> extends Resource<T> {

    public GetResource(ApiCore core, Class<T> type) {
        super(core, type);
    }

    public PagingResponse<T> get(String queryParams) throws ApiException {
        String url = endpointUrl + queryParams;
        HttpGet get = new HttpGet(url);
        setHeaders(get, new ApiBaseRequest());
        HttpResponse response = core.getClient().execute(get);

        return buildVcgPaging(response);
    }

    public EntityResponse<T> getById(Integer id) throws ApiException {
        return getById(new IdRequest(id));
    }
    public EntityResponse<T> getById(IdRequest idRequest) throws ApiException {
        Integer id = idRequest.getId();
        checkId(id);

        String url = endpointUrl + "/" + id;
        HttpGet get = new HttpGet(url);
        setHeaders(get, idRequest);
        HttpResponse response = core.getClient().execute(get);

        return buildVcgEntity(response);
    }

    protected void checkId(Integer id) throws ApiException {
        if(id == null){
            throw new ApiException("Id can not be null");
        }
    }

}
