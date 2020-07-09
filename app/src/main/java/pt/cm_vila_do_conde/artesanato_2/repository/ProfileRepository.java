package pt.cm_vila_do_conde.artesanato_2.repository;

import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import pt.cm_vila_do_conde.artesanato_2.model.User;

public class ProfileRepository {
    private String TAG = "PROFILE_REPOSITORY";
    private FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private CollectionReference usersRef = rootRef.collection("users");
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public MutableLiveData<Boolean> updateProfile(String username, String password, Bitmap image, User currentUser) {
        MutableLiveData<Boolean> success = new MutableLiveData<>(false);
        HashMap<String, Object> usersMap = new HashMap<String, Object>();


        if (image != null) {
            StorageReference storageReference = FirebaseStorage.getInstance()
                    .getReference(currentUser.getUid());

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageData = baos.toByteArray();

            storageReference.putBytes(imageData).addOnSuccessListener(task -> {
                storageReference.getDownloadUrl().addOnSuccessListener(imageUrl -> {
                    usersMap.put("name", username);
                    usersMap.put("profilePic", imageUrl.toString());
                    usersRef.document(currentUser.getUid()).update(usersMap);
                    success.postValue(true);
                });
            });
        } else {
            usersMap.put("name", username);
            usersRef.document(currentUser.getUid()).update(usersMap);
            success.postValue(true);
        }


        return success;
    }
}
