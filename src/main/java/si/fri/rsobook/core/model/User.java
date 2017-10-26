package si.fri.rsobook.core.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import si.fri.rsobook.core.entities.EntityUUID;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="`user`")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class User extends EntityUUID<User> {

    private UUID keycloakId;

    private String name;

    private String surname;

    private String email;

    private String imgHandle;


    public UUID getKeycloakId() {
        return keycloakId;
    }

    public void setKeycloakId(UUID keycloakId) {
        this.keycloakId = keycloakId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgHandle() {
        return imgHandle;
    }

    public void setImgHandle(String imgHandle) {
        this.imgHandle = imgHandle;
    }
}
