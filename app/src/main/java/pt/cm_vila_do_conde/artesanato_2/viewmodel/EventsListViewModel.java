package pt.cm_vila_do_conde.artesanato_2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

import pt.cm_vila_do_conde.artesanato_2.model.Event;
import pt.cm_vila_do_conde.artesanato_2.repository.EventRepository;


public class EventsListViewModel extends AndroidViewModel {
    public MutableLiveData<Query> query = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Event>> eventsList;
    private EventRepository eventRepository;
    private FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private CollectionReference eventsRef = rootRef.collection("events");

    public EventsListViewModel(@NonNull Application application) {
        super(application);
        eventRepository = new EventRepository();
        query.postValue(eventsRef);
    }

    public void fetchEventsList(Query query) {
        eventsList = eventRepository.fetchEventsList(query);
    }

    public LiveData<Query> getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query.postValue(query);
    }

    public LiveData<ArrayList<Event>> getEventsList() {
        return eventsList;
    }
}
