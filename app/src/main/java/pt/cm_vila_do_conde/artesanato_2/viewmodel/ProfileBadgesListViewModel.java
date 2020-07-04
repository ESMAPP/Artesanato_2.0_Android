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
import java.util.List;

import pt.cm_vila_do_conde.artesanato_2.model.Artisan;
import pt.cm_vila_do_conde.artesanato_2.model.Badge;
import pt.cm_vila_do_conde.artesanato_2.repository.ArtisanRepository;
import pt.cm_vila_do_conde.artesanato_2.repository.BadgesRepository;

public class ProfileBadgesListViewModel extends AndroidViewModel {

    private MutableLiveData<List<Badge>> badgesList;
    private BadgesRepository badgesRepository;
    private FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private CollectionReference badgesRef = rootRef.collection("badges");

    public ProfileBadgesListViewModel(@NonNull Application application) {
        super(application);
        badgesRepository = new BadgesRepository();

    }

    public void fetchBadgesList(){
        badgesList = badgesRepository.fetchBadgesList();
    }


    public LiveData<List<Badge>> getBadgesList() {
        return badgesList;
    }
}
