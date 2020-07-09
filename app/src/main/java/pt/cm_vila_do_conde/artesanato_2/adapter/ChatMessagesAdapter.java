package pt.cm_vila_do_conde.artesanato_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.viewholder.ChatMessagesViewHolder;
import pt.cm_vila_do_conde.artesanato_2.model.Message;

public class ChatMessagesAdapter extends RecyclerView.Adapter<ChatMessagesViewHolder> {
    private List<Message> messages;
    private String userId;
    private Context context;

    public ChatMessagesAdapter(List<Message> messages, String userId, Context context) {
        this.messages = messages;
        this.userId = userId;
        this.context = context;
    }

    @NonNull
    @Override
    public ChatMessagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_chat_message, parent, false);
        return new ChatMessagesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatMessagesViewHolder holder, int position) {
        Message message = messages.get(position);
        if(userId.equals(message.getSenderId())){
            holder.linearLayout.setBackgroundResource(R.drawable.shape_message_blue);
        } else {
            holder.linearLayout.setBackgroundResource(R.drawable.shape_message_grey);
        }
        holder.messageText.setText(message.getText());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
