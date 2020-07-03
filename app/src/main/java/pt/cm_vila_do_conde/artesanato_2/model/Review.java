package pt.cm_vila_do_conde.artesanato_2.model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.Exclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Review implements Serializable {
    private String userId;
    @Exclude
    private User userInfo;
    private String text;
    private ArrayList<String> likes = new ArrayList<>();
    private Timestamp createdAt = new Timestamp(new Date());

    public Review() {
    }

    public Review(String userId, String text){
        this.userId = userId;
        this.text = text;
    }

    public Review(String userId, User userInfo, String text, ArrayList<String> likes, Timestamp createdAt) {
        this.userId = userId;
        this.userInfo = userInfo;
        this.text = text;
        this.likes = likes;
        this.createdAt = createdAt;
    }

    @Exclude
    public User getUserInfo() {
        return userInfo;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Exclude
    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<String> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<String> likes) {
        this.likes = likes;
    }
}
