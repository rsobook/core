package si.fri.rsobook.core.api.data.response;


import si.fri.rsobook.core.api.data.RequestException;
import si.fri.rsobook.core.api.data.response.base.ApiBaseResponse;

import javax.ws.rs.core.Response;

public class EntityResponse<T> extends ApiBaseResponse {

    private String locationHeader;
    private String eTagHeader;

    private T item;


    public EntityResponse(Response.Status status){
        super(status);
    }

    public EntityResponse(Response.Status status, T item){
        super(status);
        this.item = item;
    }

    public EntityResponse(Response.Status status, RequestException e) {
        super(status);
        this.isApiException = e;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public String getLocationHeader() {
        return locationHeader;
    }

    public void setLocationHeader(String locationHeader) {
        this.locationHeader = locationHeader;
    }

    public Integer getLocationId(){
        if(locationHeader != null){
            try {
                int index = locationHeader.indexOf('/');
                String id = locationHeader.substring(index + 1);
                return Integer.valueOf(id);
            } catch (Exception e){}
        }

        return null;
    }

    public String geteTagHeader() {
        return eTagHeader;
    }

    public void seteTagHeader(String eTagHeader) {
        this.eTagHeader = eTagHeader;
    }
}
