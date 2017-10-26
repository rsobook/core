package si.fri.rsobook.core.api.resource.base;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import si.fri.rsobook.core.api.ApiCore;
import si.fri.rsobook.core.api.data.request.EntityRequest;
import si.fri.rsobook.core.api.data.request.IdRequest;
import si.fri.rsobook.core.api.data.response.EntityResponse;
import si.fri.rsobook.core.api.exception.ApiException;
import si.fri.rsobook.core.entities.impl.BaseEntityImpl;

public class CrudResource<T extends BaseEntityImpl> extends GetResource<T> {

    public CrudResource(ApiCore client, Class<T> type) {
        super(client, type);
    }

    public EntityResponse<T> post(T item) throws ApiException {
        return post(new EntityRequest<T>(item, isContentHeaderAdded()));
    }
    public EntityResponse<T> post(EntityRequest<T> apiRequest) throws ApiException {
        T item = apiRequest.getEntity();
        checkEntity(item, true);

        String url = endpointUrl;
        HttpPost post = new HttpPost(url);
        setHeaders(post, apiRequest);
        post.setEntity(buildJsonContent(item));

        HttpResponse response = core.getClient().execute(post);

        return buildVcgEntity(response);
    }

    public EntityResponse<T> put(T item) throws ApiException {
        return put(new EntityRequest<T>(item, isContentHeaderAdded()));
    }
    public EntityResponse<T> put(EntityRequest<T> apiRequest) throws ApiException {
        T item = apiRequest.getEntity();
        checkEntity(item);

        String url = endpointUrl + "/" + item.getId();
        HttpPut put = new HttpPut(url);
        setHeaders(put, apiRequest);
        put.setEntity(buildJsonContent(item));

        HttpResponse response = core.getClient().execute(put);

        return buildVcgEntity(response);
    }

    public EntityResponse<T> patch(T item) throws ApiException {
        return patch(new EntityRequest<T>(item, isContentHeaderAdded()));
    }
    public EntityResponse<T> patch(EntityRequest<T> apiRequest) throws ApiException {
        T item = apiRequest.getEntity();
        checkEntity(item);

        String url = endpointUrl + "/" + item.getId();
        HttpPatch patch = new HttpPatch(url);
        setHeaders(patch, apiRequest);
        patch.setEntity(buildJsonContent(item));

        HttpResponse response = core.getClient().execute(patch);

        return buildVcgEntity(response);
    }

    public EntityResponse<T> delete(Integer id) throws ApiException {
        return delete(new IdRequest(id, isContentHeaderAdded()));
    }
    public EntityResponse<T> delete(IdRequest apiRequest) throws ApiException {
        Integer id = apiRequest.getId();
        checkId(id);

        String url = endpointUrl + "/" + id;
        HttpDelete delete = new HttpDelete(url);
        setHeaders(delete, apiRequest);

        HttpResponse response = core.getClient().execute(delete);

        return buildVcgEntity(response);
    }

    public EntityResponse<T> toggleIsDeleted(Integer id) throws ApiException {
        return toggleIsDeleted(new IdRequest(id, isContentHeaderAdded()));
    }
    public EntityResponse<T> toggleIsDeleted(IdRequest apiRequest) throws ApiException {
        Integer id = apiRequest.getId();
        checkId(id);

        String url = endpointUrl + "/" + id + "/toggleIsDeleted";
        HttpPut put = new HttpPut(url);
        setHeaders(put, apiRequest);

        HttpResponse response = core.getClient().execute(put);

        return buildVcgEntity(response);
    }




}
