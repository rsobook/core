package si.fri.rsobook.core.restComponenets.exceptions;


import si.fri.rsobook.core.database.exceptions.BusinessLogicTransactionException;

import javax.ws.rs.core.Response;

public class ETagException extends BusinessLogicTransactionException {

    private Response.ResponseBuilder responseBuilder;

    public ETagException(Response.Status status, String message, Response.ResponseBuilder responseBuilder) {
        super(status, message);
        this.responseBuilder = responseBuilder;
    }

    public Response.ResponseBuilder getResponseBuilder() {
        return responseBuilder;
    }
}
