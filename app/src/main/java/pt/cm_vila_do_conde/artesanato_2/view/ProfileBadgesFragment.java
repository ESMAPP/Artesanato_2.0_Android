package pt.cm_vila_do_conde.artesanato_2.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import org.jetbrains.annotations.NotNull;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentProfileBadgesBinding;
import pt.cm_vila_do_conde.artesanato_2.model.User;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.AuthViewModel;


public class ProfileBadgesFragment extends Fragment {
    FragmentProfileBadgesBinding binding;
    AuthViewModel authViewModel;
    NavController navController;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBadgesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        initAuthViewModel();
        handleAuthState();
    }

    private void initAuthViewModel() {
        authViewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);
    }

    private void handleAuthState() {
        authViewModel.authenticationState.observe(getViewLifecycleOwner(),
                authenticationState -> {
                    switch (authenticationState) {
                        case AUTHENTICATED:
                            break;
                        case UNAUTHENTICATED:
                            goToAuth();
                            break;
                    }
                });
    }

    private void goToAuth(){
        navController.navigate(R.id.authActivity);
    }

    private void gotToEditProfile() {
        Navigation.findNavController(requireView()).navigate(R.id.action_profileFragment_to_profileEditFragment);
    }
}
