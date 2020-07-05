package pt.cm_vila_do_conde.artesanato_2.view.rankings;

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

import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.RankingsAdapter;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentRankingsBinding;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.RankingsViewModel;


public class RankingsFragment extends Fragment {
    private String TAG = "RANKINGS";

    private FragmentRankingsBinding binding;
    private NavController navController;
    private RankingsViewModel rankingsViewModel;

    public RankingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRankingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNavController();
        initBackBtn();
        setupTabAdapter();
        initRankingsViewModel();
        fetchArtisans();
    }

    private void initNavController() {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
    }

    private void initBackBtn() {
        binding.btnBack.setOnClickListener(v -> navController.popBackStack());
    }

    public void setupTabAdapter() {
        binding.viewPagerRankings.setAdapter(new RankingsAdapter(getChildFragmentManager()));
        binding.innerNavBar.setupWithViewPager(binding.viewPagerRankings);
        handleTabChange();
    }

    public void handleTabChange() {
        binding.innerNavBar.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        Log.d("TAG", "artesãos");
                        fetchArtisans();
                        break;
                    case 1:
                        Log.d("TAG", "visitantes");
                        loadTopUsers();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void initRankingsViewModel() {
        rankingsViewModel = new ViewModelProvider(requireActivity()).get(RankingsViewModel.class);
    }

    private void fetchArtisans() {
        rankingsViewModel.fetchTopArtisans();
        loadTopArtisans();
    }

    private void loadTopArtisans() {
        rankingsViewModel.getTopArtisans().observe(getViewLifecycleOwner(), artisanList -> {
            if (artisanList != null) {
                binding.rankingsUserName1.setText(artisanList.get(0).getName());
                binding.rankingsUserName2.setText(artisanList.get(1).getName());
                binding.rankingsUserName3.setText(artisanList.get(2).getName());
                binding.rankingsUserReputation1.setText(String.valueOf(artisanList.get(0).getReputation()));
                binding.rankingsUserReputation2.setText(String.valueOf(artisanList.get(1).getReputation()));
                binding.rankingsUserReputation3.setText(String.valueOf(artisanList.get(2).getReputation()));
                Picasso.get().load(artisanList.get(0).getProfilePic())
                        .placeholder(R.drawable.ic_placeholder_user_pic)
                        .fit()
                        .transform(new CropCircleTransformation())
                        .centerCrop()
                        .into(binding.rankingsUserPic1);
                Picasso.get().load(artisanList.get(1).getProfilePic())
                        .placeholder(R.drawable.ic_placeholder_user_pic)
                        .fit()
                        .transform(new CropCircleTransformation())
                        .centerCrop()
                        .into(binding.rankingsUserPic2);
                Picasso.get().load(artisanList.get(2).getProfilePic())
                        .placeholder(R.drawable.ic_placeholder_user_pic)
                        .fit()
                        .transform(new CropCircleTransformation())
                        .centerCrop()
                        .into(binding.rankingsUserPic3);
            }
        });
    }

    private void loadTopUsers() {
        binding.rankingsUserName1.setText("visitante 1");
        binding.rankingsUserName2.setText("visitante 2");
        binding.rankingsUserName3.setText("visitante 3");
        binding.rankingsUserReputation1.setText("v 1111");
        binding.rankingsUserReputation2.setText("v 222");
        binding.rankingsUserReputation3.setText("v 33");
    }
}
