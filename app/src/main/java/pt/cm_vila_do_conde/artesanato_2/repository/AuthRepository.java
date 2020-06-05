package pt.cm_vila_do_conde.artesanato_2.repository;

import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.common.api.ApiException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
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
    private String TAG = "AUTH_REPOSITORY";
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
                            String profile_pic = firebaseUser.getPhotoUrl().toString();
                            User user = new User(uid, name, email, profile_pic);
                            user.setNew(isNewUser);
                            authenticatedUserMutableLiveData.setValue(user);
                        }
                    } else {
                        Log.d(TAG, authTask.getException().getMessage());
                    }
                });

        return authenticatedUserMutableLiveData;
    }

    public MutableLiveData<User> firebaseSignInWithFacebook(AccessToken facebookAccessToken){
        MutableLiveData<User> authenticatedUserMutableLiveData = new MutableLiveData<>();

        AuthCredential facebookAuthCredential = FacebookAuthProvider.getCredential(facebookAccessToken.getToken());
        firebaseAuth.signInWithCredential(facebookAuthCredential)
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
                            String profile_pic = firebaseUser.getPhotoUrl().toString();
                            User user = new User(uid, name, email, profile_pic);
                            user.setNew(isNewUser);
                            authenticatedUserMutableLiveData.setValue(user);
                        }
                    } else {
                        Log.d(TAG, authTask.getException().getMessage());
                    }
                });
        return authenticatedUserMutableLiveData;
    }

    public MutableLiveData<User> firebaseSignInWithEmail(String email, String password) {
        MutableLiveData<User> authenticatedUserMutableLiveData = new MutableLiveData<>();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(authTask -> {
                    if(authTask.isSuccessful()){
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                        if (firebaseUser != null) {
                            String uid = firebaseUser.getUid();
                            String name = firebaseUser.getDisplayName();
                            String firebaseUserEmail = firebaseUser.getEmail();
                            String profile_pic = firebaseUser.getPhotoUrl().toString();
                            User user = new User(uid, name, firebaseUserEmail, profile_pic);
                            authenticatedUserMutableLiveData.setValue(user);
                        }
                    } else {
                        Log.w(TAG, "signInWithEmail:failure", authTask.getException());
                    }
                });
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


    public MutableLiveData<User> signOut() {
        MutableLiveData<User> signOutUserLiveData = new MutableLiveData<>();
        firebaseAuth.signOut();
        LoginManager.getInstance().logOut();
        signOutUserLiveData.setValue(null);
        return signOutUserLiveData;
    }

}
