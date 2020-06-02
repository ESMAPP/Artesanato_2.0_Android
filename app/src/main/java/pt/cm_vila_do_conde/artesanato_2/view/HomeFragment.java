package pt.cm_vila_do_conde.artesanato_2.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private String TAG = "HOME_FRAGMENT";
    private FragmentHomeBinding binding;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.btnProfile.setOnClickListener(v -> goToProfile());
        binding.logoutTestBtn.setOnClickListener(v -> {
            firebaseAuth.signOut();
            Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_authActivity);
        });
    }

    private void goToProfile() {
        Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_profileBadgesFragment);
    }


}
