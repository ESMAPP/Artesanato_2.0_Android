package pt.cm_vila_do_conde.artesanato_2.model;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;
import java.util.List;

public class ChatRoom implements Serializable {
    @Exclude
    private String id;
    private String artisanId;
    private String userId;
    @Exclude
    private List<Message> messageList;
    private Message lastMessage;

    @Exclude
    private User userInfo;

    public ChatRoom() {
    }

    public ChatRoom(String id, String artisanId, String userId) {
        this.id = id;
        this.artisanId = artisanId;
        this.userId = userId;
    }

    public ChatRoom(String id, String artisanId, String userId, List<Message> messageList) {
        this.id = id;
        this.artisanId = artisanId;
        this.userId = userId;
        this.messageList = messageList;
    }

    @Exclude
    public User getUserInfo() {
        return userInfo;
    }

    @Exclude
    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }

    @Exclude
    public String getId() {
        return id;
    }

    @Exclude
    public void setId(String id) {
        this.id = id;
    }

    public String getArtisanId() {
        return artisanId;
    }

    public void setArtisanId(String artisanId) {
        this.artisanId = artisanId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Exclude
    public List<Message> getMessageList() {
        return messageList;
    }

    @Exclude
    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public Message getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }
}
