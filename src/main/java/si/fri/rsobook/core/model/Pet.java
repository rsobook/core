package si.fri.rsobook.core.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import si.fri.rsobook.core.entities.EntityUUID;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="pet")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class Pet extends EntityUUID<Pet> {

    private UUID ownerId;

    private String name;

    private String imageHandle;

    private Integer age;


    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageHandle() {
        return imageHandle;
    }

    public void setImageHandle(String imageHandle) {
        this.imageHandle = imageHandle;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
