package pt.cm_vila_do_conde.artesanato_2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import pt.cm_vila_do_conde.artesanato_2.databinding.ActivityProfileBadgesBinding;
import pt.cm_vila_do_conde.artesanato_2.model.User;


public class ProfileBadgesActivity extends AppCompatActivity {
    ActivityProfileBadgesBinding binding;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initBinding();
        user = getUserFromIntent();
        binding.btnExtra.setOnClickListener(v -> gotToEditProfile(user));
    }

    private void initBinding() {
        binding = ActivityProfileBadgesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    private User getUserFromIntent(){
        return (User) getIntent().getSerializableExtra("user");
    }

    private void gotToEditProfile (User user) {
        Intent intent = new Intent(ProfileBadgesActivity.this, ProfileEditActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }
}
