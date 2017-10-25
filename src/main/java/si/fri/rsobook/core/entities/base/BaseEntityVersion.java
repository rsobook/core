package si.fri.rsobook.core.entities.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import si.fri.rsobook.core.entities.impl.BaseEntityImpl;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.lang.reflect.Field;

@MappedSuperclass
public abstract class BaseEntityVersion<I, T extends BaseEntityVersion> extends BaseEntity<I, T> implements BaseEntityImpl<I, T> {

    @Column(name = "origin_id")
    protected Integer originId;

    @Column(name = "version_order", nullable = false)
    protected Integer versionOrder;

    @Column(name = "is_latest", nullable = false)
    protected Boolean isLatest;


    @JsonIgnore
    @Override
    public void prePersist() {
        super.prePersist();
        this.isLatest = true;
        this.versionOrder = 1;
    }

    @JsonIgnore
    public void prePersist(int versionOrder) {
        super.prePersist();
        this.isLatest = true;
        this.versionOrder = versionOrder;
    }

    @JsonIgnore
    public void setAllBaseVersionPropertiesToNull() {
        originId = null;
        versionOrder = null;
        isLatest = null;
    }

    @JsonIgnore
    @Override
    public boolean baseSkip(Field field){
        boolean skip = super.baseSkip(field);
        if(skip) {
            return skip;
        } else {
            switch (field.getName()){
                case "originId":
                case "versionOrder":
                case "isLatest":
                    return true;
                default:
                    return false;
            }
        }
    }

    public Integer getOriginId() {
        return originId;
    }

    public void setOriginId(Integer originId) {
        this.originId = originId;
    }

    public Integer getVersionOrder() {
        return versionOrder;
    }

    public void setVersionOrder(int versionOrder) {
        this.versionOrder = versionOrder;
    }

    public Boolean getIsLatest() {
        return isLatest;
    }

    public void setIsLatest(Boolean latest) {
        isLatest = latest;
    }
}

