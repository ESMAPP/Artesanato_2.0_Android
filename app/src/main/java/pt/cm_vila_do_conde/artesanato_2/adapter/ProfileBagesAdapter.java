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
import pt.cm_vila_do_conde.artesanato_2.adapter.viewholder.BagdesViewHolder;
import pt.cm_vila_do_conde.artesanato_2.model.Artisan;
import pt.cm_vila_do_conde.artesanato_2.model.Badge;

public class ProfileBagesAdapter extends RecyclerView.Adapter<BagdesViewHolder>  {

    private ArrayList<Badge> badgesList;
    private NavController navController;

    public ProfileBagesAdapter(ArrayList<Badge> badgesList, NavController navController) {
        this.badgesList = badgesList;
        this.navController = navController;
    }

    @NonNull
    @Override
    public BagdesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View imageView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_artisan_list/*change cardview*/, parent, false);
        return new BadgesViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(@NonNull BagdesViewHolder holder, int position) {

        Badge badge = badgesList.get(position);
        holder.description.setText(badge.getDescription());
        holder.label.setText(badge.getLabel());
        Picasso.get().load(badge.getIcon())
                .placeholder(R.drawable.ic_logo_small)
                .fit()
                .centerInside()
                .into(holder.icon);
        holder.card.setOnClickListener(v -> goToBadgePageById(badge.getUid()));
    }

    public void goToBadgePageById(String id){
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        navController.navigate(R.id.ProfileFragment, bundle);
    }

    @Override
    public int getItemCount() {
        return badgesList.size();
    }
}
