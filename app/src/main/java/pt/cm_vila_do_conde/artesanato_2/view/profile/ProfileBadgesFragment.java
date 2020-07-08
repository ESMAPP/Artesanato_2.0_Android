package pt.cm_vila_do_conde.artesanato_2.view.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.ProfileBadgesAdapter;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentProfileBadgesBinding;
import pt.cm_vila_do_conde.artesanato_2.model.Badge;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.ProfileViewModel;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.SharedUserViewModel;


public class ProfileBadgesFragment extends Fragment {
    private String TAG = "PROFILE_BADGES";

    private FragmentProfileBadgesBinding binding;
    private NavController navController;
    private SharedUserViewModel sharedUserViewModel;
    private ProfileViewModel profileViewModel;

    private ArrayList<Badge> badges;

    public ProfileBadgesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBadgesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        setBackground();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNavController();
        initSharedUserViewModel();
        initProfileViewModel();
        initRecyclerView();
    }

    private void setBackground() {
        requireActivity().findViewById(R.id.profile_page).setBackgroundResource(R.drawable.bg_3);
    }

    private void initNavController() {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
    }

    private void initSharedUserViewModel() {
        sharedUserViewModel = new ViewModelProvider(requireActivity()).get(SharedUserViewModel.class);
    }

    private void initProfileViewModel() {
        profileViewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);
    }

    private void initRecyclerView() {
        sharedUserViewModel.getUserLiveData().observe(getViewLifecycleOwner(), user -> updateBadges(user.getBadges()));
    }

    private void updateBadges(ArrayList<String> badges) {
        // TODO: get badge icon from id and category
        if (badges != null) {
            Log.d(TAG, badges.get(0));
            RecyclerView recyclerView = binding.badgesList;
            recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 3));
            recyclerView.setAdapter(new ProfileBadgesAdapter(badges));
        }
    }
}
