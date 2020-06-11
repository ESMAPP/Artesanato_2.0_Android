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
    public final MutableLiveData<AuthenticationState> authenticationState =
            new MutableLiveData<>();
    public LiveData<User> authenticatedUserLiveData;
    public LiveData<User> createdUserLiveData;
    private AuthRepository authRepository;

    public AuthViewModel(Application application) {
        super(application);
        authRepository = new AuthRepository();
    }

    public void signInWithEmail(String email, String password) {
        authenticatedUserLiveData = authRepository.firebaseSignInWithEmail(email, password);
        manageAuthState();
    }

    public void signInWithGoogle(AuthCredential googleAuthCredential) {
        authenticatedUserLiveData = authRepository.firebaseSignInWithGoogle(googleAuthCredential);
        manageAuthState();
    }

    public void signInWithFacebook(AccessToken facebookAccessToken) {
        authenticatedUserLiveData = authRepository.firebaseSignInWithFacebook(facebookAccessToken);
        manageAuthState();
    }

    public void checkIfUserIsAuthenticated() {
        authenticatedUserLiveData = authRepository
                .checkIfUserIsAuthenticatedInFirebase();
        manageAuthState();
    }

    public void setUid(String uid) {
        authenticatedUserLiveData = authRepository.addUserToLiveData(uid);
        manageAuthState();
    }

    public void signUp(String name, String email, String password) {
        authenticatedUserLiveData = authRepository.signUpNewUser(name, email, password);
        authenticationState.setValue(AuthenticationState.AUTHENTICATED);
    }

    public void signOut() {
        authenticatedUserLiveData = authRepository.signOut();
        manageAuthState();
    }

    public void createUser(User authenticatedUser) {
        createdUserLiveData = authRepository.createUserInFirestoreIfNotExists(authenticatedUser);
    }

    private void manageAuthState() {
        authenticatedUserLiveData.observeForever(user -> {
            if (user == null) {
                authenticationState.setValue(AuthenticationState.UNAUTHENTICATED);
            } else {
                if (user.getError() != null) {
                    authenticationState.setValue(AuthenticationState.INVALID_AUTHENTICATION);
                } else {
                    if (user.getType() == 2) {
                        authenticationState.setValue(AuthenticationState.ARTISAN_AUTHENTICATED);
                    } else if (user.getType() == 3) {
                        authenticationState.setValue(AuthenticationState.AUTHENTICATED);
                    }
                }
            }
        });
    }

    public enum AuthenticationState {
        UNAUTHENTICATED,        // Initial state, the user needs to authenticate
        AUTHENTICATED,          // The user has authenticated successfully
        ARTISAN_AUTHENTICATED, // The user has authenticated successfully and is artisan
        INVALID_AUTHENTICATION  // Authentication failed
    }
          
    public void createUserSignUp() {
        //createdUserLiveData = authRepository.createUserInFirestoreIfNotExists();
    }
}
