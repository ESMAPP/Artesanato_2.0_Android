package pt.cm_vila_do_conde.artesanato_2.view.profile;

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

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentProfileEditBinding;
import pt.cm_vila_do_conde.artesanato_2.model.User;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.SharedUserViewModel;


public class ProfileEditFragment extends Fragment {
    private FragmentProfileEditBinding binding;
    private NavController navController;
    private SharedUserViewModel sharedUserViewModel;

    public ProfileEditFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileEditBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNavController();
        initBackBtn();
        initUserViewModel();
        handleInitialUiState();
    }

    private void initNavController() {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
    }

    private void initBackBtn() {
        binding.btnBack.setOnClickListener(v -> navController.popBackStack());
    }

    private void initUserViewModel() {
        sharedUserViewModel = new ViewModelProvider(requireActivity()).get(SharedUserViewModel.class);
    }

    private void handleInitialUiState() {
        sharedUserViewModel.getUserLiveData().observe(getViewLifecycleOwner(), this::updateUserData);
    }

    private void updateUserData(@NonNull User user) {
        binding.inputEditName.setText(user.getName());
        binding.inputEditEmail.setText(String.valueOf(user.getEmail()));

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
    }
}
