package si.fri.rsobook.core.restComponenets.providers.exceptions;


import si.fri.rsobook.core.restComponenets.exceptions.ETagException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ETagExceptionMapper implements ExceptionMapper<ETagException>{

    @Override
    public Response toResponse(ETagException exception) {
        return exception.getResponseBuilder().build();
    }

}
