package pt.cm_vila_do_conde.artesanato_2.view.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentAuthSignUpBinding;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.AuthViewModel;


public class AuthSignUpFragment extends Fragment {
    private String TAG = "SIGN_UP";

    private FragmentAuthSignUpBinding binding;
    private AuthViewModel authViewModel;

    public AuthSignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAuthSignUpBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAuthViewModel();
        initBtnSubmit();
    }

    private void initAuthViewModel() {
        authViewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);
    }

    public void initBtnSubmit() {
        binding.btnSubmit.setOnClickListener(v -> signUp());
    }

    private void signUp() {
        String inputName = binding.inputName.getText().toString();
        String inputEmail = binding.inputEmail.getText().toString();
        String inputPassword = binding.inputPassword.getText().toString();
        String inputConfirmPassword = binding.inputConfirmPassword.getText().toString();

        if (runValidations(inputName, inputEmail, inputPassword, inputConfirmPassword)) {
            authViewModel.signUp(inputName, inputEmail, inputPassword);
            authViewModel.authenticatedUserLiveData.observe(getViewLifecycleOwner(), authenticatedUser -> {
                if (authenticatedUser != null) {
                    goToMainActivity();
                }
            });
        }
    }

    private boolean runValidations(String inputName, String inputEmail, String inputPassword, String inputConfirmPassword) {
        String err = getString(R.string.error_field_empty);
        boolean isValid = true;

        if (inputName.isEmpty()) {
            binding.inputName.setError(err);
            isValid = false;
        }

        if (inputEmail.isEmpty()) {
            binding.inputEmail.setError(err);
            isValid = false;
        }

        if (inputPassword.isEmpty()) {
            binding.inputPassword.setError(err);
            isValid = false;
        }

        if (inputConfirmPassword.isEmpty()) {
            binding.inputConfirmPassword.setError(err);
            isValid = false;
        }

        if (!inputPassword.equals(inputConfirmPassword)) {
            err = getString(R.string.error_field_password);
            binding.inputConfirmPassword.setError(err);
            isValid = false;
        }

        return isValid;
    }

    private void goToMainActivity() {
        Navigation.findNavController(requireView()).navigate(R.id.action_authActivity_to_homeFragment);
    }
}
