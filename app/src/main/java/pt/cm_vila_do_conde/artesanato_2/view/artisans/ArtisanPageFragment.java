package pt.cm_vila_do_conde.artesanato_2.view.artisans;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.ArtisanPageAdapter;
import pt.cm_vila_do_conde.artesanato_2.constants.UserTypes;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentArtisanPageBinding;
import pt.cm_vila_do_conde.artesanato_2.model.Artisan;
import pt.cm_vila_do_conde.artesanato_2.model.User;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.ArtisanPageViewModel;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.SharedUserViewModel;


public class ArtisanPageFragment extends Fragment {
    private static final String ARTISAN_ID = "id";

    private FragmentArtisanPageBinding binding;
    private NavController navController;
    private ArtisanPageViewModel artisanPageViewModel;
    private SharedUserViewModel sharedUserViewModel;

    private String artisanId;

    public ArtisanPageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            artisanId = getArguments().getString(ARTISAN_ID);
        }
        initArtisanViewModel();
        initSharedViewModel();
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentArtisanPageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNavController();
        initBackBtn();
        initChatBtn();
        setupTabAdapter();
        fetchArtisanById();
        handleExtraBtnState();
        fetchReviews();
    }

    private void initArtisanViewModel() {
        artisanPageViewModel = new ViewModelProvider(requireActivity()).get(ArtisanPageViewModel.class);
    }

    private void initSharedViewModel() {
        sharedUserViewModel = new ViewModelProvider(requireActivity()).get(SharedUserViewModel.class);
    }

    private void initNavController() {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
    }

    private void initBackBtn() {
        binding.btnBack.setOnClickListener(v -> navController.popBackStack());
    }

    private void initChatBtn() {
        sharedUserViewModel.getUserLiveData().observe(getViewLifecycleOwner(), user -> {
            if (!user.isAuthenticated()) {
                binding.btnChat.setVisibility(View.GONE);
                binding.toolbar.setVisibility(View.GONE);
            } else {
                artisanPageViewModel.getArtisan().observe(getViewLifecycleOwner(), artisan -> {
                    binding.btnChat.setOnClickListener(v -> {
                        Bundle bundle = new Bundle();
                        bundle.putString("userId", user.getUid());
                        bundle.putString("artisanId", artisanId);
                        if (!user.getUid().equals(artisan.getAssociatedUser())) {
                            navController.navigate(R.id.action_artisanPageFragment_to_chatFragment, bundle);
                        } else {
                            navController.navigate(R.id.action_artisanPageFragment_to_chatList, bundle);
                        }
                    });
                });
            }
        });
    }

    public void setupTabAdapter() {
        binding.viewPagerArtisan.setAdapter(new ArtisanPageAdapter(getChildFragmentManager()));
        binding.innerNavBar.setupWithViewPager(binding.viewPagerArtisan);
    }

    private void fetchArtisanById() {
        artisanPageViewModel.fetchArtisanById(artisanId);
        initObservable();
    }

    private void initObservable() {
        artisanPageViewModel.getArtisan().observe(getViewLifecycleOwner(), this::updateUI);
    }

    private void updateUI(@NotNull Artisan artisan) {
        binding.artisanName.setText(artisan.getName());
        binding.artisanReputation.setText(String.valueOf(artisan.getReputation()));
        binding.artisanRanking.setText(String.valueOf(artisan.getRanking()));
        Picasso.get().load(artisan.getProfilePic())
                .placeholder(R.drawable.ic_placeholder_user_pic)
                .transform(new CropCircleTransformation())
                .into(binding.artisanPic);
        checkUserRanking(artisan.getRanking());
    }

    private void checkUserRanking(int ranking) {
        int shape = R.drawable.shape_circle_stroke_grey;
        int icon = 0;

        if (ranking == 1) {
            shape = R.drawable.shape_circle_stroke_yellow;
            icon = R.drawable.ic_crown_color;
        }

        if (ranking == 2) {
            shape = R.drawable.shape_circle_stroke_orange;
            icon = R.drawable.ic_second_color;
        }

        if (ranking == 3) {
            shape = R.drawable.shape_circle_stroke_blue;
            icon = R.drawable.ic_third_color;
        }

        binding.artisanFrame.setBackgroundResource(shape);
        binding.artisanIcon.setBackgroundResource(icon);
    }

    private void handleExtraBtnState() {
        artisanPageViewModel.getArtisan().observe(getViewLifecycleOwner(), artisan -> {
            User user = sharedUserViewModel.getUserLiveData().getValue();

            if (user == null || user.getType() != UserTypes.ARTISAN || !user.getUid().equals(artisan.getAssociatedUser())) {
                binding.btnExtra.setVisibility(View.GONE);
            }
            else {
                // TODO: initExtraBtn();
            }
        });
    }

    private void fetchReviews() {
        artisanPageViewModel.fetchReviews(artisanId);
    }
}
