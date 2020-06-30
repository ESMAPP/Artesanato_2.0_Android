package pt.cm_vila_do_conde.artesanato_2.view.artisan;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.ArtisanListAdapter;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentArtisansListBinding;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.ArtisanListViewModel;

public class ArtisansListFragment extends Fragment {
    private ArtisanListViewModel artisanListViewModel;

    private FragmentArtisansListBinding binding;
    private RecyclerView artisanRecyclerView;
    private ArtisanListAdapter artisanListAdapter;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
        artisanListViewModel = new ViewModelProvider(requireActivity()).get(ArtisanListViewModel.class);
    }

    private void fetchArtisansList() {
        artisanListViewModel.fetchArtisansList(query);
    }

    private void initRecyclerAdapter() {
        artisanRecyclerView = binding.artisansList;
        artisanRecyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        artisanRecyclerView.setLayoutManager(gridLayoutManager);
        initObservable();
    }

    private void initObservable() {
        artisanListViewModel.getArtisansList().observe(getViewLifecycleOwner(), artisanList -> {
            if (artisanList != null) {
                artisanListAdapter = new ArtisanListAdapter(artisanList, navController);
                artisanRecyclerView.setAdapter(artisanListAdapter);
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
                artisanListViewModel.fetchArtisansList(query);
                initObservable();
                return true;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    query = artisansRef;
                    artisanListViewModel.fetchArtisansList(query);
                    initObservable();
                }
                return true;
            }
        });
    }

}
