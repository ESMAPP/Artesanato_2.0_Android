package pt.cm_vila_do_conde.artesanato_2.view.rankings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import pt.cm_vila_do_conde.artesanato_2.adapter.RankingsArtisansAdapter;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentRankingsListBinding;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.RankingsViewModel;


public class RankingsArtisansFragment extends Fragment {
    private RankingsViewModel rankingsViewModel;
    private FragmentRankingsListBinding binding;
    private RecyclerView recyclerView;

    public RankingsArtisansFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRankingsListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRankingsViewModel();
        fetchArtisans();
    }

    private void initRankingsViewModel() {
        rankingsViewModel = new ViewModelProvider(requireActivity()).get(RankingsViewModel.class);
    }

    private void fetchArtisans() {
        rankingsViewModel.fetchArtisans();
        initObservable();
    }

    private void initObservable() {
        initRecyclerAdapter();
        rankingsViewModel.getArtisans().observe(getViewLifecycleOwner(), artisanList -> {
            if (artisanList != null) {
                recyclerView.setAdapter(new RankingsArtisansAdapter(artisanList.subList(3, artisanList.size())));
            }
        });
    }

    private void initRecyclerAdapter() {
        recyclerView = binding.rankingsList;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
    }
}