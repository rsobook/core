package si.fri.rsobook.core.restComponenets.resource;

import si.fri.rsobook.core.database.exceptions.BusinessLogicTransactionException;
import si.fri.rsobook.core.entities.impl.BaseEntityVersionImpl;
import si.fri.rsobook.core.restComponenets.providers.configuration.PATCH;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

public abstract class CrudVersionResource<I, T extends BaseEntityVersionImpl<I, T>> extends GetResource<I, T> {

    public CrudVersionResource(Class<T> type) {
        super(type);
    }

    @POST
    public Response create(@HeaderParam("X-Content") Boolean xContent, T entity) throws BusinessLogicTransactionException {
        entity.setId(null);

        T dbEntity = getDatabaseService().createVersion(entity, authorizationManager, validationManager);

        return buildResponse(dbEntity, xContent, true, Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@HeaderParam("X-Content") Boolean xContent,
                           @PathParam("id") String rawId, T entity) throws BusinessLogicTransactionException {
        I id = parseId(rawId);

        entity.setId(id);

        T dbEntity = getDatabaseService().updateVersion(id, entity, authorizationManager, validationManager);

        return buildResponse(dbEntity, xContent, true, Response.Status.CREATED).build();
    }

    @PATCH
    @Path("{id}")
    public Response patch(@HeaderParam("X-Content") Boolean xContent,
                          @PathParam("id") String rawId, T entity) throws BusinessLogicTransactionException {
        I id = parseId(rawId);

        entity.setId(id);

        T dbEntity = getDatabaseService().patchVersion(id, entity, authorizationManager, validationManager);

        return buildResponse(dbEntity, xContent, true, Response.Status.CREATED).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@HeaderParam("X-Content") Boolean xContent,
                           @PathParam("id") String rawId) throws BusinessLogicTransactionException {
        I id = parseId(rawId);

        T dbEntity = getDatabaseService().delete(type, id, authorizationManager, validationManager);

        return buildResponse(dbEntity, xContent).build();
    }

    @PUT
    @Path("{id}/toggleIsDeleted")
    public Response toggleIsDeleted(@HeaderParam("X-Content") Boolean xContent,
                                    @PathParam("id") String rawId) throws BusinessLogicTransactionException {
        I id = parseId(rawId);

        T dbEntity = getDatabaseService().toggleIsDeleted(type, id, authorizationManager, validationManager);

        return buildResponse(dbEntity, xContent).build();
    }
}
