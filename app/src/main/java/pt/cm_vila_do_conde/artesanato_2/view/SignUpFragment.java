package pt.cm_vila_do_conde.artesanato_2.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentSignupBinding;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.AuthViewModel;


public class SignUpFragment extends Fragment {
    private String TAG = "SIGN_UP";

    private FragmentSignupBinding binding;
    private AuthViewModel authViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSignupBinding.inflate(inflater, container, false);
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
        Log.d(TAG, "sign up");

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
        String err = "Este campo tem de estar preenchido.";
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

        if (inputPassword.equals(inputConfirmPassword) == false) {
            Log.d(TAG, inputPassword);
            Log.d(TAG, inputConfirmPassword);

            err = "As passwords tem de ser idênticas";
            binding.inputConfirmPassword.setError(err);
            isValid = false;
        }

        return isValid;
    }

    private void goToMainActivity() {
        Navigation.findNavController(requireView()).navigate(R.id.action_authActivity_to_homeFragment);
    }
}
