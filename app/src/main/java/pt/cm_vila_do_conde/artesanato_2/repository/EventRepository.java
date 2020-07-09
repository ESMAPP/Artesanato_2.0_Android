package pt.cm_vila_do_conde.artesanato_2.repository;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

import pt.cm_vila_do_conde.artesanato_2.model.Event;


public class EventRepository {
    private String TAG = "EVENT_REPOSITORY";

    private FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private CollectionReference eventsRef = rootRef.collection("events");
    private CollectionReference usersRef = rootRef.collection("users");

    // TODO: implement search and filtering query
    public MutableLiveData<ArrayList<Event>> fetchEventsList(Query query) {
        MutableLiveData<ArrayList<Event>> eventsList = new MutableLiveData<>();

        query.orderBy("startDate", Query.Direction.DESCENDING).addSnapshotListener((task, e) -> {
            ArrayList<Event> fetchedEvents = new ArrayList<>();
            for (DocumentSnapshot doc : task.getDocuments()) {
                Event event = doc.toObject(Event.class);
                event.setUid(doc.getId());
                System.out.println(event.getUid());
                fetchedEvents.add(event);
            }
            eventsList.setValue(fetchedEvents);
        });

        return eventsList;
    }

    public MutableLiveData<Event> fetchEventById(String id) {
        MutableLiveData<Event> event = new MutableLiveData<>();

        eventsRef.document(id).addSnapshotListener((doc, e) -> {
            System.out.println(id);
            System.out.println(doc);

            Event fetchedEvent = doc.toObject(Event.class);
            fetchedEvent.setUid(doc.getId());
            event.setValue(fetchedEvent);
        });

        return event;
    }

    // TODO: create model Comment
    // TODO: add fetched comments
    /*
    public MutableLiveData<List<Event>> fetchedComments(String eventId) {
        MutableLiveData<List<Event>> fetchedComments = new MutableLiveData<>();

        eventsRef.document(eventId).collection("comments")
                .addSnapshotListener((task, e) -> {
                    List<Comment> tempComments = task.toObjects(Comment.class);
                    for (Comment comment : tempComments) {
                        usersRef.document(comment.getUserId()).addSnapshotListener((userTask, err) -> {
                            User user = userTask.toObject(User.class);
                            comment.setUserInfo(user);
                        });
                    }
                    fetchedComments.setValue(tempComments);
                });

        return fetchedComments;
    }*/

    // TODO: add submit comments
    /*
    public void submitComment(String text, String userId, String eventId) {
        Comment comment = new Comment(userId, text);
        eventsRef.document(eventId).collection("comments").add(comment);
    }*/
}
