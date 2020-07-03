package pt.cm_vila_do_conde.artesanato_2.view.artisans.gallery;

import android.os.Bundle;
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

import java.util.ArrayList;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.ArtisanGalleryAdapter;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentArtisanGalleryListBinding;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.ArtisanPageViewModel;

public class ArtisanGalleryFragment extends Fragment {

    FragmentArtisanGalleryListBinding binding;
    private int mColumnCount = 3;
    private ArtisanPageViewModel artisanPageViewModel;

    public ArtisanGalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentArtisanGalleryListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requireActivity().findViewById(R.id.artisan_page).setBackgroundResource(R.drawable.bg_3);

        initArtisanViewModel();
        initRecvclerView();
    }

    private void initArtisanViewModel() {
        artisanPageViewModel = new ViewModelProvider(requireActivity()).get(ArtisanPageViewModel.class);
    }

    private void initRecvclerView() {
        artisanPageViewModel.getArtisan().observe(getViewLifecycleOwner(), artisan -> updateUI(artisan.getGallery()));
    }

    private void updateUI(ArrayList<String> gallery) {
        if (gallery != null) {
            RecyclerView recyclerView = binding.galleryList;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), mColumnCount));
            }
            recyclerView.setAdapter(new ArtisanGalleryAdapter(gallery));
        }
    }
}