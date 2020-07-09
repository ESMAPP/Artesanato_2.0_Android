package pt.cm_vila_do_conde.artesanato_2.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import pt.cm_vila_do_conde.artesanato_2.R;

public class NotificationViewHolder extends RecyclerView.ViewHolder {
    public TextView notificationDesc;
    public TextView time;
    public ImageView image;
    public CardView cardView;

    public NotificationViewHolder(@NonNull View itemView) {
        super(itemView);
        notificationDesc = itemView.findViewById(R.id.notification_message);
        time = itemView.findViewById(R.id.notification_time);
        image = itemView.findViewById(R.id.notification_pic);
        cardView = itemView.findViewById(R.id.notification_single);
    }
}
