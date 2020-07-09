package pt.cm_vila_do_conde.artesanato_2.model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.Exclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Review implements Serializable {
    @Exclude
    private String id;
    private String userId;
    @Exclude
    private User userInfo;
    private String message;
    private ArrayList<String> likes = new ArrayList<>();
    private Timestamp createdAt = new Timestamp(new Date());

    public Review() {
    }

    public Review(String userId, String text) {
        this.userId = userId;
        this.message = text;
    }

    public Review(String userId, User userInfo, String message, ArrayList<String> likes, Timestamp createdAt) {
        this.userId = userId;
        this.userInfo = userInfo;
        this.message = message;
        this.likes = likes;
        this.createdAt = createdAt;
    }

    @Exclude
    public String getId() {
        return id;
    }

    @Exclude
    public void setId(String id) {
        this.id = id;
    }

    @Exclude
    public User getUserInfo() {
        return userInfo;
    }

    @Exclude
    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String text) {
        this.message = text;
    }

    public ArrayList<String> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<String> likes) {
        this.likes = likes;
    }
}
