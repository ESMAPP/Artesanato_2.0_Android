package pt.cm_vila_do_conde.artesanato_2.repository;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

import pt.cm_vila_do_conde.artesanato_2.model.Artisan;
import pt.cm_vila_do_conde.artesanato_2.model.Badge;
import pt.cm_vila_do_conde.artesanato_2.model.Review;
import pt.cm_vila_do_conde.artesanato_2.model.User;

public class BadgesRepository {

    private String TAG = "BADGES_REPOSITORY";
    private FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private CollectionReference badgesRef = rootRef.collection("badges");
    private CollectionReference usersRef = rootRef.collection("users");



    public MutableLiveData<List<Badge>> fetchBadgesList() {
        MutableLiveData<List<Badge>> badgesList = new MutableLiveData<>();
        badgesRef.addSnapshotListener((task, e) -> {
            List<Badge> fetchedBadges = new ArrayList<>();
            for (DocumentSnapshot doc : task.getDocuments()) {
                Badge badge = doc.toObject(Badge.class);
                badge.setUid(doc.getId());
                System.out.println(badge.getUid());
                fetchedBadges.add(badge);
            }
            badgesList.setValue(fetchedBadges);
        });

        return badgesList;
    }

    public MutableLiveData<Badge> fetchBadgeById(String id) {
        MutableLiveData<Badge> badge = new MutableLiveData<>();

        badgesRef.document(id).addSnapshotListener((doc, e) -> {
            Badge fetchedBadges = doc.toObject(Badge.class);
            fetchedBadges.setUid(doc.getId());
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
            badge.setValue(fetchedBadges);
        });

        return badge;
    }
}
