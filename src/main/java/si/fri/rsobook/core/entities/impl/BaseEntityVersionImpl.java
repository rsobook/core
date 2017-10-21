package si.fri.rsobook.core.entities.impl;

public interface BaseEntityVersionImpl<I, T extends BaseEntityVersionImpl> extends BaseEntityImpl<I, T> {

    void prePersist(int versionOrder);

    I getOriginId();
    void setOriginId(I originId);

    Integer getVersionOrder();
    void setVersionOrder(int versionOrder);

    Boolean getIsLatest();
    void setIsLatest(Boolean latest);

    void setAllBaseVersionPropertiesToNull();
}
