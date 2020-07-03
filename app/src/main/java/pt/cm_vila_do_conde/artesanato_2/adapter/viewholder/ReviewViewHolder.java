package pt.cm_vila_do_conde.artesanato_2.adapter.viewholder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pt.cm_vila_do_conde.artesanato_2.R;

public class ReviewViewHolder extends RecyclerView.ViewHolder {
    public ImageView userImage;
    public TextView userName;
    public TextView text, dateText;
    public TextView likes;
    public ImageButton likeBtn;

    public ReviewViewHolder(@NonNull View itemView) {
        super(itemView);
        userImage = itemView.findViewById(R.id.review_pic);
        userName = itemView.findViewById(R.id.review_user);
        text = itemView.findViewById(R.id.review_text);
        dateText = itemView.findViewById(R.id.review_date);
        likes = itemView.findViewById(R.id.likes_text);
        likeBtn = itemView.findViewById(R.id.btn_like);
    }
}
