package pt.cm_vila_do_conde.artesanato_2.view.artisans.contests;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentViewPagerEmptyBinding;


public class ArtisanContestsEmptyFragment extends Fragment {
    FragmentViewPagerEmptyBinding binding;
    private String TAG = "ARTISAN_CONTESTS_EMPTY";

    public ArtisanContestsEmptyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        setEmptyState();
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentViewPagerEmptyBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //setEmptyState();
    }

    private void setEmptyState() {
        Log.d(TAG, "white");
        requireActivity().findViewById(R.id.artisan_page).setBackgroundResource(R.color.white);
        binding.textEmptyState.setText(getText(R.string.label_empty_artisan_contests));
        binding.imageEmptyState.setImageResource(R.drawable.ic_placeholder_cup_color);
    }
}
