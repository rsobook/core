package si.fri.rsobook.core.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import si.fri.rsobook.core.entities.EntityUUID;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="user_messages")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class UserMessages extends EntityUUID<UserMessages> {

    private UUID senderId;

    private UUID recipientID;

    private String content;


    public UUID getSenderId() {
        return senderId;
    }

    public void setSenderId(UUID senderId) {
        this.senderId = senderId;
    }

    public UUID getRecipientID() {
        return recipientID;
    }

    public void setRecipientID(UUID recipientID) {
        this.recipientID = recipientID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
