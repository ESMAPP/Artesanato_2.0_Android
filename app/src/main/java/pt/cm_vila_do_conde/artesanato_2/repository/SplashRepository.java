package pt.cm_vila_do_conde.artesanato_2.repository;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import pt.cm_vila_do_conde.artesanato_2.model.Review;
import pt.cm_vila_do_conde.artesanato_2.model.User;


public class SplashRepository {
    private User user = new User();

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private CollectionReference usersRef = rootRef.collection("users");

    public MutableLiveData<User> checkIfUserIsAuthenticatedInFirebase() {
        MutableLiveData<User> isUserAuthenticatedMutableLiveData = new MutableLiveData<>();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser == null) {
            user.setAuthenticated(false);
            isUserAuthenticatedMutableLiveData.setValue(user);
        } else {
            user.setUid(firebaseUser.getUid());
            user.setAuthenticated(true);
            isUserAuthenticatedMutableLiveData.setValue(user);
        }

        return isUserAuthenticatedMutableLiveData;
    }

    public MutableLiveData<User> addUserToLiveData(String uid) {
        MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();

        usersRef.document(uid).addSnapshotListener((doc, e) -> {
            if (doc.exists()) {
                User user = doc.toObject(User.class);
                user.setUid(doc.getId());
                user.setAuthenticated(true);
                userMutableLiveData.setValue(user);
            } else {
                User user = new User();
                user.setAuthenticated(false);
                userMutableLiveData.setValue(user);
            }
        });

        return userMutableLiveData;
    }
}
