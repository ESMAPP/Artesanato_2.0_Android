package pt.cm_vila_do_conde.artesanato_2.view.artisans;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.ArtisanPageAdapter;
import pt.cm_vila_do_conde.artesanato_2.constants.UserTypes;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentArtisanPageBinding;
import pt.cm_vila_do_conde.artesanato_2.model.Artisan;
import pt.cm_vila_do_conde.artesanato_2.model.User;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.ArtisanPageViewModel;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.SharedUserViewModel;


public class ArtisanPageFragment extends Fragment implements PopupMenu.OnMenuItemClickListener{
    private static final String TAG = "ARTISAN_PAGE";
    private static final String ARTISAN_ID = "id";

    private FragmentArtisanPageBinding binding;
    private NavController navController;
    private ArtisanPageViewModel artisanPageViewModel;
    private SharedUserViewModel sharedUserViewModel;

    private String artisanId;

    public ArtisanPageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            artisanId = getArguments().getString(ARTISAN_ID);
        }
        initArtisanViewModel();
        initSharedViewModel();
    }

    @Override
    public void onResume() {
        super.onResume();
        setBackground();
    }

    /**
     * Binds to corresponding layout
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentArtisanPageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupTabAdapter();
        initNavController();
        initBackBtn();
        initChatBtn();
        fetchArtisanById();
        handleExtraBtnState();
        fetchReviews();
    }

    /**
     * Sets artisan page background to default drawable
     */
    private void setBackground() {
        Log.d(TAG, "color");
        binding.artisanPage.setBackgroundResource(R.drawable.bg_3);
    }

    /**
     * Initializes artisan view model
     */
    private void initArtisanViewModel() {
        artisanPageViewModel = new ViewModelProvider(requireActivity()).get(ArtisanPageViewModel.class);
    }

    /**
     * Initializes sharer user view model
     */
    private void initSharedViewModel() {
        sharedUserViewModel = new ViewModelProvider(requireActivity()).get(SharedUserViewModel.class);
    }

    /**
     * Initializes navigation controller
     */
    private void initNavController() {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
    }

    /**
     * Initializes back button and goes to last page on the pop back stack
     */
    private void initBackBtn() {
        binding.btnBack.setOnClickListener(v -> navController.popBackStack());
    }

    /**
     * Sets artisan page inner navigation tab bar
     */
    public void setupTabAdapter() {
        binding.viewPagerArtisan.setAdapter(new ArtisanPageAdapter(getChildFragmentManager()));
        binding.innerNavBar.setupWithViewPager(binding.viewPagerArtisan);
    }

    /**
     * Initializes chat button
     * Checks if user is authenticated
     * if false: hides chat button
     * if true: checks if current user is the artisan
     * if true: goes to chat list with all user chat rooms
     * if false: goes to chat room of that artisan
     */
    private void initChatBtn() {
        sharedUserViewModel.getUserLiveData().observe(getViewLifecycleOwner(), user -> {
            if (!user.isAuthenticated()) {
                binding.btnChat.setVisibility(View.GONE);
                binding.barBottom.setVisibility(View.GONE);
            } else {
                artisanPageViewModel.getArtisan().observe(getViewLifecycleOwner(), artisan -> {
                    binding.btnChat.setOnClickListener(v -> {
                        Bundle bundle = new Bundle();
                        bundle.putString("userId", user.getUid());
                        bundle.putString("artisanId", artisanId);
                        if (!user.getUid().equals(artisan.getAssociatedUser())) {
                            navController.navigate(R.id.action_artisanPageFragment_to_chatFragment, bundle);
                        } else {
                            navController.navigate(R.id.action_artisanPageFragment_to_chatList, bundle);
                        }
                    });
                });
            }
        });
    }

    /**
     * Fetches artisan by an id
     */
    private void fetchArtisanById() {
        artisanPageViewModel.fetchArtisanById(artisanId);
        initObservable();
    }

    private void initObservable() {
        artisanPageViewModel.getArtisan().observe(getViewLifecycleOwner(), this::updateUI);
    }

    /**
     * Updates artisans ui info
     * @param artisan Receives active artisan object
     */
    private void updateUI(@NotNull Artisan artisan) {
        binding.artisanName.setText(artisan.getName());
        binding.artisanReputation.setText(String.valueOf(artisan.getReputation()));
        binding.artisanRanking.setText(String.valueOf(artisan.getRanking()));
        Picasso.get().load(artisan.getProfilePic())
                .placeholder(R.drawable.ic_placeholder_user_pic)
                .transform(new CropCircleTransformation())
                .into(binding.artisanPic);
        checkArtisanRanking(artisan.getRanking());
    }

    /**
     * Checks which is the selected artisans ranking position
     * Changes the profile pic frame and icon according to first, second or third position
     * if ranking > 3 then frame is the default color and icon is 0
     * @param ranking Receives the artisan ranking position
     */
    private void checkArtisanRanking(int ranking) {
        int shape = R.drawable.shape_circle_stroke_grey;
        int icon = 0;

        if (ranking == 1) {
            shape = R.drawable.shape_circle_stroke_yellow;
            icon = R.drawable.ic_crown_color;
        }

        if (ranking == 2) {
            shape = R.drawable.shape_circle_stroke_orange;
            icon = R.drawable.ic_second_color;
        }

        if (ranking == 3) {
            shape = R.drawable.shape_circle_stroke_blue;
            icon = R.drawable.ic_third_color;
        }

        binding.artisanFrame.setBackgroundResource(shape);
        binding.artisanIcon.setBackgroundResource(icon);
    }

    /**
     * Checks if user is sign in or if user is authenticated if it's the page owner
     * if false: hides extra menu button
     * if true: shows extra button with menu options
     */
    private void handleExtraBtnState() {
        artisanPageViewModel.getArtisan().observe(getViewLifecycleOwner(), artisan -> {
            User user = sharedUserViewModel.getUserLiveData().getValue();

            if (user == null || user.getType() != UserTypes.ARTISAN || !user.getUid().equals(artisan.getAssociatedUser())) {
                binding.btnExtra.setVisibility(View.GONE);
            } else {
                binding.btnExtra.setOnClickListener(this::showPopup);
            }
        });
    }

    /**
     * Shows corresponding pop up menu
     * @param v
     */
    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(requireContext(), v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.artisan_menu);
        popup.show();
    }

    /**
     * Checks which menu option was clicked then goes to corresponding page
     * @param item Pop-up menu clicked item
     * @return
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit_artisan:
                // TODO: add artisan navigation to edit artisan page
                return false;
            case R.id.menu_payment_plans:
                // TODO: add artisan navigation to payment plans page
                return true;
            case R.id.menu_boosts:
                // TODO: add artisan navigation to boosts page
                return true;
            default:
                return false;
        }
    }

    /**
     * Fetches all current artisan reviews
     */
    private void fetchReviews() {
        artisanPageViewModel.fetchReviews(artisanId);
    }
}
