package pt.cm_vila_do_conde.artesanato_2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.viewholder.ArtisanGalleryViewHolder;
import pt.cm_vila_do_conde.artesanato_2.dummy.DummyContent.DummyItem;

public class ArtisanGalleryAdapter extends RecyclerView.Adapter<ArtisanGalleryViewHolder> {
    private ArrayList<String> gallery;
    private NavController navController;

    public ArtisanGalleryAdapter(ArrayList<String> gallery) {
        this.gallery = gallery;
    }

    @Override
    public ArtisanGalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.artisan_gallery_item, parent, false);
        return new ArtisanGalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ArtisanGalleryViewHolder holder, int position) {
        Picasso.get().load(gallery.get(position))
                .placeholder(R.drawable.logo_i)
                .fit()
                .centerCrop()
                .transform(new RoundedCornersTransformation(5, 5))
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return gallery.size();
    }
}