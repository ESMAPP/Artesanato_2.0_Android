package pt.cm_vila_do_conde.artesanato_2.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.FragmentAuthAdapter;
import pt.cm_vila_do_conde.artesanato_2.adapter.ProfileViewPager;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentProfileBinding;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.SharedUserViewModel;


public class ProfileFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {
    FragmentProfileBinding binding;
    private NavController navController;
    private SharedUserViewModel sharedUserViewModel;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUserViewModel();
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        binding.btnExtra.setOnClickListener(this::showPopup);
        binding.btnBack.setOnClickListener(v -> goBack());
    }

    private void initUserViewModel(){
        sharedUserViewModel = new ViewModelProvider(requireActivity()).get(SharedUserViewModel.class);
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(requireContext(), v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.profile_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_profile:
                navController.navigate(R.id.action_profileFragment_to_profileEditFragment);
                return true;
            case R.id.signout:
                sharedUserViewModel.signOut();

                navController.navigate(R.id.homeFragment);
                return true;
            default:
                return false;
        }
    }
/*
    public void setupTabAdapter() {
        binding.profileViewPager.setAdapter(new ProfileViewPager(getChildFragmentManager()));
        TabLayout tabs = binding.profileTabs;
        tabs.setupWithViewPager(binding.profileViewPager);
    }*/

    public void goBack() {
        navController.popBackStack();
    }

    private void gotToEditProfile() {
        Navigation.findNavController(requireView()).navigate(R.id.action_profileFragment_to_profileEditFragment);
    }
}
