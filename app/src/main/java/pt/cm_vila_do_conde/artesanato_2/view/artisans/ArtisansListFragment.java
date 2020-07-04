package pt.cm_vila_do_conde.artesanato_2.view.artisans;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.ArtisansListAdapter;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentArtisansListBinding;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.ArtisansListViewModel;


public class ArtisansListFragment extends Fragment {
    private ArtisansListViewModel artisansListViewModel;
    private FragmentArtisansListBinding binding;
    private RecyclerView artisanRecyclerView;
    private ArtisansListAdapter artisansListAdapter;
    private NavController navController;

    private FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private CollectionReference artisansRef = rootRef.collection("artisans");
    private Query query = artisansRef;

    public ArtisansListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentArtisansListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initArtisanListViewModel();
        fetchArtisansList();
        initRecyclerAdapter();
        initSearchListener();
        initFilterBtnListener();
        initBackBtn();
    }

    private void initArtisanListViewModel() {
        artisansListViewModel = new ViewModelProvider(requireActivity()).get(ArtisansListViewModel.class);
    }

    private void fetchArtisansList() {
        artisansListViewModel.fetchArtisansList(query);
    }

    private void initRecyclerAdapter() {
        artisanRecyclerView = binding.artisansList;
        artisanRecyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        artisanRecyclerView.setLayoutManager(gridLayoutManager);
        initObservable();
    }

    private void initObservable() {
        artisansListViewModel.getArtisansList().observe(getViewLifecycleOwner(), artisanList -> {
            if (artisanList != null) {
                artisansListAdapter = new ArtisansListAdapter(artisanList, navController);
                artisanRecyclerView.setAdapter(artisansListAdapter);
            }
        });
    }

    private void initFilterBtnListener() {
        binding.btnFilter.setOnClickListener(v -> navController.navigate(R.id.action_artisansListFragment_to_filterArtisanFragment));
    }

    private void initBackBtn(){
        binding.btnBack.setOnClickListener(v -> navController.popBackStack());
    }

    public void initSearchListener() {
        binding.inputSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                query = artisansRef
                        .orderBy("name")
                        .startAt(text)
                        .endAt(text + "\uf8ff");
                artisansListViewModel.fetchArtisansList(query);
                initObservable();
                return true;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    query = artisansRef;
                    artisansListViewModel.fetchArtisansList(query);
                    initObservable();
                }
                return true;
            }
        });
    }

}
