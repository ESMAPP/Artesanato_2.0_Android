package pt.cm_vila_do_conde.artesanato_2.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.databinding.ActivityAuthBinding;
import pt.cm_vila_do_conde.artesanato_2.model.User;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.AuthViewModel;

public class AuthActivity extends AppCompatActivity {
    private String TAG = "AUTH_ACTIVITY";
    private static final int RC_SIGN_IN = 30;
    GoogleSignInClient googleSignInClient;

    AuthViewModel authViewModel;
    ActivityAuthBinding binding;
    CallbackManager callbackManager = CallbackManager.Factory.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        initGoogleSignInButton();
        initFacebookSignInButton();
        initAuthViewModel();
        initGoogleSignInClient();
        binding.facebookBtn.setOnClickListener(v -> binding.fbLoginBtnHidden.performClick());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount googleSignInAccount = task.getResult(ApiException.class);
                if (googleSignInAccount != null) {
                    getGoogleAuthCredential(googleSignInAccount);
                }
            } catch (ApiException e) {
                Toast.makeText(AuthActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                Log.d(TAG, e.getMessage());
            }
        }
    }

    private void initBinding() {
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    private void initGoogleSignInButton() {
        binding.googleBtn.setOnClickListener((v -> GoogleSignIn()));
    }

    private void initFacebookSignInButton() {
        LoginButton loginButton = binding.fbLoginBtnHidden;
        loginButton.setPermissions("email", "public_profile");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                signInWithFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.e(TAG, "facebook:onError");
            }

        });
    }

    private void initAuthViewModel() {
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
    }

    private void initGoogleSignInClient() {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
    }

    private void GoogleSignIn() {
        Intent googleSignInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(googleSignInIntent, RC_SIGN_IN);
    }


    private void getGoogleAuthCredential(GoogleSignInAccount googleSignInAccount) {
        String googleToken = googleSignInAccount.getIdToken();
        AuthCredential googleAuthCredential = GoogleAuthProvider
                .getCredential(googleToken, null);
        signInWithGoogleAuthcredential(googleAuthCredential);
    }

    private void signInWithGoogleAuthcredential(AuthCredential googleAuthCredential) {
        authViewModel.signInWithGoogle(googleAuthCredential);
        authViewModel.authenticatedUserLiveData.observe(this, authenticatedUser -> {
            if (authenticatedUser.isNew()) {
                createNewUser(authenticatedUser);
            } else {
                goToMainActivity(authenticatedUser);
            }
        });
    }


    private void signInWithFacebookAccessToken(AccessToken facebookAccessToken) {
        authViewModel.signInWithFacebook(facebookAccessToken);
        authViewModel.authenticatedUserLiveData.observe(this, authenticatedUser -> {
            if (authenticatedUser.isNew()) {
                createNewUser(authenticatedUser);
            } else {
                goToMainActivity(authenticatedUser);
            }
        });
    }

    private void createNewUser(User authenticatedUser) {
        authViewModel.createUser(authenticatedUser);
        authViewModel.createdUserLiveData.observe(this, user -> {
            if (user.isCreated()) {
                Toast.makeText(AuthActivity.this, "User Created", Toast.LENGTH_SHORT).show();
            }
            goToMainActivity(user);
        });
    }

    public void goToMainActivity(User user) {
        Intent intent = new Intent(AuthActivity.this, HomeActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }
}
