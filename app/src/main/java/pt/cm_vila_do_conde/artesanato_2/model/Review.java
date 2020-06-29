package pt.cm_vila_do_conde.artesanato_2.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Review implements Serializable {
    private String userId;
    private String text;
    private ArrayList<String> likes;

    public Review() {
    }

    public Review(String userId, String text, ArrayList<String> likes) {
        this.userId = userId;
        this.text = text;
        this.likes = likes;
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
