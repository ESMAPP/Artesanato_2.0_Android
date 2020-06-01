package pt.cm_vila_do_conde.artesanato_2.view;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import org.jetbrains.annotations.NotNull;

import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentProfileBadgesBinding;
import pt.cm_vila_do_conde.artesanato_2.model.User;


public class ProfileBadgesFragment extends Fragment {
    FragmentProfileBadgesBinding binding;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBadgesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    private void gotToEditProfile (User user) {
        Intent intent = new Intent(requireActivity(), ProfileEditFragment.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}
