package pt.cm_vila_do_conde.artesanato_2.adapter.viewholder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import pt.cm_vila_do_conde.artesanato_2.R;

public class ChatMessagesViewHolder extends RecyclerView.ViewHolder{
    public TextView messageText;
    public LinearLayout linearLayout;
    public TextView createdAtText;

    public ChatMessagesViewHolder(@NonNull View itemView) {
        super(itemView);
        messageText = itemView.findViewById(R.id.chat_message);
        linearLayout = itemView.findViewById(R.id.message_holder);
    }
}
