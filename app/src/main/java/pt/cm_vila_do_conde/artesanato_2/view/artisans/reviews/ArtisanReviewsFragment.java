package pt.cm_vila_do_conde.artesanato_2.view.artisans.reviews;

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
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentArtisanReviewsBinding;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.SharedUserViewModel;

public class ArtisanReviewsFragment extends Fragment {
    private String TAG = "ARTISAN_REVIEWS";

    private FragmentArtisanReviewsBinding binding;
    private SharedUserViewModel sharedUserViewModel;

    public ArtisanReviewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_artisan_reviews, container, false);
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

    /**
     * Checks if user is authenticated and handles
     * fragment selection based on condition
     */
    private void handleView() {
        sharedUserViewModel.getUserLiveData().observe(getViewLifecycleOwner(), user -> {
            if (!user.isAuthenticated()) {
                ArtisanReviewsUnauthenticatedFragment artisanReviewsUnauthenticatedFragment = new ArtisanReviewsUnauthenticatedFragment();
                this.getChildFragmentManager().beginTransaction()
                        .replace(R.id.root_reviews, artisanReviewsUnauthenticatedFragment, null)
                        .addToBackStack(null)
                        .commit();
            } else {
                ArtisanReviewsAuthenticatedFragment artisanReviewsAuthenticatedFragment = new ArtisanReviewsAuthenticatedFragment();
                this.getChildFragmentManager().beginTransaction()
                        .replace(R.id.root_reviews, artisanReviewsAuthenticatedFragment, null)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

}