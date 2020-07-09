package pt.cm_vila_do_conde.artesanato_2.view.events;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import org.jetbrains.annotations.NotNull;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentEventPageBinding;


public class EventPageFragment extends Fragment {
    private static final String TAG = "EVENT_PAGE";
    private static final String EVENT_ID = "id";

    private FragmentEventPageBinding binding;
    private NavController navController;

    private String eventId;

    public EventPageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventId = getArguments().getString(EVENT_ID);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEventPageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNavController();
        initBackBtn();

        // TODO: binding.testEventPage.setText(eventId);
    }

    private void initNavController() {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
    }

    private void initBackBtn() {
        binding.btnBack.setOnClickListener(v -> navController.popBackStack());
    }
}
