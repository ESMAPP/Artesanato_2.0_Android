package pt.cm_vila_do_conde.artesanato_2.adapter;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.adapter.viewholder.NotificationViewHolder;
import pt.cm_vila_do_conde.artesanato_2.model.Notification;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationViewHolder> {
    private List<Notification> notifications;
    private NavController navController;

    public NotificationsAdapter(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public NotificationsAdapter(List<Notification> notifications, NavController navController) {
        this.notifications = notifications;
        this.navController = navController;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_notification_single, parent, false);
        return new NotificationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Notification notification = notifications.get(position);
        holder.notificationDesc.setText(notification.getMessage());
        holder.time.setText(DateUtils
                .getRelativeDateTimeString(holder.time.getContext(),
                        notification.getTimestamp().toDate().getTime(),
                        DateUtils.DAY_IN_MILLIS,
                        DateUtils.YEAR_IN_MILLIS,
                        0));
        handleImageType(holder, notification);
        holder.cardView.setOnClickListener(v -> handleNavigationType(holder, notification));
    }

    private void handleImageType(NotificationViewHolder holder, Notification notification) {
        switch (notification.getType()) {
            case 1: {
                Picasso.get().load(R.drawable.ic_placeholder_heart_color)
                        .fit()
                        .transform(new CropCircleTransformation())
                        .centerCrop()
                        .into(holder.image);
            }
            break;
            default:
                break;
        }
    }

    private void handleNavigationType(NotificationViewHolder holder, Notification notification) {
        Bundle bundle = new Bundle();
        switch (notification.getAction()) {
            case 1: {
                bundle.putString("id", notification.getDestination());
                navController.navigate(R.id.artisanPageFragment, bundle);
            }
            break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }
}
