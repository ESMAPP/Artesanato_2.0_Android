package pt.cm_vila_do_conde.artesanato_2.view.artisans;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.jetbrains.annotations.NotNull;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentArtisanAboutBinding;
import pt.cm_vila_do_conde.artesanato_2.model.Artisan;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.ArtisanPageViewModel;


public class ArtisanAboutFragment extends Fragment {
    private static final String TAG = "ARTISAN_ABOUT";

    private FragmentArtisanAboutBinding binding;
    private ArtisanPageViewModel artisanPageViewModel;

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
        binding = FragmentArtisanAboutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //setBackground();
        initArtisanViewModel();
        initObservable();
    }

    private void setBackground() {
        Log.d(TAG, "color");
        requireActivity().findViewById(R.id.artisan_page).setBackgroundResource(R.drawable.bg_3);
    }

    private void initArtisanViewModel() {
        artisanPageViewModel = new ViewModelProvider(requireActivity()).get(ArtisanPageViewModel.class);
    }

    private void initObservable(){
        artisanPageViewModel.getArtisan().observe(getViewLifecycleOwner(), this::updateUi);
    }

    private void updateUi(Artisan artisan){
        binding.textDescription.setText(artisan.getDescription());
        binding.textCity.setText(artisan.getCity());
        binding.textEmail.setText(artisan.getEmail());
        binding.textPhone.setText(artisan.getPhone());
        if (!artisan.getAttributes().isAcceptsCC()) binding.textAtm.setVisibility(View.GONE);
        if (!artisan.getAttributes().isAcceptsMBWay()) binding.textMbWay.setVisibility(View.GONE);
    }
}