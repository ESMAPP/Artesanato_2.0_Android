package pt.cm_vila_do_conde.artesanato_2.view;

import android.os.Bundle;
import android.view.LayoutInflater;
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
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.FragmentArtisanPageAdapter;
import pt.cm_vila_do_conde.artesanato_2.adapter.ProfileAdapter;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentProfileBinding;
import pt.cm_vila_do_conde.artesanato_2.model.User;
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
        //setupTabAdapter();
        initUserViewModel();
        handleInicialUiState();
        setupTabAdapter();
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

    private void handleInicialUiState(){

        sharedUserViewModel.getUserLiveData().observe(getViewLifecycleOwner(), this::updateUI);
    }

    private void updateUI(User user) {

        binding.profileName.setText(user.getName());
        binding.profileRanking.setText(String.valueOf(user.getRanking()));
        binding.profileReputation.setText(String.valueOf(user.getReputation()));
        if (user.getProfilePic().isEmpty()) {

            Picasso.get().load(R.drawable.logo_i)
                    .transform(new CropCircleTransformation())
                    .into(binding.profilePic);
        }
        else {
            Picasso.get().load(user.getProfilePic())
                    .placeholder(R.drawable.logo_i)
                    .transform(new CropCircleTransformation())
                    .into(binding.profilePic);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.editProfile:
                navController.navigate(R.id.action_profileFragment_to_profileEditFragment);
                return true;
            case R.id.signOut:
                sharedUserViewModel.signOut();
                navController.navigate(R.id.homeFragment);
                return true;
            default:
                return false;
        }
    }
    public void goBack() {
        navController.popBackStack();
    }

    private void gotToEditProfile() {
        Navigation.findNavController(requireView()).navigate(R.id.action_profileFragment_to_profileEditFragment);
    }

    public void setupTabAdapter() {
        ViewPager artisanViewPager = binding.profileViewPager;
        artisanViewPager.setAdapter(new ProfileAdapter(getChildFragmentManager()));
        TabLayout tabs = binding.innerNavBar;
        tabs.setupWithViewPager(binding.profileViewPager);
    }
}
