package pt.cm_vila_do_conde.artesanato_2.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

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
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentSigninBinding;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentSigninBinding;
import pt.cm_vila_do_conde.artesanato_2.model.User;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.AuthViewModel;

public class SignInFragment extends Fragment {
    private static final int RC_SIGN_IN = 30;
    private GoogleSignInClient googleSignInClient;
    private AuthViewModel authViewModel;
    private FragmentSigninBinding binding;
    private CallbackManager callbackManager = CallbackManager.Factory.create();
    private String TAG = "AUTH_FRAGMENT";

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSigninBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFacebookSignInButton();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initGoogleSignInButton();
        initAuthViewModel();
        initGoogleSignInClient();
        initSubmitButton();
        initCustomFacebookButton();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount googleSignInAccount = task.getResult(ApiException.class);
                if (googleSignInAccount != null) {
                    getGoogleAuthCredential(googleSignInAccount);
                }
            } catch (ApiException e) {
                Toast.makeText(requireContext(), Objects.requireNonNull(e.getMessage()), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initGoogleSignInButton() {
        binding.googleBtn.setOnClickListener(v -> GoogleSignIn());
    }

    private void initCustomFacebookButton() {
        binding.facebookBtn.setOnClickListener(v -> binding.fbLoginBtnHidden.performClick());
    }

    private void initSubmitButton() {
        binding.btnSubmit.setOnClickListener(v -> signInWithEmail());
    }

    private void initFacebookSignInButton() {
        LoginButton loginButton = binding.fbLoginBtnHidden;
        loginButton.setFragment(this);
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
            }

            @Override
            public void onError(FacebookException error) {
                Log.e(TAG, "facebook:onError");
                Toast.makeText(requireContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }

    private void initAuthViewModel() {
        authViewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);
    }

    private void signInWithEmail() {
        String emailInput = binding.inputEmail.getText().toString();
        String passwordInput = binding.inputPassword.getText().toString();
        authViewModel.signInWithEmail(emailInput, passwordInput);
        authViewModel.authenticatedUserLiveData.observe(getViewLifecycleOwner(), authenticatedUser ->{
            if(authenticatedUser != null){
                goToMainActivity();
            }
        });
    }

    private void initGoogleSignInClient() {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), googleSignInOptions);
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
                goToMainActivity();
            }
        });
    }


    private void signInWithFacebookAccessToken(AccessToken facebookAccessToken) {
        authViewModel.signInWithFacebook(facebookAccessToken);
        authViewModel.authenticatedUserLiveData.observe(this, authenticatedUser -> {
            if (authenticatedUser.isNew()) {
                createNewUser(authenticatedUser);
            } else {
                goToMainActivity();
            }
        });
    }

    private void createNewUser(User authenticatedUser) {
        authViewModel.createUser(authenticatedUser);
        authViewModel.createdUserLiveData.observe(this, user -> {
            if (user.isCreated()) {
                Toast.makeText(requireActivity(), "User Created", Toast.LENGTH_LONG).show();
            }
            goToMainActivity();
        });
    }

    public void goToMainActivity() {
        Navigation.findNavController(requireView()).navigate(R.id.action_authActivity_to_homeFragment);
    }

}
