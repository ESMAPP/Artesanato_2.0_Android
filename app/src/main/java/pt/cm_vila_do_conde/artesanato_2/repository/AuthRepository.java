package pt.cm_vila_do_conde.artesanato_2.repository;

import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.facebook.AccessToken;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import pt.cm_vila_do_conde.artesanato_2.model.User;

public class AuthRepository {
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private CollectionReference usersRef = rootRef.collection("users");
    private StorageReference storageRef = FirebaseStorage.getInstance().getReference();


    public MutableLiveData<User> firebaseSignInWithGoogle(AuthCredential googleAuthCredential) {
        MutableLiveData<User> authenticatedUserMutableLiveData = new MutableLiveData<>();

        firebaseAuth.signInWithCredential(googleAuthCredential)
                .addOnCompleteListener(authTask -> {
                    if (authTask.isSuccessful()) {
                        boolean isNewUser = authTask.getResult()
                                .getAdditionalUserInfo()
                                .isNewUser();
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                        if (firebaseUser != null) {
                            String uid = firebaseUser.getUid();
                            String name = firebaseUser.getDisplayName();
                            String email = firebaseUser.getEmail();
                            Uri avatar = firebaseUser.getPhotoUrl();
                            User user = new User(uid, name, email);
                            user.setNew(isNewUser);
                            authenticatedUserMutableLiveData.setValue(user);
                        }
                    } else {
                        Log.d("Test ", authTask.getException().getMessage());
                    }
                });

        return authenticatedUserMutableLiveData;
    }

    public MutableLiveData<User> firebaseSignInWithFacebook(AccessToken facebookAccessToken){
        MutableLiveData<User> authenticatedUserMutableLiveData = new MutableLiveData<>();


        return authenticatedUserMutableLiveData;
    }


    public MutableLiveData<User> createUserInFirestoreIfNotExists(User authenticatedUser) {
        MutableLiveData<User> newUserMutableLiveData = new MutableLiveData<>();
        DocumentReference uidRef = usersRef.document(authenticatedUser.getUid());
        uidRef.get().addOnCompleteListener(uidTask -> {
            if (uidTask.isSuccessful()) {
                DocumentSnapshot document = uidTask.getResult();
                if (!document.exists()) {
                    uidRef.set(authenticatedUser).addOnCompleteListener(userCreationTask -> {
                        if (userCreationTask.isSuccessful()) {
                            authenticatedUser.setCreated(true);
                            newUserMutableLiveData.setValue(authenticatedUser);
                        } else {
                            System.out.println(userCreationTask.getException().getMessage());
                        }
                    });
                } else {
                    newUserMutableLiveData.setValue(authenticatedUser);
                }
            } else {
                System.out.println(uidTask.getException().getMessage());
            }
        });
        return newUserMutableLiveData;
    }

}
