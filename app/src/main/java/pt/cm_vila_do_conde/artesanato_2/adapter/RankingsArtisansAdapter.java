package pt.cm_vila_do_conde.artesanato_2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.viewholder.RankingViewHolder;
import pt.cm_vila_do_conde.artesanato_2.model.Artisan;


public class RankingsArtisansAdapter extends RecyclerView.Adapter<RankingViewHolder> {
    private ArrayList<Artisan> artisanList;
    private NavController navController;

    public RankingsArtisansAdapter(ArrayList<Artisan> artisanList, NavController navController) {
        this.artisanList = artisanList;
        this.navController = navController;
    }

    @NonNull
    @Override
    public RankingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_ranking, parent, false);
        return new RankingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RankingViewHolder holder, int position) {
        Artisan artisan = artisanList.get(position);
        holder.name.setText(artisan.getName());
        holder.position.setText(String.valueOf(artisan.getRanking()));
        holder.reputation.setText(String.valueOf(artisan.getReputation()));
        Picasso.get().load(artisan.getProfilePic())
                .placeholder(R.drawable.ic_placeholder_image_color)
                .fit()
                .centerCrop()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return artisanList.size();
    }
}
