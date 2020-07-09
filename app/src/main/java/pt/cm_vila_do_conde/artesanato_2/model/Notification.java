package pt.cm_vila_do_conde.artesanato_2.model;

import com.google.firebase.Timestamp;

import java.io.Serializable;
import java.util.Date;


public class Notification implements Serializable {
    private String message;
    private int type;
    private int action;
    private String destination;
    private Timestamp timestamp = new Timestamp(new Date());
    private boolean seen = false;

    public Notification() {
    }

    public Notification(String message, int type, int action, String destination, Timestamp timestamp, boolean seen) {
        this.message = message;
        this.type = type;
        this.action = action;
        this.destination = destination;
        this.timestamp = timestamp;
        this.seen = seen;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
