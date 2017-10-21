package si.fri.rsobook.core.database.manager;


import com.github.tfaga.lynx.beans.QueryParameters;
import com.github.tfaga.lynx.interfaces.CriteriaFilter;
import si.fri.rsobook.core.database.dto.AuthEntity;
import si.fri.rsobook.core.database.Database;
import si.fri.rsobook.core.database.exceptions.BusinessLogicTransactionException;
import si.fri.rsobook.core.entities.impl.BaseEntityImpl;

public abstract class AuthorizationManager<T extends BaseEntityImpl> {

    protected AuthEntity authEntity;

    public AuthorizationManager(AuthEntity authEntity) {
        this.authEntity = authEntity;
    }

    public void setAuthorityFilter(QueryParameters queryParameters, Database database) throws BusinessLogicTransactionException {}
    public void setAuthorityCriteria(CriteriaFilter<T> criteriaAuthority, Database database) throws BusinessLogicTransactionException {}

    public void checkAuthority(T entity, Database database) throws BusinessLogicTransactionException {}
    public  void setEntityAuthority(T entityAuthority, Database database) throws BusinessLogicTransactionException {}
}
