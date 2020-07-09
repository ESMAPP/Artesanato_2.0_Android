package pt.cm_vila_do_conde.artesanato_2.model;

import com.google.firebase.Timestamp;

import java.io.Serializable;
import java.util.ArrayList;


public class Artisan implements Serializable {
    private String uid;
    private String associatedUser;
    private String name;
    private String description;
    private Attributes attributes;
    private String profilePic; /* TODO: set initial profile pic to placeholder */
    private ArrayList<String> gallery;
    private int coins;
    private int subscriptionType;
    private Timestamp subscriptionPaymentDate;
    private int views;
    private int reputation;
    private int ranking;    /* TODO: get last artisan ranking position */
    private boolean validated;
    private ArrayList<String> uniqueViews;
    private Timestamp createdAt;
    private String city;
    private String category;
    private boolean featured;
    private ArrayList<Review> reviews;
    private ArrayList<String> contests;

    public Artisan() {
    }

    public Artisan(String uid, String associatedUser, String name, String description,
                   Attributes attributes, String profilePic, ArrayList<String> gallery,
                   int coins, int subscriptionType, Timestamp subscriptionPaymentDate,
                   int views, int reputation, int ranking, boolean validated,
                   ArrayList<String> uniqueViews, Timestamp createdAt, String city,
                   String category, boolean featured, ArrayList<Review> reviews, ArrayList<String> contests) {
        this.uid = uid;
        this.associatedUser = associatedUser;
        this.name = name;
        this.description = description;
        this.attributes = attributes;
        this.profilePic = profilePic;
        this.gallery = gallery;
        this.coins = coins;
        this.subscriptionType = subscriptionType;
        this.subscriptionPaymentDate = subscriptionPaymentDate;
        this.views = views;
        this.reputation = reputation;
        this.ranking = ranking;
        this.validated = validated;
        this.uniqueViews = uniqueViews;
        this.createdAt = createdAt;
        this.city = city;
        this.category = category;
        this.featured = featured;
        this.reviews = reviews;
        this.contests = contests;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAssociatedUser() {
        return associatedUser;
    }

    public void setAssociatedUser(String associatedUser) {
        this.associatedUser = associatedUser;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public ArrayList<String> getGallery() {
        return gallery;
    }

    public void setGallery(ArrayList<String> gallery) {
        this.gallery = gallery;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(int subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public Timestamp getSubscriptionPaymentDate() {
        return subscriptionPaymentDate;
    }

    public void setSubscriptionPaymentDate(Timestamp subscriptionPaymentDate) {
        this.subscriptionPaymentDate = subscriptionPaymentDate;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public ArrayList<String> getUniqueViews() {
        return uniqueViews;
    }

    public void setUniqueViews(ArrayList<String> uniqueViews) {
        this.uniqueViews = uniqueViews;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public ArrayList<String> getContests() {
        return contests;
    }

    public void setContests(ArrayList<String> contests) {
        this.contests = contests;
    }
}
