package pt.cm_vila_do_conde.artesanato_2.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.model.User;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.AuthViewModel;

public class AuthActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 30;
    GoogleSignInClient googleSignInClient;
    AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        initGoogleSignInButton();
        initAuthViewModel();
        initGoogleSignInClient();
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
                    Toast.makeText(AuthActivity.this, "1", Toast.LENGTH_SHORT).show();
                }
            } catch (ApiException e) {
                Toast.makeText(AuthActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Error", e.getMessage());
            }
        }
    }

    private void initGoogleSignInButton() {
        ImageButton googleSignInButton = findViewById(R.id.google_btn);
        googleSignInButton.setOnClickListener((v -> GoogleSignIn()));
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

    private void getGoogleAuthCredential(GoogleSignInAccount googleSignInAccount){
        String googleToken = googleSignInAccount.getIdToken();
        AuthCredential googleAuthCredential = GoogleAuthProvider
                .getCredential(googleToken, null);
        signInWithGoogleAuthcredential(googleAuthCredential);
    }

    private void signInWithGoogleAuthcredential(AuthCredential googleAuthCredential){
        authViewModel.signInWithGoogle(googleAuthCredential);
        authViewModel.authenticatedUserLiveData.observe(this, authenticatedUser -> {
            if(authenticatedUser.isNew){
                System.out.println("is new");
                createNewUser(authenticatedUser);
            } else {
                goToMainActivity(authenticatedUser);
            }
        });
    }

    private void createNewUser(User authenticatedUser){
        authViewModel.createUser(authenticatedUser);
        authViewModel.createdUserLiveData.observe(this, user ->{
            if(user.isCreated){
                Toast.makeText(AuthActivity.this, user.name, Toast.LENGTH_SHORT).show();
            }
            goToMainActivity(user);
        });
    }

    public void goToMainActivity(User user){
        Intent intent = new Intent(AuthActivity.this, MainActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }
}
