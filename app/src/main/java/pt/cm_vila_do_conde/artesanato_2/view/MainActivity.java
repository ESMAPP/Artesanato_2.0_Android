package pt.cm_vila_do_conde.artesanato_2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.databinding.ActivityMainBinding;
import pt.cm_vila_do_conde.artesanato_2.model.User;

public class MainActivity extends AppCompatActivity {
    GoogleSignInClient googleSignInClient;
    TextView messageTextView;
    public User user;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinging();
        user = getUserFromIntent();
        initGoogleSignInClient();
        initMessageTextView();
        setMessageToMessageTextView(user);
    }

    private void initBinging() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    private User getUserFromIntent(){
        return (User) getIntent().getSerializableExtra("user");
    }

    private void initGoogleSignInClient(){
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
    }

    private void initMessageTextView() {
        messageTextView = findViewById(R.id.login_message);
    }

    private void setMessageToMessageTextView(User user){
        String message = "You are logged in as: " + user.name;
        messageTextView.setText(message);
    }
}
