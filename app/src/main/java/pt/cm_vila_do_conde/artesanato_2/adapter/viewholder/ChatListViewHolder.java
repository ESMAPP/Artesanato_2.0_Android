package pt.cm_vila_do_conde.artesanato_2.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import pt.cm_vila_do_conde.artesanato_2.R;

public class ChatListViewHolder extends RecyclerView.ViewHolder {
    public CardView cardView;
    public TextView userName;
    public ImageView userImage;
    public TextView lastMessageDate;
    public TextView lastMessageText;

    public ChatListViewHolder(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.chat_card);
        userName = itemView.findViewById(R.id.chat_user_name);
        userImage = itemView.findViewById(R.id.chat_user_pic);
        lastMessageDate = itemView.findViewById(R.id.chat_last_time);
        lastMessageText = itemView.findViewById(R.id.last_message_text);
    }
}
