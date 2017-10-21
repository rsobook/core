package si.fri.rsobook.core.database.manager;

import si.fri.rsobook.core.database.Database;
import si.fri.rsobook.core.database.exceptions.BusinessLogicTransactionException;
import si.fri.rsobook.core.entities.impl.BaseEntityImpl;

public abstract class ValidationManager<T extends BaseEntityImpl> {

    public void baseValidate(T entity, Database database) throws BusinessLogicTransactionException { }

    public void createValidate(T entity, Database database) throws BusinessLogicTransactionException { baseValidate(entity, database); }
    public void updateValidate(T entity, Database database) throws BusinessLogicTransactionException { baseValidate(entity, database); }
    public void patchValidate(T entity, Database database) throws BusinessLogicTransactionException { baseValidate(entity, database); }

    public void deleteValidate(T entity, Database database) throws BusinessLogicTransactionException { }
    public void toggleDeleteValidate(T entity, Database database) throws BusinessLogicTransactionException { }
    public void permDeleteValidate(T entity, Database database) throws BusinessLogicTransactionException { }

}
