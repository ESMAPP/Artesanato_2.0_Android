package pt.cm_vila_do_conde.artesanato_2.adapter;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.viewholder.ArtisanReviewsViewHolder;
import pt.cm_vila_do_conde.artesanato_2.adapter.viewholder.ArtisanViewHolder;
import pt.cm_vila_do_conde.artesanato_2.model.Artisan;
import pt.cm_vila_do_conde.artesanato_2.model.Review;
import pt.cm_vila_do_conde.artesanato_2.model.User;
import pt.cm_vila_do_conde.artesanato_2.repository.ArtisanRepository;
import pt.cm_vila_do_conde.artesanato_2.viewmodel.ArtisanPageViewModel;


public class ArtisanReviewsAdapter extends RecyclerView.Adapter<ArtisanReviewsViewHolder> {
    private List<Review> reviews;
    private User currentUser;
    private Artisan artisan;
    private NavController navController;

    private ArtisanRepository artisanRepository;

    public ArtisanReviewsAdapter(List<Review> reviews, User currentUser, Artisan artisan) {
        this.reviews = reviews;
        this.currentUser = currentUser;
        this.artisan = artisan;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArtisanReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_artisan_review, parent, false);
        artisanRepository = new ArtisanRepository();
        return new ArtisanReviewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtisanReviewsViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.text.setText(review.getMessage());
        holder.dateText.setText(DateUtils
                .getRelativeDateTimeString(holder.dateText.getContext(),
                        review.getCreatedAt().toDate().getTime(),
                        DateUtils.DAY_IN_MILLIS,
                        DateUtils.YEAR_IN_MILLIS,
                        0));
        holder.likes.setText(String.valueOf(review.getLikes().size()));

        if (review.getUserInfo() != null) {
            holder.userName.setText(review.getUserInfo().getName());
            Picasso.get().load(review.getUserInfo().getProfilePic())
                    .placeholder(R.drawable.ic_placeholder_user_pic)
                    .fit()
                    .transform(new CropCircleTransformation())
                    .centerCrop()
                    .into(holder.userImage);
        }

        holder.likeBtn.setOnClickListener(v -> likeReview(review));
    }

    private void likeReview(Review review){
        artisanRepository.likeReview(currentUser, artisan, review);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }
}
