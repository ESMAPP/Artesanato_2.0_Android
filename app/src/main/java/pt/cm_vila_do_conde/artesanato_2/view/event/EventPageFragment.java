package pt.cm_vila_do_conde.artesanato_2.view.event;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentEventPageBinding;

public class EventPageFragment extends Fragment {
    private static final String EVENT_ID = "id";
    private final String TAG = "EVENT_PAGE_FRAGMENT";

    private FragmentEventPageBinding binding;

    private String eventId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventId = getArguments().getString(EVENT_ID);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEventPageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.testEventPage.setText(eventId);
    }
}
