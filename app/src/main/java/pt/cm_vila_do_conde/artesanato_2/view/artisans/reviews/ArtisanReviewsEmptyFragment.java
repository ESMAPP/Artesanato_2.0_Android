package pt.cm_vila_do_conde.artesanato_2.view.artisans.reviews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentViewPagerEmptyBinding;


public class ArtisanReviewsEmptyFragment extends Fragment {
    FragmentViewPagerEmptyBinding binding;

    public ArtisanReviewsEmptyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentViewPagerEmptyBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
/*
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setEmptyState();
    }

    @Override
    public void onResume() {
        super.onResume();
        setEmptyState();
    }

    private void setEmptyState() {
        requireActivity().findViewById(R.id.artisan_page).setBackgroundResource(R.color.white);
        binding.textEmptyState.setText(getText(R.string.label_empty_gallery));
        binding.imageEmptyState.setImageResource(R.drawable.ic_placeholder_image_color);
    }*/
}