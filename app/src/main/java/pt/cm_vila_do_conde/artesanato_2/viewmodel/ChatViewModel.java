package pt.cm_vila_do_conde.artesanato_2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import pt.cm_vila_do_conde.artesanato_2.model.Artisan;
import pt.cm_vila_do_conde.artesanato_2.model.ChatRoom;
import pt.cm_vila_do_conde.artesanato_2.model.Message;
import pt.cm_vila_do_conde.artesanato_2.model.User;
import pt.cm_vila_do_conde.artesanato_2.repository.ChatRepository;

public class ChatViewModel extends AndroidViewModel {
    private MutableLiveData<ChatRoom> chatRoom;
    private MutableLiveData<List<ChatRoom>> chatList;
    private MutableLiveData<List<Message>> messages;
    private MutableLiveData<User> user;
    private MutableLiveData<Artisan> artisan;
    private ChatRepository chatRepository;


    public ChatViewModel(@NonNull Application application) {
        super(application);
        chatRepository = new ChatRepository();
    }

    public void fetchChatRoom(String artisanId, String userId){
        chatRoom = chatRepository.fetchChatRoom(artisanId, userId);
    }

    public void fetchUserInfo(String userId){
        user = chatRepository.fetchUserInfo(userId);
    }

    public void fetchArtisanInfo(String artisanId){
        artisan = chatRepository.fetchArtisanInfo(artisanId);
    }

    public void fetchChatList(String artisanId){
        chatList = chatRepository.fetchChatList(artisanId);
    }

    public void fetchMessages(String chatId){
        messages = chatRepository.fetchMessages(chatId);
    }

    public void sendMessage(String chatId, Message message){
        chatRepository.sendMessage(chatId, message);
    }

    public MutableLiveData<List<ChatRoom>> getChatList() {
        return chatList;
    }

    public MutableLiveData<ChatRoom> getChatRoom() {
        return chatRoom;
    }

    public MutableLiveData<List<Message>> getMessages() {
        return messages;
    }

    public MutableLiveData<User> getUser() {
        return user;
    }

    public MutableLiveData<Artisan> getArtisan() {
        return artisan;
    }
}
