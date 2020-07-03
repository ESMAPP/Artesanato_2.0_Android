package pt.cm_vila_do_conde.artesanato_2.view.artisan.reviews;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentEmptyViewPagerBinding;


public class EmptyReviewsFragment extends Fragment {

    FragmentEmptyViewPagerBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEmptyViewPagerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requireActivity().findViewById(R.id.artisan_page).setBackgroundColor(Color.WHITE);

        binding.textEmptyState.setText(getText(R.string.label_empty_reviews));
        binding.imageEmptyState.setImageResource(R.drawable.ic_placeholder_image_color);
    }
}