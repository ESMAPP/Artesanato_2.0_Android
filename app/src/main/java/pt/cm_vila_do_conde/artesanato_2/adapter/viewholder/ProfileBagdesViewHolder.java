package pt.cm_vila_do_conde.artesanato_2.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pt.cm_vila_do_conde.artesanato_2.R;


public class ProfileBagdesViewHolder extends RecyclerView.ViewHolder {
    public ImageView image;

    // TODO enable when textview badges pop up card is created
    //public final String description, label, type;
    public TextView description, label, type;

    public ProfileBagdesViewHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.profile_badge);
    }
}
