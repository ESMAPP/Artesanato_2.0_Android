package pt.cm_vila_do_conde.artesanato_2.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.ArtisanListAdapter;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentArtisansListBinding;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.ArtisanListViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArtisansListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArtisansListFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ArtisanListViewModel artisanListViewModel;

    private FragmentArtisansListBinding binding;
    private RecyclerView artisanRecyclerView;
    private ArtisanListAdapter artisanListAdapter;
    private NavController navController;

    public ArtisansListFragment() {
        // Required empty public constructor
    }

    public static ArtisansListFragment newInstance(String param1, String param2) {
        ArtisansListFragment fragment = new ArtisansListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
        initRecyclerAdapter();
    }

    private void initArtisanListViewModel(){
        artisanListViewModel = new ViewModelProvider(requireActivity()).get(ArtisanListViewModel.class);
    }

    private void fetchArtisansList(){
        artisanListViewModel.fetchArtisansList();
    }

    private void initRecyclerAdapter() {
        artisanRecyclerView = binding.artisansList;
        artisanRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        artisanRecyclerView.setLayoutManager(linearLayoutManager);
        artisanListViewModel.fetchArtisansList();
        artisanListViewModel.getArtisansList().observe(getViewLifecycleOwner(), artisanList -> {
            if(artisanList != null ) {
                artisanListAdapter = new ArtisanListAdapter(artisanList, navController);
                artisanRecyclerView.setAdapter(artisanListAdapter);
            }
        });

    }
}
