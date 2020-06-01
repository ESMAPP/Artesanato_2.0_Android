package pt.cm_vila_do_conde.artesanato_2.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import pt.cm_vila_do_conde.artesanato_2.databinding.ActivityHomeBinding;
import pt.cm_vila_do_conde.artesanato_2.model.User;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    User user;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initBinding();
        binding.btnProfile.setOnClickListener(v -> goToProfile(user));
        binding.logoutTestBtn.setOnClickListener(v -> {
            firebaseAuth.signOut();
            Intent intent = new Intent(HomeActivity.this, AuthActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void initBinding() {
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    private User getUserFromIntent() {
        return (User) getIntent().getSerializableExtra("user");
    }

    private void goToProfile(User user) {
        Intent intent = new Intent(HomeActivity.this, ProfileBadgesActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }


}
