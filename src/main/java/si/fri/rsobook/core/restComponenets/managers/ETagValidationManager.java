package si.fri.rsobook.core.restComponenets.managers;


import si.fri.rsobook.core.database.Database;
import si.fri.rsobook.core.database.exceptions.BusinessLogicTransactionException;
import si.fri.rsobook.core.database.manager.ValidationManager;
import si.fri.rsobook.core.entities.impl.BaseEntityImpl;
import si.fri.rsobook.core.restComponenets.exceptions.ETagException;

import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

public abstract class ETagValidationManager<T extends BaseEntityImpl> extends ValidationManager<T> {

    @Override
    public void updateValidate(T entity, Database database) throws BusinessLogicTransactionException {
        checkETag(entity);
    }

    @Override
    public void patchValidate(T entity, Database database) throws BusinessLogicTransactionException {
        checkETag(entity);
    }

    @Override
    public void deleteValidate(T entity, Database database) throws BusinessLogicTransactionException {
        checkETag(entity);
    }

    @Override
    public void toggleDeleteValidate(T entity, Database database) throws BusinessLogicTransactionException {
        checkETag(entity);
    }

    private void checkETag(T entity) throws BusinessLogicTransactionException {
        Response.ResponseBuilder rb = getRequest().evaluatePreconditions(entity.getEntityTag());
        if(rb != null) {
            throw new ETagException(Response.Status.PRECONDITION_FAILED, null, rb);
        }
    }

    protected abstract Request getRequest();

}
