package pt.cm_vila_do_conde.artesanato_2.view;

import android.annotation.SuppressLint;
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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.squareup.picasso.Picasso;


import org.jetbrains.annotations.NotNull;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentHomeBinding;

import pt.cm_vila_do_conde.artesanato_2.model.Artisan;

import pt.cm_vila_do_conde.artesanato_2.model.Event;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.HomeViewModel;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.SharedUserViewModel;

public class HomeFragment extends Fragment {
    LinearLayoutManager layoutManager;
    private String TAG = "HOME_FRAGMENT";
    private FragmentHomeBinding binding;
    private NavController navController;
    private HomeViewModel homeViewModel;
    private SharedUserViewModel sharedUserViewModel;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUserViewModel();
        initHomeViewModel();

        getFairEvent();
        getUpcomingEvent();
        getFeaturedArtisan();
        checkIfUserIsAuthenticated();

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        // layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        // Init card navigation listeners
        binding.artisanCard.setOnClickListener(v -> goToArtisanPage());
        binding.eventCard.setOnClickListener(v -> goToUpcomingEventPage());
        binding.fairCard.setOnClickListener(v -> goToFairEventPage());

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);


        // Init buttons via binding
        binding.btnNotifications.setOnClickListener(v -> goToNotifications());
        binding.btnProfile.setOnClickListener(v -> goToProfile());
        binding.btnFair.setOnClickListener(v -> goToLocation());
        binding.btnEvents.setOnClickListener(v -> goToEvents());
        binding.btnExplore.setOnClickListener(v -> goToExplore());
        binding.btnContests.setOnClickListener(v -> goToContests());
        binding.btnFavourites.setOnClickListener(v -> goToFavourites());
        binding.btnRankings.setOnClickListener(v -> goToRankings());
        binding.btnInfo.setOnClickListener(v -> goToInformations());
    }


    private void initUserViewModel() {
        sharedUserViewModel = new ViewModelProvider(requireActivity()).get(SharedUserViewModel.class);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void initHomeViewModel() {
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
    }


    private void checkIfUserIsAuthenticated() {
        sharedUserViewModel.checkIfUserIsAuthenticated();
        sharedUserViewModel.getUserLiveData().observe(getViewLifecycleOwner(), user -> {
            if (user.isAuthenticated()) getUserFromDatabase(user.getUid());
        });
    }

    private void getUserFromDatabase(String uid) {
        sharedUserViewModel.setUid(uid);
    }

    private void getFairEvent() {
        homeViewModel.getFairEvent();
        homeViewModel.fairEvent.observe(getViewLifecycleOwner(), this::updateFairCard);
    }

    private void updateFairCard(Event fair) {

        Picasso.get().load(fair.getImage())
                .placeholder(R.drawable.logo_i)
                .resize(binding.fairCover.getMeasuredWidth(), binding.fairCover.getMeasuredHeight())
                .into(binding.fairCover);
        binding.fairTitle.setText(fair.getTitle());
        binding.fairSubtitle.setText(fair.getStartDate().toDate().toString());
    }

    private void getUpcomingEvent() {
        homeViewModel.getUpComingEvent();
        homeViewModel.upcomingEvent.observe(getViewLifecycleOwner(), this::updateEventCard);
    }

    private void updateEventCard(Event event) {
        Picasso.get().load(event.getImage())
                .placeholder(R.drawable.logo_i)
                .resize(binding.eventCover.getMeasuredWidth(), binding.eventCover.getMeasuredHeight())
                .into(binding.eventCover);
        binding.eventTitle.setText(event.getTitle());
        binding.eventSubtitle.setText(event.getStartDate().toDate().toString());
    }

    private void getFeaturedArtisan() {
        homeViewModel.getFeaturedArtisan();
        homeViewModel.featuredArtisan.observe(getViewLifecycleOwner(), this::updateArtisanCard);
    }

    @SuppressLint("SetTextI18n")
    public void updateArtisanCard(Artisan artisan){
            Picasso.get().load(artisan.getImage())
                    .placeholder(R.drawable.logo_i)
                    .resize(binding.artisanCover.getMeasuredWidth(), binding.artisanCover.getMeasuredHeight())
                    .into(binding.artisanCover);
            binding.artisanTitle.setText(artisan.getName());
            binding.artisanSubtitle.setText("Reputação: " + artisan.getReputation());

        }

    /*private void updateRecylerView(ArrayList list) {
        HomeRecyclerAdapter adapter = new HomeRecyclerAdapter(list);
        binding.highlightsCards.setHasFixedSize(true);
        binding.highlightsCards.setLayoutManager(layoutManager);
        binding.highlightsCards.setAdapter(adapter);
    }*/



    public void goToArtisanPage() {
        String id = homeViewModel.featuredArtisan.getValue().getUid();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        navController.navigate(R.id.artisanPageFragment, bundle);
    }

    public void goToUpcomingEventPage() {
        String id = homeViewModel.upcomingEvent.getValue().getId();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        navController.navigate(R.id.eventPageFragment, bundle);
    }

    public void goToFairEventPage() {
        String id = homeViewModel.fairEvent.getValue().getId();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        navController.navigate(R.id.eventPageFragment, bundle);

    }


    private void goToAuth() {
        navController.navigate(R.id.action_homeFragment_to_authActivity);
    }

    private void goToNotifications() {
        navController.navigate(R.id.action_homeFragment_to_notificationsFragment);
    }

    // TODO refactor this in the future
    private void goToProfile() {
        // Check userRole to see which path to take
        sharedUserViewModel.getUserLiveData().observe(getViewLifecycleOwner(), user -> {
            if (user.isAuthenticated()) {
                if (user.getType() == 2) {
                    navController.navigate(R.id.action_homeFragment_to_profileFragment);
                } else if (user.getType() == 3) {
                    navController.navigate(R.id.action_homeFragment_to_profileFragment);
                }
            } else {
                goToAuth();
            }
        });
    }

    private void goToLocation() {
        navController.navigate(R.id.action_homeFragment_to_fairLocationFragment);
    }

    private void goToEvents() {
        navController.navigate(R.id.action_homeFragment_to_eventListFragment);
    }

    private void goToExplore() {
        navController.navigate(R.id.action_homeFragment_to_artisansListFragment);
    }

    private void goToContests() {
    }

    private void goToFavourites() {
    }

    private void goToRankings() {
        navController.navigate(R.id.action_homeFragment_to_rankingsFragment);
    }

    private void goToInformations() {
    }
}
