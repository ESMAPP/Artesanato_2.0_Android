package pt.cm_vila_do_conde.artesanato_2.model;

import java.io.Serializable;

public class Badge implements Serializable {

    private String uid;
    private String description;
    private String icon;
    private String label;
    private int value;

    public Badge() {

    }


    public Badge(String uid, String description, String icon, String label, int value) {

        this.uid = uid;
        this.description = description;
        this.icon = icon;
        this.label = label;
        this.value = value;

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}


