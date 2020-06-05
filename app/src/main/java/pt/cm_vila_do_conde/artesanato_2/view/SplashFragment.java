package pt.cm_vila_do_conde.artesanato_2.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;


import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.model.User;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.SplashViewModel;

public class SplashFragment extends Fragment {
    private SplashViewModel splashViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initSplashViewModel() {
        splashViewModel = new ViewModelProvider(this).get(SplashViewModel.class);
    }

    private void checkIfUserIsAuthenticated() {
        splashViewModel.checkIfUserIsAuthenticated();
        splashViewModel.isUserAuthenticatedLiveData.observe(getViewLifecycleOwner(), user -> {
            if (!user.isAuthenticated()) {
                goToAuth();
            } else {
                getUserFromDatabase(user.getUid());
            }
        });
    }

    private void getUserFromDatabase(String uid) {
        splashViewModel.setUid(uid);
        splashViewModel.userLiveData.observe(getViewLifecycleOwner(), this::goToHome);
    }

    private void goToAuth(){
        // Navigation.findNavController(requireView()).navigate(R.id.action_splashFragment_to_authActivity);
    }

    private void goToHome(User user){
        // Navigation.findNavController(requireView()).navigate(R.id.action_splashFragment_to_homeFragment);
    }
}
