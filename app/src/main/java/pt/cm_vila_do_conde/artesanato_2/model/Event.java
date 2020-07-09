package pt.cm_vila_do_conde.artesanato_2.model;

import com.google.firebase.Timestamp;

import java.io.Serializable;
import java.util.ArrayList;


public class Event implements Serializable {
    private String uid;
    private ArrayList<String> going;
    private String image;
    private String info;
    private Location location;
    private String title;
    private String description;
    private Timestamp startDate;
    private Timestamp endDate;
    private String category;
    private boolean featured;
    private Timestamp createdAt;


    public Event() {
    }

    public Event(String uid, ArrayList<String> going, String image,
                 String info, Location location, String title, String description,
                 Timestamp startDate, Timestamp endDate, String category,
                 boolean featured, Timestamp createdAt
    ) {
        this.uid = uid;
        this.going = going;
        this.image = image;
        this.info = info;
        this.location = location;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.category = category;
        this.featured = featured;
        this.createdAt = createdAt;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public ArrayList<String> getGoing() {
        return going;
    }

    public void setGoing(ArrayList<String> going) {
        this.going = going;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
