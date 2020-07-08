package pt.cm_vila_do_conde.artesanato_2.view.profile;

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

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.ProfileAdapter;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentProfileBinding;
import pt.cm_vila_do_conde.artesanato_2.model.User;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.SharedUserViewModel;


public class ProfileFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {
    private String TAG = "PROFILE";

    private FragmentProfileBinding binding;
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
        initNavController();
        initBackBtn();
        initExtraBtn();
        setupTabAdapter();
        initUserViewModel();
        handleInitialUiState();
    }

    private void initNavController() {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
    }

    private void initBackBtn() {
        binding.btnBack.setOnClickListener(v -> navController.popBackStack());
    }

    private void initExtraBtn() {
        binding.btnExtra.setOnClickListener(this::showPopup);
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(requireContext(), v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.profile_menu);
        popup.show();
    }

    public void setupTabAdapter() {
        binding.viewPagerProfile.setAdapter(new ProfileAdapter(getChildFragmentManager()));
        binding.innerNavBar.setupWithViewPager(binding.viewPagerProfile);
    }

    // TODO: add go to artisan page if user is artisan
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_profile:
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

    private void initUserViewModel() {
        sharedUserViewModel = new ViewModelProvider(requireActivity()).get(SharedUserViewModel.class);
    }

    private void handleInitialUiState() {
        sharedUserViewModel.getUserLiveData().observe(getViewLifecycleOwner(), this::updateUI);
    }

    private void updateUI(User user) {
        binding.profileName.setText(user.getName());
        binding.profileRanking.setText(String.valueOf(user.getRanking()));
        binding.profileReputation.setText(String.valueOf(user.getReputation()));

        if (user.getProfilePic().isEmpty()) {
            Picasso.get().load(R.drawable.ic_placeholder_user_pic)
                    .transform(new CropCircleTransformation())
                    .into(binding.profilePic);
        } else {
            Picasso.get().load(user.getProfilePic())
                    .placeholder(R.drawable.ic_placeholder_user_pic)
                    .transform(new CropCircleTransformation())
                    .into(binding.profilePic);
        }

        checkUserRanking(user.getRanking());
    }

    private void checkUserRanking(int ranking) {
        int shape = R.drawable.shape_circle_stroke_grey;
        int icon = 0;

        if (ranking == 1) {
            shape = R.drawable.shape_circle_stroke_yellow;
            icon = R.drawable.ic_crown_color;
        }

        if (ranking == 2) {
            shape = R.drawable.shape_circle_stroke_orange;
            icon = R.drawable.ic_second_color;
        }

        if (ranking == 3) {
            shape = R.drawable.shape_circle_stroke_blue;
            icon = R.drawable.ic_third_color;
        }

        binding.profileFrame.setBackgroundResource(shape);
        binding.profileIcon.setBackgroundResource(icon);
    }
}
