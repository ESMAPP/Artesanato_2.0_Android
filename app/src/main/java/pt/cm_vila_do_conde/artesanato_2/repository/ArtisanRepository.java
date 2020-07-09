package pt.cm_vila_do_conde.artesanato_2.repository;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

import pt.cm_vila_do_conde.artesanato_2.model.Artisan;
import pt.cm_vila_do_conde.artesanato_2.model.Review;
import pt.cm_vila_do_conde.artesanato_2.model.User;


public class ArtisanRepository {
    private String TAG = "ARTISANS_REPOSITORY";

    private FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private CollectionReference artisansRef = rootRef.collection("artisans");
    private CollectionReference usersRef = rootRef.collection("users");

    // TODO implement search and filtering query
    public MutableLiveData<ArrayList<Artisan>> fetchArtisansList(Query query) {
        MutableLiveData<ArrayList<Artisan>> artisansList = new MutableLiveData<>();

        query.addSnapshotListener((task, e) -> {
            ArrayList<Artisan> fetchedArtisans = new ArrayList<>();
            if (task != null) {
                for (DocumentSnapshot doc : task.getDocuments()) {
                    Artisan artisan = doc.toObject(Artisan.class);
                    artisan.setUid(doc.getId());
                    System.out.println(artisan.getUid());
                    fetchedArtisans.add(artisan);
                }
                artisansList.setValue(fetchedArtisans);
            }
        });

        return artisansList;
    }

    public MutableLiveData<Artisan> fetchArtisanById(String id) {
        MutableLiveData<Artisan> artisan = new MutableLiveData<>();

        artisansRef.document(id).addSnapshotListener((doc, e) -> {
            Artisan fetchedArtisan = doc.toObject(Artisan.class);
            fetchedArtisan.setUid(doc.getId());
           /* if (fetchedArtisan.getReviews() != null) {
                System.out.println("GOt here");
                for (Review review : fetchedArtisan.getReviews()) {
                    usersRef.document(review.getUserId()).get()
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    User user = task.getResult().toObject(User.class);
                                    review.setUserInfo(user);
                                }
                            });
                }
            }*/
            artisan.setValue(fetchedArtisan);
        });

        return artisan;
    }

    public MutableLiveData<List<Review>> fetchReviews(String artisanId) {
        MutableLiveData<List<Review>> fetchedReviews = new MutableLiveData<>();

        artisansRef.document(artisanId).collection("reviews")
                .addSnapshotListener((task, e) -> {
                    List<Review> tempReviews = new ArrayList<>();
                    for (DocumentSnapshot doc : task.getDocuments()) {
                        Review review = doc.toObject(Review.class);
                        review.setId(doc.getId());
                        usersRef.document(review.getUserId()).addSnapshotListener((userTask, err) -> {
                            User user = userTask.toObject(User.class);
                            review.setUserInfo(user);
                        });
                        tempReviews.add(review);
                    }
                    fetchedReviews.setValue(tempReviews);
                });

        return fetchedReviews;
    }

    public void submitReview(String text, String userId, String artisanId) {
        Review review = new Review(userId, text);
        artisansRef.document(artisanId).collection("reviews").add(review);
    }

    public void likeReview(User user, Artisan artisan, Review review) {
        if(review.getLikes().contains(user.getUid())) {
            artisansRef.document(artisan.getUid())
                    .collection("reviews")
                    .document(review.getId()).update("likes", FieldValue.arrayRemove(user.getUid()));
        } else {
            artisansRef.document(artisan.getUid())
                    .collection("reviews")
                    .document(review.getId()).update("likes", FieldValue.arrayUnion(user.getUid()));
        }
    }
}
