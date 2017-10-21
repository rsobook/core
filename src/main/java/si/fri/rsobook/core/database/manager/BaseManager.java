package si.fri.rsobook.core.database.manager;


import si.fri.rsobook.core.database.Database;
import si.fri.rsobook.core.entities.impl.BaseEntityImpl;

public abstract class BaseManager<T extends BaseEntityImpl> {

    protected Database database;

    protected AuthorizationManager<T> authorizationManager;

    public BaseManager(Database database, AuthorizationManager<T> authorizationManager) {
        this.database = database;
        this.authorizationManager = authorizationManager;
    }

}
