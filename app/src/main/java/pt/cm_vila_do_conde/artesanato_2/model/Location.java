package pt.cm_vila_do_conde.artesanato_2.model;


import com.google.firebase.firestore.GeoPoint;

import java.io.Serializable;

public class Location implements Serializable {
    private GeoPoint coordinates;
    private String name;

    public Location() {
    }

    public Location(GeoPoint coordinates, String name) {
        this.coordinates = coordinates;
        this.name = name;
    }

    public GeoPoint getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(GeoPoint coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
