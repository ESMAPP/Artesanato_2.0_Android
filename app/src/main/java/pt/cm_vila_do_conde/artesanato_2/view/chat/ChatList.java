package pt.cm_vila_do_conde.artesanato_2.view.chat;

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

import java.util.List;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.ChatListAdapter;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentChatListBinding;
import pt.cm_vila_do_conde.artesanato_2.model.ChatRoom;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.ChatViewModel;

public class ChatList extends Fragment {
    private static final String TAG = "CHAT_FRAGMENT";
    private static final String ARTISAN_ID = "artisanId";

    private ChatViewModel chatViewModel;
    private NavController navController;

    private FragmentChatListBinding binding;

    private String artisanId;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            artisanId = getArguments().getString(ARTISAN_ID);
        }
        initChatViewModel();
        fetchChatList();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentChatListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNavController();
        initObservable();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initChatViewModel() {
        chatViewModel = new ViewModelProvider(requireActivity()).get(ChatViewModel.class);
    }

    private void initNavController() {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
    }

    private void fetchChatList() {
        chatViewModel.fetchChatList(artisanId);
    }

    private void initObservable() {
        chatViewModel.getChatList().observe(getViewLifecycleOwner(), this::updateUi);
    }

    private void updateUi(List<ChatRoom> chatList) {
        RecyclerView recyclerView = binding.chatListRecycler;
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        ChatListAdapter adapter = new ChatListAdapter(chatList, navController);
        recyclerView.setAdapter(adapter);
        recyclerView.post(adapter::notifyDataSetChanged);
    }
}
