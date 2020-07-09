package pt.cm_vila_do_conde.artesanato_2.adapter.viewholder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import pt.cm_vila_do_conde.artesanato_2.R;


public class EventViewHolder extends RecyclerView.ViewHolder {
    public ImageView image;
    public TextView title;
    public TextView date;
    public CardView card;
    public ImageButton btnFavourite;

    public EventViewHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image_event_cover);
        title = itemView.findViewById(R.id.text_event_title);
        date = itemView.findViewById(R.id.text_event_date);
        card = itemView.findViewById(R.id.card_event);
        btnFavourite = itemView.findViewById(R.id.button_add_favourite);
    }
}
