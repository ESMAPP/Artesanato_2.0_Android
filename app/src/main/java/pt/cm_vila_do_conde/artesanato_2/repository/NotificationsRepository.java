package pt.cm_vila_do_conde.artesanato_2.repository;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import pt.cm_vila_do_conde.artesanato_2.model.Notification;

public class NotificationsRepository {
    private FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private CollectionReference usersRef = rootRef.collection("users");

    public MutableLiveData<List<Notification>> fetchUserNotifications(String userId) {
        MutableLiveData<List<Notification>> notifications = new MutableLiveData<>();

        usersRef.document(userId)
                .collection("notifications").addSnapshotListener((docs, e) -> {
                notifications.postValue(docs.toObjects(Notification.class));
        });

        return notifications;
    }

}
