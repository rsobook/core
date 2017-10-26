package si.fri.rsobook.core.entities;

import si.fri.rsobook.core.entities.base.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public abstract class EntityUUID<T extends EntityUUID> extends BaseEntity<UUID, T> {

    @Id
    @GeneratedValue
    protected UUID id;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public void prePersist() {
        super.prePersist();
        id = null;
    }

    @Override
    public void setAllBasePropertiesToNull() {
        super.setAllBasePropertiesToNull();
        id = null;
    }

}
