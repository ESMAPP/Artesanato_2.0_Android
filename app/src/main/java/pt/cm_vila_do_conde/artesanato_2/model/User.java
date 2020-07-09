package pt.cm_vila_do_conde.artesanato_2.model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.Exclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class User implements Serializable {
    @SuppressWarnings("WeakerAccess")
    private String email;
    @Exclude
    private boolean isAuthenticated;
    @Exclude
    private boolean isNew, isCreated;
    private String uid;
    private String name;
    private String profilePic = "null";
    private int reputation = 0;
    private ArrayList<String> badges = new ArrayList<>();
    private int ranking;
    private int type = 3; // 1 - Admin, 2 - Artisan, 3 - Visitor || Defaults to: 3
    private ArrayList<String> contests;
    private Timestamp createdAt = new Timestamp(new Date());
    private Timestamp updatedAt = new Timestamp(new Date());
    @Exclude
    private Throwable error;

    public User() {
    }

    public User(String uid, String name, String email, String profilePic) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.profilePic = profilePic;
    }

    public User(Throwable error) {
        this.error = error;
    }

    public User(String email, boolean isAuthenticated, boolean isNew, boolean isCreated, String uid, String name,
                String profilePic, int reputation, ArrayList<String> badges, ArrayList<String> contests,
                int ranking, int type, Timestamp createdAt, Timestamp updatedAt) {
        this.email = email;
        this.isAuthenticated = isAuthenticated;
        this.isNew = isNew;
        this.isCreated = isCreated;
        this.uid = uid;
        this.name = name;
        this.profilePic = profilePic;
        this.reputation = reputation;
        this.badges = badges;
        this.contests = contests;
        this.ranking = ranking;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Exclude
    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
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

    public String getFirstName(String name) {
        int i = name.indexOf(' ');

        if (i == -1) {
            return name;
        }

        return name.substring(0, i);
    }

    public String getInitials(String name) {
        StringBuilder initialsName = new StringBuilder();

        for (int i = 1; i < name.length() - 1; i++) {
            if (Character.isUpperCase(name.charAt(i))) {
                char letter = name.charAt(i);
                initialsName.append(" ").append(letter).append(".");
            }
        }

        return String.valueOf(initialsName);
    }

    public String getShortName() {
        return getFirstName(name) + getInitials(name);
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public ArrayList<String> getBadges() {
        return badges;
    }

    public void setBadges(ArrayList<String> badges) {
        this.badges = badges;
    }

    public ArrayList<String> getContests() {
        return contests;
    }

    public void setContests(ArrayList<String> contests) {
        this.contests = contests;
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
