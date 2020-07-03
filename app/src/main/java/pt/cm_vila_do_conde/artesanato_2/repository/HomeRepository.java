package pt.cm_vila_do_conde.artesanato_2.repository;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.Date;

import pt.cm_vila_do_conde.artesanato_2.model.Artisan;
import pt.cm_vila_do_conde.artesanato_2.model.Event;
import pt.cm_vila_do_conde.artesanato_2.utils.SortingHelper;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.ArrayList;

import pt.cm_vila_do_conde.artesanato_2.model.User;
import pt.cm_vila_do_conde.artesanato_2.model.Event;

public class HomeRepository {
    private String TAG = "HOME_REPOSITORY";
    private FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private CollectionReference artisansRef = rootRef.collection("artisans");
    private CollectionReference eventsRef = rootRef.collection("events");
    private SortingHelper sortingHelper = new SortingHelper();

/*    public MutableLiveData<Integer> getUserRole() {
        MutableLiveData<Integer> userType = new MutableLiveData<>();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            usersRef.document(firebaseUser.getUid()).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    User user = document.toObject(User.class);
                    userType.setValue(user.getType());
                } else {
                    Log.e(TAG, task.getException().getLocalizedMessage());
                }
            });
        } else {
            userType.setValue(null);
        }
        return userType;
    }*/

    public MutableLiveData<Event> getUpcomingEvent() {
        MutableLiveData<Event> upcomingEvent = new MutableLiveData<>();
        Timestamp currentTime = new Timestamp(new Date());
        eventsRef.whereGreaterThan("startDate", currentTime)
                .orderBy("startDate", Query.Direction.ASCENDING)
                .limit(1)
                .addSnapshotListener((task, e) -> {
                    for (DocumentSnapshot doc : task.getDocuments()) {
                        if (doc.exists()) {
                            Event event = doc.toObject(Event.class);
                            event.setId(doc.getId());
                            upcomingEvent.setValue(event);
                        } else {
                            Log.d(TAG, "Não existem futuros eventos");
                        }
                    }
                });
        return upcomingEvent;
    }

    public MutableLiveData<Event> getFairEvent() {
        MutableLiveData<Event> fairEvent = new MutableLiveData<>();
        eventsRef.whereEqualTo("category", "fair").addSnapshotListener((task, e) -> {
            for (DocumentSnapshot doc : task.getDocuments()) {
                Event fair = doc.toObject(Event.class);
                fair.setId(doc.getId());
                fairEvent.setValue(fair);
            }
        });
        return fairEvent;
    }

    public MutableLiveData<Artisan> getFeaturedArtisan() {
        MutableLiveData<Artisan> featuredArtisan = new MutableLiveData<>();
        artisansRef.whereLessThanOrEqualTo("subscriptionType", 2).addSnapshotListener((task, e) -> {
            ArrayList<Artisan> artisansList = new ArrayList<>();
            for (DocumentSnapshot doc : task.getDocuments()) {
                Artisan artisan = doc.toObject(Artisan.class);
                artisan.setUid(doc.getId());
                artisansList.add(artisan);
            }
            Artisan randomArtisan = sortingHelper.randomFeaturedArtisan(artisansList);
            featuredArtisan.setValue(randomArtisan);
        });

        return featuredArtisan;
    }

    public MutableLiveData<Event> getFeaturedEvent() {
        MutableLiveData<Event> featuredEvent = new MutableLiveData<>();

        eventsRef.whereEqualTo("featured", true).addSnapshotListener((task, e) -> {

            for (DocumentSnapshot document : task.getDocuments()) {

                Log.d(TAG, document.getData().toString());
                featuredEvent.setValue(document.toObject(Event.class));
            }

        });

        return featuredEvent;
    }
}
