package pt.cm_vila_do_conde.artesanato_2.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentSignupBinding;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.AuthViewModel;


public class SignUpFragment extends Fragment {

    private FragmentSignupBinding binding;
    private AuthViewModel AuthViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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
        AuthViewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);
    }

    public void initBtnSubmit() {
        binding.btnSubmit.setOnClickListener(v -> signUp());
    }

    private void signUp() {
        String name = binding.inputName.getText().toString();
        String email = binding.inputEmail.getText().toString();
        String password = binding.inputPassword.getText().toString();
        String confirmPassword = binding.inputConfirmPassword.getText().toString();

        runValidations(name, email, password, confirmPassword);


        // chamar authviewmodel
        // send to database

    }

    private void runValidations(String name, String email, String password, String confirmPassword) {
        String errMsg = "Este campo tem de estar preenchido";

        if (name.isEmpty()) {
            binding.inputName.setError(errMsg);
        }

        if (email.isEmpty()) {
            binding.inputEmail.setError(errMsg);
        }

        if (password.isEmpty()) {
            binding.inputPassword.setError(errMsg);
        }

        if (confirmPassword.isEmpty()) {
            binding.inputConfirmPassword.setError(errMsg);
        }

        if (password != confirmPassword) {
            errMsg = "As passwords não são iguais";

            binding.inputConfirmPassword.setError(errMsg);
        }
    }
}
