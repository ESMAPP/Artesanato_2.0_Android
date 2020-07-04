package pt.cm_vila_do_conde.artesanato_2.adapter;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.viewholder.ArtisanReviewsViewHolder;
import pt.cm_vila_do_conde.artesanato_2.model.Review;


public class ArtisanReviewsAdapter extends RecyclerView.Adapter<ArtisanReviewsViewHolder> {
    private List<Review> reviews;

    private NavController navController;

    public ArtisanReviewsAdapter(List<Review> reviews) {
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ArtisanReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_artisan_review, parent, false);
        return new ArtisanReviewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtisanReviewsViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.text.setText(review.getText());
        holder.dateText.setText(DateUtils
                .getRelativeDateTimeString(holder.dateText.getContext(),
                        review.getCreatedAt().toDate().getTime(),
                        DateUtils.MINUTE_IN_MILLIS,
                        DateUtils.YEAR_IN_MILLIS,
                        0));
        holder.likes.setText(String.valueOf(review.getLikes().size()));

        if (review.getUserInfo() != null) {
            holder.userName.setText(review.getUserInfo().getName());

            Picasso.get().load(review.getUserInfo().getProfilePic())
                    .placeholder(R.drawable.ic_placeholder_user_pic)
                    .transform(new CropCircleTransformation())
                    .into(holder.userImage);
        }
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }
}
