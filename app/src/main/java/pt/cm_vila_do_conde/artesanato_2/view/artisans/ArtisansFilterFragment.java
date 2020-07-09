package pt.cm_vila_do_conde.artesanato_2.view.artisans;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentArtisansFilterBinding;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.ArtisansListViewModel;


// TODO: fix popBackStack because on filter button click it auto returns to list

public class ArtisansFilterFragment extends Fragment {
    private static final String TAG = "FILTER";

    private FragmentArtisansFilterBinding binding;
    private NavController navController;
    private ArtisansListViewModel artisanListViewModel;

    private FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private CollectionReference artisansRef = rootRef.collection("artisans");
    private Query query = artisansRef;

    private String selected;

    public ArtisansFilterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentArtisansFilterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNavController();
        initBackBtn();
        initArtisanListViewModel();
        initSpinner();
        populateFilters();
        initClearBtn();
        initSubmitBtn();
    }

    private void initNavController() {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
    }

    private void initBackBtn() {
        binding.btnBack.setOnClickListener(v -> navController.popBackStack());
    }

    private void initArtisanListViewModel() {
        artisanListViewModel = new ViewModelProvider(requireActivity()).get(ArtisansListViewModel.class);
    }

    private void initSubmitBtn() {
        binding.submitFilterBtn.setOnClickListener(v -> filter());
    }

    private void initSpinner() {
        List<String> orderBy = new ArrayList<>();
        orderBy.add("Nenhum");
        orderBy.add("Nome");
        orderBy.add("Reputação");
        orderBy.add("Visualizações");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_spinner_item, orderBy);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.orderBySpinner.setAdapter(adapter);
        binding.orderBySpinner.setOnItemSelectedListener(new ItemSelectListener());
    }

    private void populateFilters() {

    }

    private void initClearBtn() {

    }

    private void filter() {
        int orderType = binding.orderTypeRadioGrp.getCheckedRadioButtonId();
        int artisanType = binding.typeRadioGrp.getCheckedRadioButtonId();

        String queryArtisanType = "";
        String queryOrderBy = "name";
        Query.Direction queryOrderType = Query.Direction.ASCENDING;

        if (orderType > -1) {
            switch (orderType) {
                case R.id.asc_btn:
                    queryOrderType = Query.Direction.ASCENDING;
                    break;
                case R.id.desc_btn:
                    queryOrderType = Query.Direction.DESCENDING;
                    break;
                default:
                    break;
            }
        }

        if (!selected.equals("Nenhum")) {
            switch (selected) {
                case "Nome":
                    queryOrderBy = "name";
                    break;
                case "Reputação":
                    queryOrderBy = "ranking";
                    break;
                case "Visualizaões":
                    queryOrderBy = "uniqueViews";
                    break;
                default:
                    break;
            }
        }

        if (artisanType > -1) {
            switch (artisanType) {
                case R.id.food_btn:
                    queryArtisanType = "food";
                    break;
                case R.id.clothes_btn:
                    queryArtisanType = "clothing";
                    break;
                case R.id.decor_btn:
                    queryArtisanType = "decoration";
                    break;
                default:
                    break;
            }
        }

        query = queryArtisanType.isEmpty() ? artisansRef : artisansRef.whereEqualTo("artisanType", queryArtisanType);
        query = query.orderBy(queryOrderBy, queryOrderType);

        artisanListViewModel.setQuery(query);
        navController.navigate(R.id.artisansListFragment);
    }


    private class ItemSelectListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            selected = parent.getItemAtPosition(position).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }
}
