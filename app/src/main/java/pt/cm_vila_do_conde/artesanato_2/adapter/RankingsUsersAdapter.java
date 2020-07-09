package pt.cm_vila_do_conde.artesanato_2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.viewholder.RankingViewHolder;
import pt.cm_vila_do_conde.artesanato_2.model.User;


public class RankingsUsersAdapter extends RecyclerView.Adapter<RankingViewHolder> {
    private List<User> userList;

    public RankingsUsersAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public RankingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_ranking, parent, false);
        return new RankingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RankingViewHolder holder, int position) {
        User user = userList.get(position);

        holder.name.setText(user.getName());
        holder.position.setText(String.valueOf(user.getRanking()));
        holder.reputation.setText(String.valueOf(user.getReputation()));
        Picasso.get().load(user.getProfilePic())
                .placeholder(R.drawable.ic_placeholder_user_pic)
                .fit()
                .transform(new CropCircleTransformation())
                .centerCrop()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
