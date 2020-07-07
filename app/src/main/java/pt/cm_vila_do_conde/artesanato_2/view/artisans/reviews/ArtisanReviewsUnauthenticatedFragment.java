package pt.cm_vila_do_conde.artesanato_2.view.artisans.reviews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import org.jetbrains.annotations.NotNull;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentViewPagerEmptyBinding;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentViewPagerEmptyBtnBinding;


public class ArtisanReviewsUnauthenticatedFragment extends Fragment {
    private static final String TAG = "UNAUTHENTICATED_REVIEWS";

    private FragmentViewPagerEmptyBtnBinding binding;
    private NavController navController;

    public ArtisanReviewsUnauthenticatedFragment() {
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
        binding = FragmentViewPagerEmptyBtnBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNavController();
        setEmptyState();
        initSignInBtn();
    }

    private void initNavController() {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
    }

    private void setEmptyState() {
        requireActivity().findViewById(R.id.artisan_page).setBackgroundResource(R.color.white);
        binding.textEmptyState.setText(getText(R.string.label_need_login_reviews));
        binding.imageEmptyState.setImageResource(R.drawable.ic_placeholder_key_color);
    }

    private void initSignInBtn() {
        binding.btnSignIn.setOnClickListener(v -> navController.navigate(R.id.authActivity));
    }
}