package si.fri.rsobook.core.entities;

import si.fri.rsobook.core.entities.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public abstract class EntityInteger extends BaseEntity<Integer, EntityInteger> {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    protected Integer id;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
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
