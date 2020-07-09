package pt.cm_vila_do_conde.artesanato_2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.viewholder.ProfileBagdesViewHolder;
import pt.cm_vila_do_conde.artesanato_2.model.Badge;


public class ProfileBadgesAdapter extends RecyclerView.Adapter<ProfileBagdesViewHolder> {
    private ArrayList<String> userBadges;
    private List<Badge> badgesList;

    private NavController navController;

    public ProfileBadgesAdapter(ArrayList<String> userBadges, List<Badge> badgesList) {
        this.userBadges = userBadges;
        this.badgesList = badgesList;
    }

    @NonNull
    @Override
    public ProfileBagdesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_profile_badge, parent, false);
        return new ProfileBagdesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileBagdesViewHolder holder, int position) {
        Picasso.get().load(badgesList.get(position).getIcon())
                .placeholder(R.drawable.ic_placeholder_badge_image_grey)
                .resize(300 , 300)
                .transform(new CropCircleTransformation())
                .into(holder.image);
        for (String userBadgeId : userBadges) {
            if(userBadgeId.equals(badgesList.get(position).getUid())){
                holder.image.animate().alpha(1);
            }
        }
    }

    @Override
    public int getItemCount() {
        return badgesList.size();
    }
}
