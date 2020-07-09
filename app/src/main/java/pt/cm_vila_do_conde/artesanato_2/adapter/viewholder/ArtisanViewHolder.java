package pt.cm_vila_do_conde.artesanato_2.adapter.viewholder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import pt.cm_vila_do_conde.artesanato_2.R;


public class ArtisanViewHolder extends RecyclerView.ViewHolder {
    public ImageView image;
    public TextView name;
    public TextView city;
    public CardView card;
    public ImageButton favouriteBtn;

    public ArtisanViewHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.card_artisan_cover);
        name = itemView.findViewById(R.id.card_artisan_name);
        city = itemView.findViewById(R.id.card_artisan_city);
        card = itemView.findViewById(R.id.card_holder_artisan);
    }
}
