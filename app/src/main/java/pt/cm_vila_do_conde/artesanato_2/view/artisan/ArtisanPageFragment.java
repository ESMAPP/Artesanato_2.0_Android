package pt.cm_vila_do_conde.artesanato_2.view.artisan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.FragmentArtisanPageAdapter;
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
    private String artisanId;
    private ArtisanPageViewModel artisanPageViewModel;
    private SharedUserViewModel sharedUserViewModel;

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
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentArtisanPageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fetchArtisanById();
        handleInitialUiState();
        initNavController();
        setupTabAdapter();
        initBackBtn();
        initChatBtn();
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

    private void handleInitialUiState() {
        artisanPageViewModel.getArtisan().observe(getViewLifecycleOwner(), artisan -> {
            User user = sharedUserViewModel.getUserLiveData().getValue();
            if (user == null || user.getType() != UserTypes.ARTISAN || !user.getUid().equals(artisan.getAssociatedUser())) {
                binding.btnExtra.setVisibility(View.GONE);
            }
        });
    }

    private void fetchArtisanById() {
        artisanPageViewModel.fetchArtisanById(artisanId);
        initObservable();
    }

    private void initObservable() {
        artisanPageViewModel.getArtisan().observe(getViewLifecycleOwner(), this::updateUI);
    }

    public void setupTabAdapter() {
        ViewPager artisanViewPager = binding.viewPagerArtisan;
        artisanViewPager.setAdapter(new FragmentArtisanPageAdapter(getChildFragmentManager()));
        TabLayout tabs = binding.innerNavBar;
        tabs.setupWithViewPager(artisanViewPager);
    }

    private void updateUI(@NotNull Artisan artisan) {
        binding.artisanName.setText(artisan.getName());
        binding.artisanReputation.setText(String.valueOf(artisan.getReputation()));
        binding.artisanRanking.setText(String.valueOf(artisan.getRanking()));
        Picasso.get().load(artisan.getImage())
                .placeholder(R.drawable.logo_i)
                .transform(new CropCircleTransformation())
                .into(binding.artisanPic);
    }

    private void initBackBtn(){
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
                        if (!user.getUid().equals(artisan.getAssociatedUser())) {
                            Bundle bundle = new Bundle();
                            bundle.putString("userId", user.getUid());
                            bundle.putString("artisanId", artisanId);
                            navController.navigate(R.id.action_artisanPageFragment_to_chatFragment, bundle);
                        } else {
                            Toast.makeText(requireContext(), "Implement artisan chat list!", Toast.LENGTH_LONG).show();
                        }
                    });
                });
            }
        });
    }
}
