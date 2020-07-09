package pt.cm_vila_do_conde.artesanato_2.view.profile.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentViewPagerEmptyBinding;


public class ProfileActivityEmptyFragment extends Fragment {
    private String TAG = "PROFILE_ACTIVITY";

    private FragmentViewPagerEmptyBinding binding;

    public ProfileActivityEmptyFragment() {
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
        requireActivity().findViewById(R.id.profile_page).setBackgroundResource(R.color.white);
        binding.textEmptyState.setText(getText(R.string.label_empty_activity));
        binding.imageEmptyState.setImageResource(R.drawable.ic_placeholder_image_color);
    }
}
