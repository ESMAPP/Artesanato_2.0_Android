package pt.cm_vila_do_conde.artesanato_2.adapter;

import android.content.Context;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.viewholder.ChatListViewHolder;
import pt.cm_vila_do_conde.artesanato_2.model.ChatRoom;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListViewHolder> {
    private List<ChatRoom> chatList;
    private NavController navController;
    private Context context;

    public ChatListAdapter(List<ChatRoom> chatList, NavController navController, Context context) {
        this.chatList = chatList;
        this.navController = navController;
        this.context = context;
    }

    @NonNull
    @Override
    public ChatListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_chat_list, parent, false);
        return new ChatListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatListViewHolder holder, int position) {
        ChatRoom chatRoom = chatList.get(position);
        holder.userName.setText(chatRoom.getUserInfo() != null ? chatRoom.getUserInfo().getName() : "...");
        if(chatRoom.getUserInfo() != null && chatRoom.getUserInfo().getProfilePic() != null){
            Picasso.get().load(chatRoom.getUserInfo().getProfilePic())
                    .placeholder(R.drawable.ic_placeholder_image_color)
                    .fit()
                    .centerCrop()
                    .transform(new CropCircleTransformation())
                    .into(holder.userImage);
        }

        if(chatRoom.getLastMessage() != null) {
            holder.lastMessageDate.setText(DateUtils.getRelativeDateTimeString(context,
                    chatRoom.getLastMessage().getCreatedAt().toDate().getTime(),
                    DateUtils.MINUTE_IN_MILLIS,
                    DateUtils.YEAR_IN_MILLIS,
                    0));
            holder.lastMessageText.setText(chatRoom.getLastMessage().getText());
        }

        holder.cardView.setOnClickListener(v -> navigateToChat(chatRoom.getArtisanId(), chatRoom.getUserId()));
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    private void navigateToChat(String artisanId, String userId){
        Bundle bundle = new Bundle();
        bundle.putString("userId", userId);
        bundle.putString("artisanId", artisanId);
        navController.navigate(R.id.action_chatList_to_chatFragment, bundle);
    }
}
