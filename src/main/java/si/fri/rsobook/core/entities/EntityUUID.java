package si.fri.rsobook.core.entities;

import si.fri.rsobook.core.entities.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.UUID;

public class EntityUUID extends BaseEntity<UUID, EntityUUID> {

    @Id
    @org.hibernate.annotations.Type(type="pg-uuid")
    @Column(name = "id")
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
