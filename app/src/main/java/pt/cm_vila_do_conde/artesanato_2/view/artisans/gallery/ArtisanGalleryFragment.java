package pt.cm_vila_do_conde.artesanato_2.view.artisans.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.ArtisanGalleryAdapter;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentArtisanGalleryBinding;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.ArtisanPageViewModel;


public class ArtisanGalleryFragment extends Fragment {
    private String TAG = "ARTISAN_GALLERY";

    private FragmentArtisanGalleryBinding binding;
    private ArtisanPageViewModel artisanPageViewModel;

    private int columnCount = 3;

    public ArtisanGalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        setBackground();
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentArtisanGalleryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //setBackground();
        initArtisanViewModel();
        initRecyclerView();
    }

    private void setBackground() {
        Log.d(TAG, "color");
        requireActivity().findViewById(R.id.artisan_page).setBackgroundResource(R.drawable.bg_3);
    }

    private void initArtisanViewModel() {
        artisanPageViewModel = new ViewModelProvider(requireActivity()).get(ArtisanPageViewModel.class);
    }

    private void initRecyclerView() {
        artisanPageViewModel.getArtisan().observe(getViewLifecycleOwner(), artisan -> updateGallery(artisan.getGallery()));
    }

    private void updateGallery(ArrayList<String> gallery) {
        if (gallery != null) {
            RecyclerView recyclerView = binding.galleryList;

            if (columnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), columnCount));
            }

            recyclerView.setAdapter(new ArtisanGalleryAdapter(gallery));
        }
    }
}