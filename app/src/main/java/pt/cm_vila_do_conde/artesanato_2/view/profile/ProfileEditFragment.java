package pt.cm_vila_do_conde.artesanato_2.view.profile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.io.InputStream;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentProfileEditBinding;
import pt.cm_vila_do_conde.artesanato_2.model.User;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.ProfileViewModel;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.SharedUserViewModel;


public class ProfileEditFragment extends Fragment {
    private static final int PICK_IMAGE = 1;

    private FragmentProfileEditBinding binding;
    private NavController navController;

    private ProfileViewModel profileViewModel;
    private SharedUserViewModel sharedUserViewModel;

    private Bitmap photo;

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
        initProfileViewModel();
        handleInitialUiState();
        initPickPictureBtn();
        initSubmitBtn();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream;
                imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                photo = BitmapFactory.decodeStream(imageStream);
                Picasso.get().load(imageUri)
                        .transform(new CropCircleTransformation())
                        .into(binding.profilePic);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    private void initNavController() {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
    }

    private void initSubmitBtn() {
        binding.btnSave.setOnClickListener(v -> submitProfileChanges());
    }

    private void initBackBtn() {
        binding.btnBack.setOnClickListener(v -> navController.popBackStack());
    }

    private void initProfileViewModel() {
        profileViewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);
    }

    private void initUserViewModel() {
        sharedUserViewModel = new ViewModelProvider(requireActivity()).get(SharedUserViewModel.class);
    }

    private void handleInitialUiState() {
        sharedUserViewModel.getUserLiveData().observe(getViewLifecycleOwner(), this::updateUserData);
    }

    private void initPickPictureBtn(){
        binding.textEditPicture.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem de perfil"), PICK_IMAGE);
        });
    }

    private void submitProfileChanges() {
            sharedUserViewModel.getUserLiveData().observe(getViewLifecycleOwner(), currentUser -> {
                String userName = binding.inputEditName.getText().toString();
                String password = binding.inputEditPassword.getText().toString();
                profileViewModel.updateProfile(userName, password, photo, currentUser);
                profileViewModel.getEditSuccess().observe(getViewLifecycleOwner(), success -> {
                    if(success) {
                        Toast.makeText(requireContext(), "Perfil editado com sucesso!", Toast.LENGTH_LONG).show();
                        navController.navigate(R.id.action_profileEditFragment_to_profileFragment);
                    }

                });
            });
    }

    private boolean validateInputs() {
        boolean hasErrors = false;

        if (binding.inputEditName.getText().toString().isEmpty()) {
            hasErrors = true;
            binding.inputEditName.setError("O nome não poder estar vazio");
        }
        if (!binding.inputEditPassword.getText().toString().isEmpty()) {
            if (!binding.inputEditPassword.getText().toString()
                    .equals(binding.inputEditConfirmPassword.getText().toString())) {
                hasErrors = true;
                binding.inputEditPassword.setError("As passwords não coincidem");
                binding.inputEditConfirmPassword.setError("As passwords não coincidem");
            }
        }

        return hasErrors;
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
