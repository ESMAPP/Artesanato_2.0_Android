package pt.cm_vila_do_conde.artesanato_2.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pt.cm_vila_do_conde.artesanato_2.R;


public class ArtisanGalleryViewHolder extends RecyclerView.ViewHolder {
    public ImageView image;

    public ArtisanGalleryViewHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.gallery_pic);
    }
}
