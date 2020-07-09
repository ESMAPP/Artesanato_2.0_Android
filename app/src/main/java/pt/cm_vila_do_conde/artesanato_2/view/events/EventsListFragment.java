package pt.cm_vila_do_conde.artesanato_2.view.events;

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

import org.jetbrains.annotations.NotNull;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.ArtisansListAdapter;
import pt.cm_vila_do_conde.artesanato_2.adapter.EventsListAdapter;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentEventsListBinding;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.ArtisansListViewModel;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.EventsListViewModel;


public class EventsListFragment extends Fragment {
    private static final String TAG = "EVENTS_LIST";

    private FragmentEventsListBinding binding;
    private NavController navController;
    private RecyclerView recyclerView;
    private EventsListViewModel eventsListViewModel;
    private EventsListAdapter eventsListAdapter;

    private FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private CollectionReference eventsRef = rootRef.collection("events");
    private Query query = eventsRef;

    public EventsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEventsListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNavController();
        initBackBtn();
        initEventsListViewModel();
        fetchEventsList();
        // TODO: initSearchListener();
        // TODO: initFilterBtnListener();
    }

    private void initNavController() {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
    }

    private void initBackBtn() {
        binding.btnBack.setOnClickListener(v -> navController.popBackStack());
    }

    private void initEventsListViewModel() {
        eventsListViewModel = new ViewModelProvider(requireActivity()).get(EventsListViewModel.class);
    }

    private void fetchEventsList() {
        eventsListViewModel.getQuery().observe(getViewLifecycleOwner(), query -> {
            eventsListViewModel.fetchEventsList(query);
            initRecyclerAdapter();
        });
    }

    private void initRecyclerAdapter() {
        recyclerView = binding.eventsList;
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        initObservable();
    }

    private void initObservable() {
        eventsListViewModel.getEventsList().observe(getViewLifecycleOwner(), eventsList -> {
            if (eventsList != null) {
                eventsListAdapter = new EventsListAdapter(eventsList, navController);
                recyclerView.setAdapter(eventsListAdapter);
            }
        });
    }

    // TODO: add event filter page
    private void initFilterBtnListener() {
        // TODO: binding.btnFilter.setOnClickListener(v -> navController.navigate(R.id.action_eventsListFragment_to_filterEventFragment));
    }

    // TODO: add search event
    public void initSearchListener() {
        /*
        binding.inputSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                query = eventsRef
                        .orderBy("title")
                        .startAt(text)
                        .endAt(text + "\uf8ff");
                eventsListViewModel.setQuery(query);
                initObservable();
                return true;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    query = eventsRef;
                    eventsListViewModel.fetchEventsList(query);
                    initObservable();
                }
                return true;
            }
        });
        */
    }
}
