package pt.cm_vila_do_conde.artesanato_2.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;



import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.model.User;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.SplashViewModel;

public class SplashFragment extends Fragment {
    SplashViewModel splashViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        requireActivity().setTheme(R.style.AppTheme);
        super.onActivityCreated(savedInstanceState);
        initSplashViewModel();
        checkIfUserIsAuthenticated();
    }

    private void initSplashViewModel() {
        splashViewModel = new ViewModelProvider(this).get(SplashViewModel.class);
    }

    private void checkIfUserIsAuthenticated() {
        splashViewModel.checkIfUserIsAuthenticated();
        splashViewModel.isUserAuthenticatedLiveData.observe(getViewLifecycleOwner(), user -> {
            if (!user.isAuthenticated()) {

                goToAuthActivity();
            } else {
                getUserFromDatabase(user.getUid());
            }
        });
    }

    private void getUserFromDatabase(String uid) {
        splashViewModel.setUid(uid);
        splashViewModel.userLiveData.observe(getViewLifecycleOwner(), user -> {
            goToMainActivity(user);
        });
    }

    public void goToAuthActivity(){
        Intent intent = new Intent(requireActivity(), AuthFragment.class);
        startActivity(intent);
    }

    public void goToMainActivity(User user){
        Intent intent = new Intent(requireActivity(), HomeFragment.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}
