package pt.cm_vila_do_conde.artesanato_2.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.FragmentArtisanPageAdapter;
import pt.cm_vila_do_conde.artesanato_2.adapter.FragmentAuthAdapter;
import pt.cm_vila_do_conde.artesanato_2.constants.UserTypes;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentArtisanPageBinding;
import pt.cm_vila_do_conde.artesanato_2.model.Artisan;
import pt.cm_vila_do_conde.artesanato_2.model.User;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.ArtisanPageViewModel;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.SharedUserViewModel;


public class ArtisanPageFragment extends Fragment {
    private static final String ARTISAN_ID = "id";
    FragmentArtisanPageBinding binding;

    private String artisanId;
    private ArtisanPageViewModel artisanPageViewModel;
    private SharedUserViewModel sharedUserViewModel;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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
        setupTabAdapter();
    }

    private void initArtisanViewModel(){
        artisanPageViewModel = new ViewModelProvider(requireActivity()).get(ArtisanPageViewModel.class);
    }

    private void initSharedViewModel(){
        sharedUserViewModel = new ViewModelProvider(requireActivity()).get(SharedUserViewModel.class);
    }

    private void handleInitialUiState(){
        artisanPageViewModel.getArtisan().observe(getViewLifecycleOwner(), artisan -> {
            User user = sharedUserViewModel.getUserLiveData().getValue();
            if(user == null || user.getType() != UserTypes.ARTISAN || !user.getUid().equals(artisan.getAssociatedUser())){
                binding.btnExtra.setVisibility(View.GONE);
            }
        });
    }

    private void fetchArtisanById() {
        artisanPageViewModel.fetchArtisanById(artisanId);
        initObservable();
    }

    private void initObservable(){
        artisanPageViewModel.getArtisan().observe(getViewLifecycleOwner(), this::updateUI);
    }

    public void setupTabAdapter() {
        ViewPager artisanViewPager = binding.artisanViewPager;
        artisanViewPager.setAdapter(new FragmentArtisanPageAdapter(getChildFragmentManager()));
        TabLayout tabs = binding.innerNavBar;
        tabs.setupWithViewPager(artisanViewPager);
    }

    private void updateUI(Artisan artisan){
        binding.artisanName.setText(artisan.getName());
        binding.artisanReputation.setText(String.valueOf(artisan.getReputation()));
        binding.artisanRanking.setText(String.valueOf(artisan.getRanking()));
        Picasso.get().load(artisan.getImage())
                .placeholder(R.drawable.logo_i)
                .transform(new CropCircleTransformation())
                .into(binding.artisanPic);
    }
}
