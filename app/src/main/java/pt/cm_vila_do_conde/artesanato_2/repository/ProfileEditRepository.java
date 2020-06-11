package pt.cm_vila_do_conde.artesanato_2.repository;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.MutableData;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import pt.cm_vila_do_conde.artesanato_2.model.User;

public class ProfileEditRepository {

    private String TAG = "EDIT_REPOSITORY";
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private CollectionReference usersRef = rootRef.collection("users");
    private StorageReference storageRef = FirebaseStorage.getInstance().getReference();

    public MutableLiveData <User> getUserIdFromFirebase() {

        MutableLiveData<User> userFromFireBase = new MutableLiveData<>();

        FirebaseUser authenticatedUser = firebaseAuth.getCurrentUser();

        usersRef.document(authenticatedUser.getUid()).get().addOnCompleteListener(task -> {

           if(task.isSuccessful()){

               DocumentSnapshot documentSnapshot = task.getResult();

               if ( documentSnapshot.exists()) {

                   User user = documentSnapshot.toObject(User.class);

                   userFromFireBase.setValue(user);
               }
           }
        });



        return userFromFireBase;
    }


}
