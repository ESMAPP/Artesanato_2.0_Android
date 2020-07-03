package pt.cm_vila_do_conde.artesanato_2.view.artisans.gallery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.ArtisanPageViewModel;

public class RootGalleryFragment extends Fragment {
    private ArtisanPageViewModel artisanPageViewModel;

    public RootGalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_root_gallery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initArtisanViewModel();
        handleView();
    }

    private void initArtisanViewModel(){
        artisanPageViewModel = new ViewModelProvider(requireActivity()).get(ArtisanPageViewModel.class);
    }

    private void handleView(){
        artisanPageViewModel.getArtisan().observe(getViewLifecycleOwner(), artisan -> {
            if(artisan.getGallery() != null) {
                ArtisanGalleryFragment artisanGalleryFragment = new ArtisanGalleryFragment();
                this.getChildFragmentManager().beginTransaction()
                        .replace(R.id.root_gallery, artisanGalleryFragment, null)
                        .addToBackStack(null)
                        .commit();
            } else {
                EmptyGalleryFragment  emptyGalleryFragment= new EmptyGalleryFragment();
                this.getChildFragmentManager().beginTransaction()
                        .replace(R.id.root_gallery, emptyGalleryFragment, null)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}