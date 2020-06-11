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

import org.jetbrains.annotations.NotNull;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentProfileEditBinding;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.EditProfileViewModel;

public class ProfileEditFragment extends Fragment {
    FragmentProfileEditBinding binding;
    private NavController navController;
    private EditProfileViewModel editProfileViewModel;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileEditBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        binding.btnBack.setOnClickListener(v -> goBack());
        initEditProfileViewModel();
    }

    public void goBack() {
        navController.popBackStack();
    }

    public void initEditProfileViewModel() {

        editProfileViewModel = new ViewModelProvider(requireActivity()).get(EditProfileViewModel.class);
    }

    public void getUserIdFromDataBase() {



    };

}
