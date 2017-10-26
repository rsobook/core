package si.fri.rsobook.core.api.data.response.base;


import si.fri.rsobook.core.api.data.RequestException;

import javax.ws.rs.core.Response;

public abstract class ApiBaseResponse {

    protected Response.Status status;
    protected RequestException isApiException;

    public ApiBaseResponse(Response.Status status) {
        this.status = status;
    }

    public Response.Status getStatus() {
        return status;
    }

    public void setStatus(Response.Status status) {
        this.status = status;
    }

    public boolean isStatusValid(){
        switch (status){
            case OK:
            case NO_CONTENT:
            case CREATED:
                return true;
            default:
                return false;
        }
    }

    public RequestException getIsApiException() {
        return isApiException;
    }

    public void setIsApiException(RequestException isApiException) {
        this.isApiException = isApiException;
    }
}
