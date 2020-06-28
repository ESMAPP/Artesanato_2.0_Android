package pt.cm_vila_do_conde.artesanato_2.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

import pt.cm_vila_do_conde.artesanato_2.model.Artisan;

public class ArtisanRepository {
    private String TAG = "ARTISANS_REPOSITORY";
    private FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private CollectionReference artisansRef = rootRef.collection("artisans");


    // TODO implement search and filtering query
    public MutableLiveData<ArrayList<Artisan>> fetchArtisansList(Query query) {
        MutableLiveData<ArrayList<Artisan>> artisansList = new MutableLiveData<>();
        query.addSnapshotListener((task, e) -> {
            ArrayList<Artisan> fetchedArtisans = new ArrayList<>();
            for (DocumentSnapshot doc : task.getDocuments()) {
                Log.d(TAG, doc.getId());
                Artisan artisan = doc.toObject(Artisan.class);
                artisan.setUid(doc.getId());
                fetchedArtisans.add(artisan);
            }
            artisansList.setValue(fetchedArtisans);
        });

        return artisansList;
    }

    public MutableLiveData<Artisan> fetchArtisanById (String id){
        MutableLiveData<Artisan> artisan = new MutableLiveData<>();

        artisansRef.document(id).addSnapshotListener((doc, e) -> {
            Artisan fetchedArtisan = doc.toObject(Artisan.class);
            artisan.setValue(fetchedArtisan);
        });

        return artisan;
    }
}
