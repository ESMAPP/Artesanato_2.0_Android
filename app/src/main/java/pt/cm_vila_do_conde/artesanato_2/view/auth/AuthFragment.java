package pt.cm_vila_do_conde.artesanato_2.view.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.FragmentAuthAdapter;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentAuthBinding;

public class AuthFragment extends Fragment {
    private FragmentAuthBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAuthBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupTabAdapter();
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        binding.btnBack.setOnClickListener(v -> goBack());
    }

    public void setupTabAdapter() {
        binding.viewPagerAuth.setAdapter(new FragmentAuthAdapter(getChildFragmentManager()));
        TabLayout tabs = binding.innerNavBar;
        tabs.setupWithViewPager(binding.viewPagerAuth);
    }

    public void goBack() {
        navController.popBackStack();
    }
}
