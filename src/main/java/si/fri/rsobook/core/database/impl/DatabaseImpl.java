package si.fri.rsobook.core.database.impl;

import com.github.tfaga.lynx.beans.QueryParameters;
import com.github.tfaga.lynx.interfaces.CriteriaFilter;
import org.jinq.jpa.JPAJinqStream;
import si.fri.rsobook.core.database.dto.Paging;
import si.fri.rsobook.core.database.exceptions.BusinessLogicTransactionException;
import si.fri.rsobook.core.database.manager.AuthorizationManager;
import si.fri.rsobook.core.database.manager.ValidationManager;
import si.fri.rsobook.core.entities.impl.BaseEntityImpl;
import si.fri.rsobook.core.entities.impl.BaseEntityVersionImpl;

import javax.persistence.EntityManager;

public interface DatabaseImpl {

    <I, T extends BaseEntityImpl<I, T>> Paging<T> getList(Class<T> c, QueryParameters param, AuthorizationManager<T> authorizationManager) throws BusinessLogicTransactionException;
    <I, T extends BaseEntityImpl<I, T>> Paging<T> getList(Class<T> c, CriteriaFilter<T> customFilter, AuthorizationManager<T> authorizationManager) throws BusinessLogicTransactionException;
    <I, T extends BaseEntityImpl<I, T>> T get(Class<T> c, I id, AuthorizationManager<T> authorizationManager) throws BusinessLogicTransactionException;

    <I, T extends BaseEntityImpl<I, T>> T create(T newEntity, AuthorizationManager<T> authorizationManager, ValidationManager<T> validationManager) throws BusinessLogicTransactionException;
    <I, T extends BaseEntityImpl<I, T>> T update(T newEntity, AuthorizationManager<T> authorizationManager, ValidationManager<T> validationManager) throws BusinessLogicTransactionException;
    <I, T extends BaseEntityImpl<I, T>> T patch(T newEntity, AuthorizationManager<T> authorizationManager, ValidationManager<T> validationManager) throws BusinessLogicTransactionException;
    <I, T extends BaseEntityImpl<I, T>> T delete(Class<T> c, I id, AuthorizationManager<T> authorizationManager, ValidationManager<T> validationManager) throws BusinessLogicTransactionException;
    <I, T extends BaseEntityImpl<I, T>> T toggleIsDeleted(Class<T> c, I id, AuthorizationManager<T> authorizationManager, ValidationManager<T> validationManager) throws BusinessLogicTransactionException;
    <I, T extends BaseEntityImpl<I, T>> T permDelete(Class<T> c, I id, AuthorizationManager<T> authorizationManager, ValidationManager<T> validationManager) throws BusinessLogicTransactionException;
    <I, T extends BaseEntityVersionImpl<I, T>> T createVersion(T newEntityVersion, AuthorizationManager<T> authorizationManager, ValidationManager<T> validationManager) throws BusinessLogicTransactionException;
    <I, T extends BaseEntityVersionImpl<I, T>> T updateVersion(I oldId, T newBaseEntityVersion, AuthorizationManager<T> authorizationManager, ValidationManager<T> validationManager) throws BusinessLogicTransactionException;
    <I, T extends BaseEntityVersionImpl<I, T>> T patchVersion(I oldId, T newBaseEntityVersion, AuthorizationManager<T> authorizationManager, ValidationManager<T> validationManager) throws BusinessLogicTransactionException;

    EntityManager getEntityManager();
    <U extends BaseEntityImpl> JPAJinqStream<U> getStream(Class<U> entity);

}
