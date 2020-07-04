package pt.cm_vila_do_conde.artesanato_2.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pt.cm_vila_do_conde.artesanato_2.R;

public class ProfileBagdesViewHolder extends RecyclerView.ViewHolder {
    public ImageView image;
    /*public final String description;
    public final String label;
    public final String type;
     */

    public ProfileBagdesViewHolder(@NonNull View itemView) {
        super(itemView);
        image = imageView.findViewById(R.id.badgesList);
    }
}
