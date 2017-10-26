package si.fri.rsobook.core.api.data.response;


import si.fri.rsobook.core.api.data.RequestException;
import si.fri.rsobook.core.api.data.response.base.ApiBaseResponse;

import javax.ws.rs.core.Response;
import java.util.List;

public class PagingResponse<T> extends ApiBaseResponse {

    private Integer count = 0;
    private List<T> items;

    public PagingResponse(Response.Status status){
        super(status);
    }

    public PagingResponse(Response.Status status, List<T> items, Integer count){
        super(status);
        this.items = items;
        this.count = count;
    }

    public PagingResponse(Response.Status status, RequestException e){
        super(status);
        this.isApiException = e;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

}
