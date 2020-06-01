package pt.cm_vila_do_conde.artesanato_2.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import pt.cm_vila_do_conde.artesanato_2.adapter.FragmentAuthAdapter;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentAuthBinding;

public class AuthFragment extends Fragment {
    private FragmentAuthBinding binding;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        binding.authViewPager.setAdapter(new FragmentAuthAdapter(getChildFragmentManager()));
        TabLayout tabs = binding.intitialTabs;
        tabs.getTabAt(0).setText("Entrar");
        tabs.setupWithViewPager(binding.authViewPager);
    }
}
