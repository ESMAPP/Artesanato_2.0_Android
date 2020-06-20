package pt.cm_vila_do_conde.artesanato_2.model;

import java.io.Serializable;
import java.util.ArrayList;


public class Event implements Serializable {
    private ArrayList<String> going;
    private String image;
    private String info;
    private Location location;
    private String title;

    public Event() {
    }

    public Event(ArrayList<String> going, String image, String info, Location location, String title) {
        this.going = going;
        this.image = image;
        this.info = info;
        this.location = location;
        this.title = title;
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
}
