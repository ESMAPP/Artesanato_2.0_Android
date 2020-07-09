package pt.cm_vila_do_conde.artesanato_2.view.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.ArtisanReviewsAdapter;
import pt.cm_vila_do_conde.artesanato_2.adapter.NotificationsAdapter;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentNotificationsBinding;
import pt.cm_vila_do_conde.artesanato_2.model.Notification;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.NotificationsViewModel;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.SharedUserViewModel;


public class NotificationsFragment extends Fragment {
    private FragmentNotificationsBinding binding;
    private NavController navController;

    private NotificationsViewModel notificationsViewModel;
    private SharedUserViewModel sharedUserViewModel;


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNavController();
        initBackBtn();
        initNotificationsViewModel();
        initUserViewModel();
        fetchNotifications();
        initClearBtn();
    }

    private void initNavController() {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
    }

    private void initBackBtn() {
        binding.btnBack.setOnClickListener(v -> navController.popBackStack());
    }

    private void initNotificationsViewModel() {
        notificationsViewModel = new ViewModelProvider(requireActivity()).get(NotificationsViewModel.class);
    }

    private void initUserViewModel() {
        sharedUserViewModel = new ViewModelProvider(requireActivity()).get(SharedUserViewModel.class);
    }

    private void fetchNotifications() {
        sharedUserViewModel.getUserLiveData().observe(getViewLifecycleOwner(), user -> {
            notificationsViewModel.fetchUserNotifications(user.getUid());
            notificationsViewModel.getNotifications().observe(getViewLifecycleOwner(), this::updateUi);
        });
    }

    private void initClearBtn(){
        binding.btnClear.setOnClickListener(v -> {
            sharedUserViewModel.getUserLiveData().observe(getViewLifecycleOwner(), user -> {
                notificationsViewModel.clearNotifications(user.getUid());
            });
        });
    }

    private void updateUi(List<Notification> notificationList) {
        RecyclerView recyclerView = binding.notificationsList;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        NotificationsAdapter adapter = new NotificationsAdapter(notificationList, navController);
        recyclerView.setAdapter(adapter);
        recyclerView.post(adapter::notifyDataSetChanged);
        if(notificationList.size() == 0) {
            binding.emptyNotificationsImage.setVisibility(View.VISIBLE);
        } else {
            binding.emptyNotificationsImage.setVisibility(View.GONE);
        }

    }
}
