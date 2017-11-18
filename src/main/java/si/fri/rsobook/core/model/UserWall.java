package si.fri.rsobook.core.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import si.fri.rsobook.core.entities.EntityUUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="user_wall")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class UserWall extends EntityUUID<UserWall> {

    private UUID userId;

    @Column(columnDefinition="TEXT")
    private String content;


    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
