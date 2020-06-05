package pt.cm_vila_do_conde.artesanato_2.model;

import android.net.Uri;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    @SuppressWarnings("WeakerAccess")
    private String email;
    @Exclude
    private boolean isAuthenticated;
    @Exclude
    private boolean isNew, isCreated;
    private String uid;
    private String name;
    private String profile_pic;
    private int reputation = 0;
    private ArrayList<Badge> badges = new ArrayList<Badge>();
    private int ranking = -1; // -1 == Unranked
    private int type = 3; // 1 - Admin, 2 - Artisan, 3 - Visitor || Defaults to: 3

    public User() {
    }

    public User(String uid, String name, String email, String profile_pic) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.profile_pic = profile_pic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    @Exclude
    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    @Exclude
    public boolean isCreated() {
        return isCreated;
    }

    public void setCreated(boolean created) {
        isCreated = created;
    }

    @Exclude
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePic() {
        return profile_pic;
    }

    public void setProfilePic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public ArrayList<Badge> getBadges() {
        return badges;
    }

    public void setBadges(ArrayList<Badge> badges) {
        this.badges = badges;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
