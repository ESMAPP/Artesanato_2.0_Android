package pt.cm_vila_do_conde.artesanato_2.view.artisans.contests;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.ArtisanPageViewModel;


public class ArtisanContestsRootFragment extends Fragment {
    private String TAG = "ARTISAN_CONTESTS_ROOT";

    private ArtisanPageViewModel artisanPageViewModel;

    public ArtisanContestsRootFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_artisan_contest_root, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initArtisanViewModel();
        handleView();
    }

    private void initArtisanViewModel() {
        artisanPageViewModel = new ViewModelProvider(requireActivity()).get(ArtisanPageViewModel.class);
    }

    private void handleView() {
        artisanPageViewModel.getArtisan().observe(getViewLifecycleOwner(), artisan -> {
            if (artisan.getContests() != null) {
                ArtisanContestsFragment artisanContestsFragment = new ArtisanContestsFragment();
                this.getChildFragmentManager().beginTransaction()
                        .replace(R.id.root_artisan_contests, artisanContestsFragment, null)
                        .addToBackStack(null)
                        .commit();
            } else {
                ArtisanContestsEmptyFragment artisanContestsEmptyFragment = new ArtisanContestsEmptyFragment();
                this.getChildFragmentManager().beginTransaction()
                        .replace(R.id.root_artisan_contests, artisanContestsEmptyFragment, null)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}