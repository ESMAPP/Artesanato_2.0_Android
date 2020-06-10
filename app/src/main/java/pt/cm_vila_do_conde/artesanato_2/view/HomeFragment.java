package pt.cm_vila_do_conde.artesanato_2.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import org.jetbrains.annotations.NotNull;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentHomeBinding;
import pt.cm_vila_do_conde.artesanato_2.model.User;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.AuthViewModel;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.HomeViewModel;

public class HomeFragment extends Fragment {
    private String TAG = "HOME_FRAGMENT";
    private FragmentHomeBinding binding;
    private NavController navController;
    private HomeViewModel homeViewModel;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getUserRole();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAuthViewModel();

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        // Init buttons via binding
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
            // authViewModel.signOut();
            goToAuth();
        });
    }

    private void initAuthViewModel() {
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
    }

    private void getUserRole() {
        homeViewModel.getUserRole();
        // Debug
        homeViewModel.userRole
                .observe(getViewLifecycleOwner(), role -> Toast.makeText(requireContext(), role.toString(), Toast.LENGTH_SHORT).show());
    }

    private void goToAuth() {
        navController.navigate(R.id.action_homeFragment_to_authActivity);
    }

    private void goToNotifications() {
        navController.navigate(R.id.action_homeFragment_to_notificationsFragment);
    }

    private void goToProfile() {
        // Check userRole to see which path to take
        homeViewModel.userRole.observe(getViewLifecycleOwner(), role -> {
            if (role != null) {
                if (role == 2) {
                    navController.navigate(R.id.action_homeFragment_to_profileFragment);
                } else if (role == 3) {
                    navController.navigate(R.id.action_homeFragment_to_profileFragment);
                }
            } else {
                navController.navigate(R.id.action_homeFragment_to_authActivity);
            }
        });
    }

    private void goToLocation() {
        navController.navigate(R.id.action_homeFragment_to_fairLocationFragment);
    }

    private void goToEvents() {
        navController.navigate(R.id.action_homeFragment_to_eventListFragment);
    }

    private void goToExplore() {
        navController.navigate(R.id.action_homeFragment_to_artisansListFragment);
    }

    private void goToContests() {
    }

    private void goToFavourites() {
    }

    private void goToRankings() {
        navController.navigate(R.id.action_homeFragment_to_rankingsFragment);
    }

    private void goToInformations() {
    }
}
