package pt.cm_vila_do_conde.artesanato_2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.viewholder.ProfileBagdesViewHolder;


public class ProfileBadgesAdapter extends RecyclerView.Adapter<ProfileBagdesViewHolder> {
    private ArrayList<String> badges;
    private NavController navController;

    public ProfileBadgesAdapter(ArrayList<String> badges) {
        this.badges = badges;
    }

    @NonNull
    @Override
    public ProfileBagdesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_profile_badge, parent, false);
        return new ProfileBagdesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileBagdesViewHolder holder, int position) {
        Picasso.get().load(badges.get(position))
                .placeholder(R.drawable.ic_placeholder_image_color)
                .transform(new CropCircleTransformation())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return badges.size();
    }
}
