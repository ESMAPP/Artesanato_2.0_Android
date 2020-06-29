package pt.cm_vila_do_conde.artesanato_2.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import pt.cm_vila_do_conde.artesanato_2.R;

public class ArtisanGalleryViewHolder extends RecyclerView.ViewHolder {
    public final ImageView mImageView;

    public ArtisanGalleryViewHolder(View view) {
        super(view);
        mImageView = (ImageView) view.findViewById(R.id.gallery_pic);
    }
}
