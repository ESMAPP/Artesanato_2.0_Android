package pt.cm_vila_do_conde.artesanato_2.view.rankings;

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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.ArtisanReviewsAdapter;
import pt.cm_vila_do_conde.artesanato_2.adapter.ArtisansListAdapter;
import pt.cm_vila_do_conde.artesanato_2.adapter.RankingsArtisansAdapter;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentRankingsRootBinding;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.ArtisansListViewModel;


public class RankingsFragment extends Fragment {
    private FragmentRankingsRootBinding binding;
    private NavController navController;
    private RecyclerView recyclerView;
    private ArtisansListViewModel artisansListViewModel;
    private RankingsArtisansAdapter rankingsArtisansAdapter;

    private FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private CollectionReference artisansRef = rootRef.collection("artisans");
    private Query query = artisansRef;

    public RankingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRankingsRootBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNavController();
        initArtisanListViewModel();
        fetchArtisansList();
        initBackBtn();
    }

    private void initNavController() {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
    }

    private void initArtisanListViewModel() {
        artisansListViewModel = new ViewModelProvider(requireActivity()).get(ArtisansListViewModel.class);
    }

    private void fetchArtisansList() {
        artisansListViewModel.getQuery().observe(getViewLifecycleOwner(), query -> {
            artisansListViewModel.fetchArtisansList(query);
            initRecyclerAdapter();
        });
    }

    private void initRecyclerAdapter() {
        recyclerView = binding.rankingsArtisansList;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        initObservable();
    }

    private void initObservable() {
        artisansListViewModel.getArtisansList().observe(getViewLifecycleOwner(), artisanList -> {
            if (artisanList != null) {
                rankingsArtisansAdapter = new RankingsArtisansAdapter(artisanList, navController);
                recyclerView.setAdapter(rankingsArtisansAdapter);
            }
        });
    }
    private void initBackBtn() {
        binding.btnBack.setOnClickListener(v -> navController.popBackStack());
    }
}
