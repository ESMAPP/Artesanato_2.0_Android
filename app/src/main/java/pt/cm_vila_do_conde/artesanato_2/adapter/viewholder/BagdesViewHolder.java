package pt.cm_vila_do_conde.artesanato_2.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pt.cm_vila_do_conde.artesanato_2.R;

public class BagdesViewHolder extends RecyclerView.ViewHolder {

    public final ImageView image;
    /*public final String description;
    public final String label;
    public final String type;
     */
    public BagdesViewHolder(@NonNull View imageView) {
        super(imageView);

        image = imageView.findViewById(R.id.badgesList);


    }
}
