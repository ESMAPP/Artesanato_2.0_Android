package pt.cm_vila_do_conde.artesanato_2.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import pt.cm_vila_do_conde.artesanato_2.model.Artisan;
import pt.cm_vila_do_conde.artesanato_2.model.ChatRoom;
import pt.cm_vila_do_conde.artesanato_2.model.Message;
import pt.cm_vila_do_conde.artesanato_2.model.User;

public class ChatRepository {
    private static final String TAG = "CHAT_REPOSITORY";
    private FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private CollectionReference chatsRef = rootRef.collection("chats");
    private CollectionReference usersRef = rootRef.collection("users");
    private CollectionReference artisansRef = rootRef.collection("artisans");

    public MutableLiveData<ChatRoom> fetchChatRoom(String artisanId, String userId) {
        MutableLiveData<ChatRoom> chatRoom = new MutableLiveData<>();
        chatsRef.document(artisanId + userId)
                .addSnapshotListener((doc, e) -> {
                    if (doc.exists()) {
                        ChatRoom fetchedChatRoom = doc.toObject(ChatRoom.class);
                        fetchedChatRoom.setId(doc.getId());
                        chatRoom.postValue(fetchedChatRoom);
                    } else {
                        createChatRoom(artisanId, userId);
                    }
                });
        return chatRoom;
    }

    private void createChatRoom(String artisanId, String userId) {
        String docId = artisanId + userId;
        ChatRoom newChatRoom = new ChatRoom(docId, artisanId, userId);
        chatsRef.document(newChatRoom.getId()).set(newChatRoom).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d(TAG, "CREATED CHATROOM!");
            }
        });
    }

    public MutableLiveData<List<Message>> fetchMessages(String chatId) {
        MutableLiveData<List<Message>> messagesList = new MutableLiveData<>();

        chatsRef.document(chatId)
                .collection("messages")
                .orderBy("createdAt", Query.Direction.ASCENDING)
                .addSnapshotListener((queryDocumentSnapshots, e) -> {
                    List<Message> tempMessages = new ArrayList<>();
                    for (DocumentSnapshot message : queryDocumentSnapshots.getDocuments() ) {
                        tempMessages.add(message.toObject(Message.class));
                    }
                    messagesList.postValue(tempMessages);
                });

        return messagesList;
    }

    public boolean sendMessage(String chatId, Message message) {
        AtomicBoolean messageSent = new AtomicBoolean(false);

        chatsRef.document(chatId)
                .collection("messages")
                .add(message)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        messageSent.set(true);
                    }
                });

        return messageSent.get();
    }

    public MutableLiveData<User> fetchUserInfo(String userId) {
        MutableLiveData<User> user = new MutableLiveData<>();
        usersRef.document(userId).addSnapshotListener((documentSnapshot, e) -> {
            User fetchedUser = documentSnapshot.toObject(User.class);
            fetchedUser.setUid(documentSnapshot.getId());
            user.postValue(fetchedUser);
        });
        return user;
    }

    public MutableLiveData<Artisan> fetchArtisanInfo(String artisanId){
        MutableLiveData<Artisan> artisan = new MutableLiveData<>();
        artisansRef.document(artisanId).addSnapshotListener((documentSnapshot, e) -> {
            Artisan fetchedArtisan = documentSnapshot.toObject(Artisan.class);
            fetchedArtisan.setUid(documentSnapshot.getId());
            artisan.postValue(fetchedArtisan);
        });
        return artisan;
    }
}
