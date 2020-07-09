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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.ArtisanReviewsAdapter;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentArtisanReviewsAuthenticatedBinding;
import pt.cm_vila_do_conde.artesanato_2.model.User;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.ArtisanPageViewModel;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.SharedUserViewModel;

public class ArtisanReviewsAuthenticatedFragment extends Fragment {
    private static final String TAG = "ARTISAN_AUTHENTICATED_REVIEWS";

    private FragmentArtisanReviewsAuthenticatedBinding binding;
    private NavController navController;
    private ArtisanPageViewModel artisanPageViewModel;
    private SharedUserViewModel sharedUserViewModel;

    public ArtisanReviewsAuthenticatedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "create");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        setBackground();
        initRecyclerView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        binding = FragmentArtisanReviewsAuthenticatedBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");
        //setBackground();
        initNavController();
        initArtisanViewModel();
        initSharedUserViewModel();
        initSendBtn();
    }

    private void setBackground() {
        Log.d(TAG, "color");
        requireActivity().findViewById(R.id.artisan_page).setBackgroundResource(R.drawable.bg_3);
    }

    private void initNavController() {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
    }

    private void initArtisanViewModel() {
        artisanPageViewModel = new ViewModelProvider(requireActivity()).get(ArtisanPageViewModel.class);
    }

    private void initSharedUserViewModel() {
        sharedUserViewModel = new ViewModelProvider(requireActivity()).get(SharedUserViewModel.class);
    }

    private void initSendBtn() {
        binding.btnSend.setOnClickListener(v -> {
            sharedUserViewModel.getUserLiveData().observe(getViewLifecycleOwner(), this::submitComment);
        });
    }

    private void submitComment(User user) {
        String text = binding.inputReview.getText().toString();
        String userId = user.getUid();
        if (!text.isEmpty()) {
            artisanPageViewModel.getArtisan().observe(getViewLifecycleOwner(), artisan -> {
                artisanPageViewModel.submitReview(text, userId, artisan.getUid());
            });
        }
    }

    private void initRecyclerView() {
        artisanPageViewModel.getReviews().observe(getViewLifecycleOwner(), reviews -> {
            Log.d(TAG, String.valueOf(reviews.size()));
            RecyclerView recyclerView = binding.reviewsList;
            recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
            ArtisanReviewsAdapter adapter = new ArtisanReviewsAdapter(reviews);
            recyclerView.setAdapter(adapter);
            recyclerView.post(adapter::notifyDataSetChanged);
        });
    }
}