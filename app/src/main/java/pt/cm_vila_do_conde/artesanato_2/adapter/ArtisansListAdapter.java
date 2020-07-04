package pt.cm_vila_do_conde.artesanato_2.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.viewholder.ArtisanViewHolder;
import pt.cm_vila_do_conde.artesanato_2.model.Artisan;


public class ArtisansListAdapter extends RecyclerView.Adapter<ArtisanViewHolder> {
    private ArrayList<Artisan> artisanList;
    private NavController navController;

    public ArtisansListAdapter(ArrayList<Artisan> artisanList, NavController navController) {
        this.artisanList = artisanList;
        this.navController = navController;
    }

    @NonNull
    @Override
    public ArtisanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_artisan_list, parent, false);
        return new ArtisanViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtisanViewHolder holder, int position) {
        Artisan artisan = artisanList.get(position);
        holder.name.setText(artisan.getName());
        holder.city.setText(artisan.getCity());
        Picasso.get().load(artisan.getProfilePic())
                .placeholder(R.drawable.ic_placeholder_image_color)
                .fit()
                .centerCrop()
                .into(holder.image);
        holder.card.setOnClickListener(v -> goToArtisanPageById(artisan.getUid()));
    }

    public void goToArtisanPageById(String id) {
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        navController.navigate(R.id.artisanPageFragment, bundle);
    }

    @Override
    public int getItemCount() {
        return artisanList.size();
    }
}
