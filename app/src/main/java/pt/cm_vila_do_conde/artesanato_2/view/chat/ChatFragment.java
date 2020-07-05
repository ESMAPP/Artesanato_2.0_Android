package pt.cm_vila_do_conde.artesanato_2.view.chat;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.ArtisanReviewsAdapter;
import pt.cm_vila_do_conde.artesanato_2.adapter.ChatMessagesAdapter;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentChatBinding;
import pt.cm_vila_do_conde.artesanato_2.model.ChatRoom;
import pt.cm_vila_do_conde.artesanato_2.model.Message;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.ChatViewModel;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.SharedUserViewModel;

public class ChatFragment extends Fragment {
    private static final String ARTISAN_ID = "artisanId";
    private static final String USER_ID = "userId";

    private String artisanId;
    private String userId;
    private String chatId;

    private ChatViewModel chatViewModel;
    private SharedUserViewModel sharedUserViewModel;

    private FragmentChatBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            artisanId = getArguments().getString(ARTISAN_ID);
            userId = getArguments().getString(USER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChatBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initSharedUserViewModel();
        initChatViewModel();
        fetchChatRoom();
        initSubmitBtn();
    }

    private void initSharedUserViewModel() {
        sharedUserViewModel = new ViewModelProvider(requireActivity()).get(SharedUserViewModel.class);
    }

    private void initChatViewModel() {
        chatViewModel = new ViewModelProvider(requireActivity()).get(ChatViewModel.class);
    }

    private void initSubmitBtn() {
        binding.submitMessage.setOnClickListener(v -> {
            sharedUserViewModel.getUserLiveData().observe(getViewLifecycleOwner(), user -> {
                String messageText = binding.messageInput.getText().toString();
                if (messageText.isEmpty()) {
                    binding.messageInput.setError("A mensagem não pode estar vazia.");
                } else {
                    Message messageToSend = new Message(messageText, user.getUid());
                    chatViewModel.sendMessage(chatId, messageToSend);
                }
            });
        });
    }

    private void fetchChatRoom() {
        chatViewModel.fetchChatRoom(artisanId, userId);
        chatViewModel.getChatRoom().observe(getViewLifecycleOwner(), chatRoom -> {
            chatId = chatRoom.getId();
            fetchInfo(chatRoom);
            handleInitialUi(chatRoom);
            fetchMessages(chatRoom.getId());
        });
    }

    private void fetchInfo(ChatRoom chatRoom) {
        chatViewModel.fetchArtisanInfo(chatRoom.getArtisanId());
        chatViewModel.fetchUserInfo(chatRoom.getUserId());
    }

    private void fetchMessages(String chatId) {
        chatViewModel.fetchMessages(chatId);
        chatViewModel.getMessages().observe(getViewLifecycleOwner(), this::updateChatUi);
    }

    private void updateChatUi(List<Message> messages) {
            RecyclerView recyclerView = binding.messagesRecycler;
            recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
            recyclerView.setAdapter(new ChatMessagesAdapter(messages));
    }

    private void handleInitialUi(ChatRoom chatRoom) {
        sharedUserViewModel.getUserLiveData().observe(getViewLifecycleOwner(), user -> {
            chatViewModel.getArtisan().observe(getViewLifecycleOwner(), artisan -> {
                chatViewModel.getUser().observe(getViewLifecycleOwner(), chatUser -> {
                    if (user.getUid().equals(artisan.getAssociatedUser())) {
                        binding.chatArtisanName.setText(chatUser.getName());
                        Picasso.get().load(chatUser.getProfilePic())
                                .placeholder(R.drawable.ic_placeholder_user_pic)
                                .fit()
                                .transform(new CropCircleTransformation())
                                .centerCrop()
                                .into(binding.chatArtisanPic);
                    } else {
                        binding.chatArtisanName.setText(artisan.getName());
                        Picasso.get().load(artisan.getProfilePic())
                                .placeholder(R.drawable.ic_placeholder_user_pic)
                                .fit()
                                .transform(new CropCircleTransformation())
                                .centerCrop()
                                .into(binding.chatArtisanPic);
                    }
                });
            });
        });
    }

}