package pt.cm_vila_do_conde.artesanato_2.view.events;

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

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentEventPageBinding;
import pt.cm_vila_do_conde.artesanato_2.model.Artisan;
import pt.cm_vila_do_conde.artesanato_2.model.Event;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.ArtisanPageViewModel;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.EventPageViewModel;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.SharedUserViewModel;


public class EventPageFragment extends Fragment {
    private static final String TAG = "EVENT_PAGE";
    private static final String EVENT_ID = "id";

    private FragmentEventPageBinding binding;
    private NavController navController;
    private EventPageViewModel eventPageViewModel;
    private SharedUserViewModel sharedUserViewModel;

    private String eventId;

    public EventPageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            eventId = getArguments().getString(EVENT_ID);
        }
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
        initEventViewModel();
        initSharedViewModel();
        fetchEventById();
    }

    private void initNavController() {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
    }

    private void initBackBtn() {
        binding.btnBack.setOnClickListener(v -> navController.popBackStack());
    }

    private void initEventViewModel() {
        eventPageViewModel = new ViewModelProvider(requireActivity()).get(EventPageViewModel.class);
    }

    private void initSharedViewModel() {
        sharedUserViewModel = new ViewModelProvider(requireActivity()).get(SharedUserViewModel.class);
    }

    private void fetchEventById() {
        eventPageViewModel.fetchEventById(eventId);
        initObservable();
    }

    private void initObservable() {
        eventPageViewModel.getEvent().observe(getViewLifecycleOwner(), this::updateEventInfo);
    }

    private void updateEventInfo(@NotNull Event event) {
        binding.textEventTitle.setText(event.getTitle());
        binding.textEventLocation.setText(event.getLocation().getName());
        binding.textDescription.setText(event.getDescription());
        Picasso.get().load(event.getImage())
                .placeholder(R.drawable.bar_top_placeholder_image)
                .fit()
                .transform(new RoundedCornersTransformation(55, 0, RoundedCornersTransformation.CornerType.BOTTOM))
                .centerCrop()
                .into(binding.imageEventCover);
    }
}
