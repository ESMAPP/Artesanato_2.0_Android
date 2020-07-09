package pt.cm_vila_do_conde.artesanato_2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import pt.cm_vila_do_conde.artesanato_2.model.Event;
import pt.cm_vila_do_conde.artesanato_2.repository.EventRepository;


public class EventPageViewModel extends AndroidViewModel {
    // TODO: public MutableLiveData<List<Comment>> comments;
    private MutableLiveData<Event> event;

    private EventRepository eventRepository;

    public EventPageViewModel(@NonNull Application application) {
        super(application);
        eventRepository = new EventRepository();
    }

    public void fetchEventById(String id) {
        event = eventRepository.fetchEventById(id);
    }

    /* TODO: fetchComments
    public void fetchComments(String artisanId) {
        comments = eventRepository.fetchReviews(artisanId);
    }
     */

    /* TODO: submitComment
    public void submitComment(String text, String userId, String artisanId) {
        eventRepository.submitComment(text, userId, artisanId);
    }
     */

    public MutableLiveData<Event> getEvent() {
        return event;
    }

    /* TODO: getComments
    public MutableLiveData<List<Comment>> getComments() {
        return comments;
    }
     */
}
