package pt.cm_vila_do_conde.artesanato_2.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import pt.cm_vila_do_conde.artesanato_2.R;


public class RankingViewHolder extends RecyclerView.ViewHolder {
    public ImageView image;
    public TextView position, name, reputation;

    public RankingViewHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.ranking_user_pic);
        position = itemView.findViewById(R.id.ranking_position);
        name = itemView.findViewById(R.id.ranking_user_name);
        reputation = itemView.findViewById(R.id.ranking_user_reputation);
    }
}
