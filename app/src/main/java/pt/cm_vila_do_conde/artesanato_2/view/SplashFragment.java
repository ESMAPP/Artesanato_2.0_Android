package pt.cm_vila_do_conde.artesanato_2.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentSplashBinding;
import pt.cm_vila_do_conde.artesanato_2.model.User;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.SharedUserViewModel;

public class SplashFragment extends Fragment {
    private SharedUserViewModel sharedUserViewModel;
    private NavController navController;
    private FragmentSplashBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSplashBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /*@Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        initUserViewModel();
        checkIfUserIsAuthenticated();
    }

    private void initUserViewModel() {
        sharedUserViewModel = new ViewModelProvider(requireActivity()).get(SharedUserViewModel.class);
    }

    private void checkIfUserIsAuthenticated() {
        sharedUserViewModel.checkIfUserIsAuthenticated();
        sharedUserViewModel.getUserLiveData().observe(getViewLifecycleOwner(), user -> {
            if (user.isAuthenticated()) getUserFromDatabase(user.getUid());
            else goToHome();
        });
    }

    private void getUserFromDatabase(String uid) {
        sharedUserViewModel.setUid(uid);
        sharedUserViewModel.getUserLiveData().observe(getViewLifecycleOwner(), user -> goToHome());
    }

    private void goToAuth() {
        // Navigation.findNavController(requireView()).navigate(R.id.action_splashFragment_to_authActivity);
    }

    *//*private void goToHome() {
        navController.navigate(R.id.action_splashFragment_to_homeFragment);
    }*/
}
