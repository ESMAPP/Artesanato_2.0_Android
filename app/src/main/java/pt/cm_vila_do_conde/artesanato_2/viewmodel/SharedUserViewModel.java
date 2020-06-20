package pt.cm_vila_do_conde.artesanato_2.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;

import pt.cm_vila_do_conde.artesanato_2.model.User;
import pt.cm_vila_do_conde.artesanato_2.repository.SplashRepository;

public class SharedUserViewModel extends AndroidViewModel {
    public MutableLiveData<User> userLiveData;
    private SplashRepository splashRepository;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public SharedUserViewModel(Application app) {
        super(app);
        splashRepository = new SplashRepository();

    }

    public void checkIfUserIsAuthenticated() {
        userLiveData = splashRepository
                .checkIfUserIsAuthenticatedInFirebase();
    }

    public void setUid(String uid) {
        userLiveData = splashRepository.addUserToLiveData(uid);
    }

    public void signOut() {
        firebaseAuth.signOut();
        userLiveData = null;
    }

    public LiveData<User> getUserLiveData() {
        return userLiveData;
    }
}
