package si.fri.rsobook.core.entities.impl;

import javax.persistence.EntityManager;
import javax.ws.rs.core.EntityTag;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

public interface BaseEntityImpl<I, T extends BaseEntityImpl> {

    void update(T object, EntityManager em) throws IllegalAccessException;
    void patch(T object, EntityManager em) throws IllegalAccessException;

    void prePersist();
    void preUpdate();

    List<Field> getAllClassFields();
    BaseEntityImpl cloneObject();

    boolean baseSkip(Field field);
    boolean genericUpdateSkip(Field field);
    boolean genericPatchSkip(Field field);
    boolean genericIsDifferentSkip(Field field);

    void genericUpdate(T object, EntityManager em) throws IllegalAccessException;
    void genericPatch(T object, EntityManager em) throws IllegalAccessException;

    boolean areObjectDifferent(Class type, Field field, Object e1, Object e2);
    boolean isUpdateDifferent(BaseEntityImpl entity) throws IllegalAccessException;
    boolean isPatchDifferent(BaseEntityImpl entity) throws IllegalAccessException;

    void setAllBasePropertiesToNull();

    String isValidDatabaseItem() throws IllegalAccessException;

    EntityTag getEntityTag();

    I getId();
    void setId(I id);

    Integer getVersion();
    void setVersion(Integer version);

    Boolean getIsDeleted();
    void setIsDeleted(Boolean active);

    Date getCreatedOn();
    void setCreatedOn(Date createdOn);

    Date getEditedOn();
    void setEditedOn(Date editedOn);
}
