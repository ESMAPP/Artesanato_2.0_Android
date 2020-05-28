package pt.cm_vila_do_conde.artesanato_2.model;

import android.net.Uri;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    public String uid;
    public String name;
    public Uri avatar;
    public int reputation = 0;
    public ArrayList<Badge> badges = new ArrayList<Badge>();
    public ArrayList<Notification> notifications = new ArrayList<Notification>();
    public int ranking = -1; // -1 == Unranked
    public int type = 3; // 1 - Admin, 2 - Artisan, 3 - Visitor || Defaults to: 3
    @SuppressWarnings("WeakerAccess")
    public String email;
    @Exclude
    public boolean isAuthenticated;
    @Exclude
    public boolean isNew, isCreated;

    public User(){}

    public User(String uid, String name, String email, Uri avatar){
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.avatar = avatar;
    }
}
