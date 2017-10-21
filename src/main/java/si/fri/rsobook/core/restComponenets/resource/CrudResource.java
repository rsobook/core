package si.fri.rsobook.core.restComponenets.resource;


import si.fri.rsobook.core.database.exceptions.BusinessLogicTransactionException;
import si.fri.rsobook.core.entities.impl.BaseEntityImpl;
import si.fri.rsobook.core.restComponenets.providers.configuration.PATCH;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;


public abstract class CrudResource<I, T extends BaseEntityImpl<I, T>> extends GetResource<I, T> {


    public CrudResource(Class<T> type) {
        super(type);
    }

    @POST
    public Response create(@HeaderParam("X-Content") Boolean xContent, T entity) throws BusinessLogicTransactionException {
        entity.setId(null);

        T dbEntity = getDatabaseService().create(entity, authorizationManager, validationManager);

        return buildResponse(dbEntity, xContent, false, Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@HeaderParam("X-Content") Boolean xContent,
                           @PathParam("id") String rawId, T newObject) throws BusinessLogicTransactionException {
        I id = parseId(rawId);

        newObject.setId(id);

        T dbEntity = getDatabaseService().update(newObject, authorizationManager, validationManager);

        return buildResponse(dbEntity, xContent).build();
    }

    @PATCH
    @Path("{id}")
    public Response patch(@HeaderParam("X-Content") Boolean xContent,
                          @PathParam("id") String rawId, T entity) throws BusinessLogicTransactionException {
        I id = parseId(rawId);

        entity.setId(id);

        T dbEntity = getDatabaseService().patch(entity, authorizationManager, validationManager);

        return buildResponse(dbEntity, xContent).build();
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
