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

import org.jetbrains.annotations.NotNull;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentHomeBinding;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.AuthViewModel;

public class HomeFragment extends Fragment {
    AuthViewModel authViewModel;
    AuthViewModel.AuthenticationState authenticationState;
    private String TAG = "HOME_FRAGMENT";
    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAuthViewModel();

        binding.btnNotifications.setOnClickListener(v -> goToNotifications());
        binding.btnProfile.setOnClickListener(v -> goToProfile());
        binding.btnFair.setOnClickListener(v -> goToLocation());
        binding.btnEvents.setOnClickListener(v -> goToEvents());
        binding.btnExplore.setOnClickListener(v -> goToExplore());
        binding.btnContests.setOnClickListener(v -> goToContests());
        binding.btnFavourites.setOnClickListener(v -> goToFavourites());
        binding.btnRankings.setOnClickListener(v -> goToRankings());
        binding.btnInfo.setOnClickListener(v -> goToInformations());

        binding.logoutTestBtn.setOnClickListener(v -> {
            authViewModel.signOut();
            goToAuth();
        });
    }

    private void initAuthViewModel() {
        authViewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);
    }

    private void goToAuth() {
        Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_authActivity);
    }

    private void goToNotifications() {
        Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_notificationsFragment);
    }

    private void goToProfile() {
        Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_profileFragment);
    }

    private void goToLocation() {
        Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_fairLocationFragment);
    }

    private void goToEvents() {
        Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_eventListFragment);
    }

    private void goToExplore() {
        Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_artisansListFragment);
    }

    private void goToContests() {
    }

    private void goToFavourites() {
    }

    private void goToRankings() {
        Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_rankingsFragment);
    }

    private void goToInformations() {
    }
}
