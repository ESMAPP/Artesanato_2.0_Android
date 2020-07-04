package pt.cm_vila_do_conde.artesanato_2.model;

import com.google.firebase.Timestamp;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private String text;
    private String senderId;
    private Timestamp createdAt = new Timestamp(new Date());

    public Message() {
    }

    public Message(String text, String senderId) {
        this.text = text;
        this.senderId = senderId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
