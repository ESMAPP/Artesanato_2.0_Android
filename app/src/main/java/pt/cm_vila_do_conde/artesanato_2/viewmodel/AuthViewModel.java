package pt.cm_vila_do_conde.artesanato_2.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.AuthCredential;

import pt.cm_vila_do_conde.artesanato_2.model.User;
import pt.cm_vila_do_conde.artesanato_2.repository.AuthRepository;

public class AuthViewModel extends AndroidViewModel {
    public LiveData<User> authenticatedUserLiveData;
    public LiveData<User> createdUserLiveData;
    private AuthRepository authRepository;

    public AuthViewModel(Application application) {
        super(application);
        authRepository = new AuthRepository();
    }

    public void signInWithGoogle(AuthCredential googleAuthCredential) {
        authenticatedUserLiveData = authRepository.firebaseSignInWithGoogle(googleAuthCredential);
    }

    public void createUser(User authenticatedUser) {
        createdUserLiveData = authRepository.createUserInFirestoreIfNotExists(authenticatedUser);
    }
}
