package pt.cm_vila_do_conde.artesanato_2.view.profile.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.SharedUserViewModel;


public class ProfileActivityRootFragment extends Fragment {
    private String TAG = "PROFILE_ACTIVITY";

    private SharedUserViewModel sharedUserViewModel;

    public ProfileActivityRootFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_activity_root, container, false);
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
            // TODO: get user collection Activity
            /*
            if (user.getActivity() != null) {
                ProfileActivityFragment profileActivityFragment = new ProfileActivityFragment();
                this.getChildFragmentManager().beginTransaction()
                        .replace(R.id.root_activity, profileActivityFragment, null)
                        .addToBackStack(null)
                        .commit();
            } else {*/
                ProfileActivityEmptyFragment profileActivityEmptyFragment = new ProfileActivityEmptyFragment();
                this.getChildFragmentManager().beginTransaction()
                        .replace(R.id.root_profile_activity, profileActivityEmptyFragment, null)
                        .addToBackStack(null)
                        .commit();
            //}
        });
    }
}
