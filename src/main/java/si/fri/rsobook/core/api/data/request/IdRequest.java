package si.fri.rsobook.core.api.data.request;


import si.fri.rsobook.core.api.data.request.base.ApiBaseRequest;

public class IdRequest extends ApiBaseRequest {

    protected Integer id;

    public IdRequest(Integer id) {
        this.id = id;
    }

    public IdRequest(Integer id, String eTagHeader) {
        this.id = id;
        this.eTagHeader = eTagHeader;
    }

    public IdRequest(Integer id, boolean xContentHeader) {
        this.id = id;
        this.xContentHeader = xContentHeader;
    }

    public IdRequest(Integer id, String eTagHeader, boolean xContentHeader) {
        this.id = id;
        this.eTagHeader = eTagHeader;
        this.xContentHeader = xContentHeader;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
