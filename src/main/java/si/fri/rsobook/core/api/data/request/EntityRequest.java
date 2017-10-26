package si.fri.rsobook.core.api.data.request;


import si.fri.rsobook.core.api.data.request.base.ApiBaseRequest;
import si.fri.rsobook.core.entities.impl.BaseEntityImpl;

public class EntityRequest<T extends BaseEntityImpl> extends ApiBaseRequest {

    private T entity;

    public EntityRequest(T entity) {
        this.entity = entity;
    }

    public EntityRequest(T entity, String eTagHeader) {
        this.entity = entity;
        this.eTagHeader = eTagHeader;
    }

    public EntityRequest(T entity, boolean xContentHeader) {
        this.entity = entity;
        this.xContentHeader = xContentHeader;
    }

    public EntityRequest(T entity, String eTagHeader, boolean xContentHeader) {
        this.entity = entity;
        this.eTagHeader = eTagHeader;
        this.xContentHeader = xContentHeader;
    }


    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
}
