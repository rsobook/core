package si.fri.rsobook.core.database;


import si.fri.rsobook.core.database.exceptions.BusinessLogicTransactionException;
import si.fri.rsobook.core.entities.impl.BaseEntityImpl;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;

public class DBValidators {

    public static <I, T extends BaseEntityImpl<I, T>> T isEntityValid(T value, boolean nullable, Database db) throws BusinessLogicTransactionException {
        if(value == null || value.getId() == null){
            if(!nullable){
                throw new BusinessLogicTransactionException(Response.Status.BAD_REQUEST, value.getClass().getSimpleName() + " can not be null and id must be set.");
            } else {
                return null;
            }
        } else {
            try {
                T entity = db.get((Class<T>) value.getClass(), value.getId(), null);
                db.getEntityManager().detach(entity);
                return entity;
            } catch (BusinessLogicTransactionException e){
                throw new BusinessLogicTransactionException(Response.Status.BAD_REQUEST, value.getClass().getSimpleName() + " does not exist.");
            }
        }
    }

    public static void isBigDecimalValid(BigDecimal value, boolean nullable, BigDecimal max, BigDecimal min, String fieldName) throws BusinessLogicTransactionException {
        if(value != null) {
            if(max != null){
                if(max.compareTo(value) < 0){
                    throw new BusinessLogicTransactionException(Response.Status.BAD_REQUEST, fieldName + " can not be bigger than " + max.toString() + ".");
                }
            }
            if(min != null){
                if(min.compareTo(value) > 0){
                    throw new BusinessLogicTransactionException(Response.Status.BAD_REQUEST, fieldName + " can not be smaller than " + min.toString() + ".");
                }
            }
        } else {
            if(!nullable){
                throw new BusinessLogicTransactionException(Response.Status.BAD_REQUEST, fieldName + " can not be null.");
            }
        }
    }
}
