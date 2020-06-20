package pt.cm_vila_do_conde.artesanato_2.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.databinding.HomeCardviewBinding;
import pt.cm_vila_do_conde.artesanato_2.model.Event;
import pt.cm_vila_do_conde.artesanato_2.model.User;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder> {

    private static String TAG = "HOME_RECYCLER_ADAPTER";

    private ArrayList<?> featuredList;
    private HomeCardviewBinding binding;

    public HomeRecyclerAdapter(ArrayList<?> featuredList)
    {
        //this.binding = binding;
        this.featuredList = featuredList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        protected TextView title;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            title = itemView.findViewById(R.id.cardTitle);
        }
    }

    @NonNull
    @Override
    public HomeRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_cardview, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        /*User item = (User) featuredList.get(position);

        holder.title.setText(item.getName());*/

        if(featuredList.get(position) instanceof Event){
            Event item = (Event) featuredList.get(position);
            holder.title.setText(item.getTitle());
        }


    }

    @Override
    public int getItemCount()
    {
        return featuredList.size();
    }
}
