package pt.cm_vila_do_conde.artesanato_2.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.facebook.AccessToken;
import com.google.firebase.auth.AuthCredential;

import pt.cm_vila_do_conde.artesanato_2.model.User;
import pt.cm_vila_do_conde.artesanato_2.repository.AuthRepository;

public class AuthViewModel extends AndroidViewModel {
    public LiveData<User> authenticatedUserLiveData;
    public LiveData<User> createdUserLiveData;
    private AuthRepository authRepository;

    public enum AuthenticationState  {
        UNAUTHENTICATED,        // Initial state, the user needs to authenticate
        AUTHENTICATED,          // The user has authenticated successfully
        INVALID_AUTHENTICATION  // Authentication failed
    }

    public final MutableLiveData<AuthenticationState> authenticationState =
            new MutableLiveData<>();

    public AuthViewModel(Application application) {
        super(application);
        authRepository = new AuthRepository();
        authenticationState.setValue(AuthenticationState.UNAUTHENTICATED);
    }

    public void signInWithEmail(String email, String password){
        authenticatedUserLiveData = authRepository.firebaseSignInWithEmail(email, password);
        authenticationState.setValue(AuthenticationState.AUTHENTICATED);
    }

    public void signInWithGoogle(AuthCredential googleAuthCredential) {
        authenticatedUserLiveData = authRepository.firebaseSignInWithGoogle(googleAuthCredential);
        authenticationState.setValue(AuthenticationState.AUTHENTICATED);
    }

    public void signInWithFacebook(AccessToken facebookAccessToken) {
        authenticatedUserLiveData = authRepository.firebaseSignInWithFacebook(facebookAccessToken);
        authenticationState.setValue(AuthenticationState.AUTHENTICATED);
    }

    public void signOut() {
        authenticatedUserLiveData = authRepository.signOut();
        authenticationState.setValue(AuthenticationState.UNAUTHENTICATED);
    }

    public void createUser(User authenticatedUser) {
        createdUserLiveData = authRepository.createUserInFirestoreIfNotExists(authenticatedUser);
    }

}
