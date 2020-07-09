package pt.cm_vila_do_conde.artesanato_2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import pt.cm_vila_do_conde.artesanato_2.model.Notification;
import pt.cm_vila_do_conde.artesanato_2.repository.NotificationsRepository;

public class NotificationsViewModel extends AndroidViewModel {
    private NotificationsRepository notificationsRepository;

    private MutableLiveData<List<Notification>> notifications;

    public NotificationsViewModel(@NonNull Application application) {
        super(application);
        notificationsRepository = new NotificationsRepository();
    }

    public void fetchUserNotifications(String userId){
        notifications = notificationsRepository.fetchUserNotifications(userId);
    }

    public MutableLiveData<List<Notification>> getNotifications() {
        return notifications;
    }
}
