package pt.cm_vila_do_conde.artesanato_2.repository;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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


public class UserRepository {
    private String TAG = "USERS_REPOSITORY";

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private CollectionReference usersRef = rootRef.collection("users");

    public MutableLiveData<ArrayList<User>> fetchUsers(Query query) {
        MutableLiveData<ArrayList<User>> usersList = new MutableLiveData<>();

        query.addSnapshotListener((task, e) -> {
            ArrayList<User> fetchedUsers = new ArrayList<>();

            for (DocumentSnapshot doc : task.getDocuments()) {
                User user = doc.toObject(User.class);
                user.setUid(doc.getId());
                fetchedUsers.add(user);
            }

            usersList.setValue(fetchedUsers);
        });

        return usersList;
    }
}
