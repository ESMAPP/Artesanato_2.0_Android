package pt.cm_vila_do_conde.artesanato_2.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import pt.cm_vila_do_conde.artesanato_2.model.User;

public class AuthRepository {
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private CollectionReference usersRef = rootRef.collection("users");

    public MutableLiveData<User> firebaseSignInWithGoogle(AuthCredential googleAuthCredential) {
        MutableLiveData<User> authenticatedUserMutableLiveData = new MutableLiveData<>();

        firebaseAuth.signInWithCredential(googleAuthCredential)
                .addOnCompleteListener(authTask -> {
                    if (authTask.isSuccessful()) {
                        boolean isNewUser = authTask.getResult()
                                .getAdditionalUserInfo()
                                .isNewUser();
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                        Log.d("Test", "Firebase yo");
                        if (firebaseUser != null) {
                            String uid = firebaseUser.getUid();
                            String name = firebaseUser.getDisplayName();
                            String email = firebaseUser.getEmail();
                            User user = new User(uid, name, email);
                            user.isNew = isNewUser;
                            authenticatedUserMutableLiveData.setValue(user);
                            Log.i("Test", "Testerino");
                        }
                    } else {
                        Log.d("Test ", authTask.getException().getMessage());
                    }
                });

        return authenticatedUserMutableLiveData;
    }

    public MutableLiveData<User> createUserInFirestoreIfNotExists(User authenticatedUser) {
        System.out.println("Started trying to create user");
        MutableLiveData<User> newUserMutableLiveData = new MutableLiveData<>();
        DocumentReference uidRef = usersRef.document(authenticatedUser.uid);

        uidRef.get().addOnCompleteListener(uidTask -> {
            if (uidTask.isSuccessful()) {
                DocumentSnapshot document = uidTask.getResult();
                System.out.println(uidTask.getResult());
                if (document.exists()) {
                    uidRef.set(authenticatedUser).addOnCompleteListener(userCreationTask -> {
                        if (userCreationTask.isSuccessful()) {
                            authenticatedUser.isCreated = true;
                            newUserMutableLiveData.setValue(authenticatedUser);
                        } else {
                            System.out.println("Didnt work bro");
                        }
                    });
                } else {
                    newUserMutableLiveData.setValue( authenticatedUser);
                }
            } else {
                System.out.println("didnt work but less nested");
            }
        });

        return newUserMutableLiveData;
    }
}
