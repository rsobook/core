package si.fri.rsobook.core.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import si.fri.rsobook.core.entities.EntityUUID;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="user_friends")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class UserFriends extends EntityUUID<UserFriends> {

    private UUID userId;

    private UUID friendsId;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getFriendsId() {
        return friendsId;
    }

    public void setFriendsId(UUID friendsId) {
        this.friendsId = friendsId;
    }
}
