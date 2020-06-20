package pt.cm_vila_do_conde.artesanato_2.model;

import com.google.firebase.Timestamp;

import java.io.Serializable;
import java.util.ArrayList;


public class Event implements Serializable {
    private String id;
    private ArrayList<String> going;
    private String image;
    private String info;
    private Location location;
    private String title;
    private Timestamp startDate;
    private Timestamp endDate;
    private String category;

    public Event() {
    }

    public Event(String id, ArrayList<String> going, String image,
                 String info, Location location, String title,
                 Timestamp startDate, Timestamp endDate, String category) {
        this.id = id;
        this.going = going;
        this.image = image;
        this.info = info;
        this.location = location;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
