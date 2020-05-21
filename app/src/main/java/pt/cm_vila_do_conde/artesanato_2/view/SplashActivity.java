package pt.cm_vila_do_conde.artesanato_2.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.model.User;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.SplashViewModel;

public class SplashActivity extends AppCompatActivity {
    SplashViewModel splashViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSplashViewModel();
        checkIfUserIsAuthenticated();
    }

    private void initSplashViewModel() {
        splashViewModel = new ViewModelProvider(this).get(SplashViewModel.class);
    }

    private void checkIfUserIsAuthenticated() {
        splashViewModel.checkIfUserIsAuthenticated();
        splashViewModel.isUserAuthenticatedLiveData.observe(this, user -> {
            System.out.println("MAAMMAMAMAMAMAMAMAMAMAAMMAMAMAMAMAMAMAMAMAMAMMAmsdmjdmasdmjasdjasdjdjjsda");
            if (!user.isAuthenticated) {
                goToAuthActivity();
                finish();
            } else {
                getUserFromDatabase(user.uid);
            }
        });
    }

    private void getUserFromDatabase(String uid) {
        splashViewModel.setUid(uid);
        splashViewModel.userLiveData.observe(this, user -> {
            goToMainActivity(user);
            finish();
        });
    }

    public void goToAuthActivity(){
        Intent intent = new Intent(SplashActivity.this, AuthActivity.class);
        startActivity(intent);
        finish();
    }

    public void goToMainActivity(User user){
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }
}
