package pt.cm_vila_do_conde.artesanato_2.adapter;

import android.content.Context;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.viewholder.EventViewHolder;
import pt.cm_vila_do_conde.artesanato_2.model.Event;


public class EventsListAdapter extends RecyclerView.Adapter<EventViewHolder> {
    private ArrayList<Event> eventList;
    private NavController navController;
    private Context context;

    public EventsListAdapter(ArrayList<Event> eventList, NavController navController, Context context) {
        this.eventList = eventList;
        this.navController = navController;
        this.context = context;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_event_list, parent, false);
        return new EventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.title.setText(event.getTitle());

        holder.date.setText(DateUtils.getRelativeDateTimeString(context,
                        event.getStartDate().toDate().getTime(),
                        DateUtils.MINUTE_IN_MILLIS,
                        DateUtils.YEAR_IN_MILLIS,
                        0));

        Picasso.get().load(event.getImage())
                .placeholder(R.drawable.bar_top_placeholder_image)
                .fit()
                .centerCrop()
                .into(holder.image);
        holder.card.setOnClickListener(v -> goToEventPageById(event.getUid()));
    }

    public void goToEventPageById(String id) {
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        navController.navigate(R.id.eventPageFragment, bundle);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
