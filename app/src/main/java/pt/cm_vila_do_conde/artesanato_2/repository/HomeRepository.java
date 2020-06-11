package pt.cm_vila_do_conde.artesanato_2.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import pt.cm_vila_do_conde.artesanato_2.model.User;

public class HomeRepository {
    private String TAG = "HOME_REPOSITORY";
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private CollectionReference usersRef = rootRef.collection("users");

    public MutableLiveData<Integer> getUserRole(){
        MutableLiveData<Integer> userType = new MutableLiveData<>();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null){
            usersRef.document(firebaseUser.getUid()).get().addOnCompleteListener(task -> {
                if(task.isSuccessful()){
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
    }
}
