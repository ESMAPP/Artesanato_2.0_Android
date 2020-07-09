package pt.cm_vila_do_conde.artesanato_2.view.profile.contests;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.SharedUserViewModel;


public class ProfileContestsRootFragment extends Fragment {
    private String TAG = "PROFILE_CONTESTS";

    private SharedUserViewModel sharedUserViewModel;

    public ProfileContestsRootFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_contests_root, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initSharedUserViewModel();
        handleView();
    }

    private void initSharedUserViewModel() {
        sharedUserViewModel = new ViewModelProvider(requireActivity()).get(SharedUserViewModel.class);
    }

    private void handleView() {
        sharedUserViewModel.getUserLiveData().observe(getViewLifecycleOwner(), user -> {
            if (user.getContests() != null) {
                ProfileContestsFragment profileContestsFragment = new ProfileContestsFragment();
                this.getChildFragmentManager().beginTransaction()
                        .replace(R.id.root_profile_contests, profileContestsFragment, null)
                        .addToBackStack(null)
                        .commit();
            } else {
                ProfileContestsEmptyFragment profileContestsEmptyFragment = new ProfileContestsEmptyFragment();
                this.getChildFragmentManager().beginTransaction()
                        .replace(R.id.root_profile_contests, profileContestsEmptyFragment, null)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
