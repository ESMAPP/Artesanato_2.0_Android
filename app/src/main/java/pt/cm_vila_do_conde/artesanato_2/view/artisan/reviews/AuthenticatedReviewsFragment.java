package pt.cm_vila_do_conde.artesanato_2.view.artisan.reviews;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.ReviewListAdapter;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentAuthenticatedReviewsBinding;
import pt.cm_vila_do_conde.artesanato_2.model.User;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.ArtisanPageViewModel;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.SharedUserViewModel;

public class AuthenticatedReviewsFragment extends Fragment {
    private static final String TAG = "AUTHENTICATED_REVIEWS_FRAGMENT";
    private FragmentAuthenticatedReviewsBinding binding;
    private NavController navController;

    private ArtisanPageViewModel artisanPageViewModel;
    private SharedUserViewModel sharedUserViewModel;

    public AuthenticatedReviewsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        initRecyclerView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAuthenticatedReviewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initArtisanViewModel();
        initSharedUserViewModel();
        initNavController();
        initSubmitBtn();
    }

    private void initArtisanViewModel() {
        artisanPageViewModel = new ViewModelProvider(requireActivity()).get(ArtisanPageViewModel.class);
    }

    private void initSharedUserViewModel(){
        sharedUserViewModel = new ViewModelProvider(requireActivity()).get(SharedUserViewModel.class);
    }


    private void initNavController() {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
    }

    private void initSubmitBtn() {
        binding.commentSubmitBtn.setOnClickListener(v -> {
            sharedUserViewModel.getUserLiveData().observe(getViewLifecycleOwner(), this::submitComment);
        });
    }

    private void submitComment(User user){
        String text = binding.commentInput.getText().toString();
        String userId = user.getUid();
        if(!text.isEmpty()){
            artisanPageViewModel.getArtisan().observe(getViewLifecycleOwner(), artisan -> {
                artisanPageViewModel.submitReview(text, userId, artisan.getUid());
            });
        }
    }

    private void initRecyclerView() {
        artisanPageViewModel.getReviews().observe(getViewLifecycleOwner(), reviews -> {
            Log.d(TAG, String.valueOf(reviews.size()));
            RecyclerView recyclerView = binding.reviewsRecylcer;
            recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
            recyclerView.setAdapter(new ReviewListAdapter(reviews));
        });
    }
}