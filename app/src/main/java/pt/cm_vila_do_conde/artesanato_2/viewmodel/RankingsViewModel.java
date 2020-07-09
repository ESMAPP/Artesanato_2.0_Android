package pt.cm_vila_do_conde.artesanato_2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import pt.cm_vila_do_conde.artesanato_2.model.Artisan;
import pt.cm_vila_do_conde.artesanato_2.model.User;
import pt.cm_vila_do_conde.artesanato_2.repository.ArtisanRepository;
import pt.cm_vila_do_conde.artesanato_2.repository.UserRepository;


public class RankingsViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Artisan>> artisansList;
    private MutableLiveData<ArrayList<Artisan>> artisansTopList;
    private MutableLiveData<ArrayList<User>> usersList;
    private MutableLiveData<ArrayList<User>> usersTopList;

    private ArtisanRepository artisanRepository;
    private UserRepository userRepository;

    private FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private CollectionReference artisansRef = rootRef.collection("artisans");
    private CollectionReference usersRef = rootRef.collection("users");

    public RankingsViewModel(@NonNull Application application) {
        super(application);
        artisanRepository = new ArtisanRepository();
        userRepository = new UserRepository();
    }

    public void fetchArtisans() {
        artisansList = artisanRepository.fetchArtisansList(artisansRef.orderBy("ranking"));
    }

    public void fetchTopArtisans() {
        artisansTopList = artisanRepository.fetchArtisansList(artisansRef.orderBy("ranking").limit(3));
    }

    public void fetchUsers() {
        usersList = userRepository.fetchUsers(usersRef.orderBy("ranking").whereGreaterThan("ranking", 0));
    }

    public void fetchTopUsers() {
        usersTopList = userRepository.fetchUsers(usersRef.orderBy("ranking").whereGreaterThan("ranking", 0).limit(3));
    }

    public LiveData<ArrayList<Artisan>> getArtisans() {
        return artisansList;
    }

    public LiveData<ArrayList<Artisan>> getTopArtisans() {
        return artisansTopList;
    }

    public LiveData<ArrayList<User>> getUsers() {
        return usersList;
    }

    public LiveData<ArrayList<User>> getTopUsers() {
        return usersTopList;
    }
}
