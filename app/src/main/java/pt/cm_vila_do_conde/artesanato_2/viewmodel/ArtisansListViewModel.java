package pt.cm_vila_do_conde.artesanato_2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pt.cm_vila_do_conde.artesanato_2.model.Artisan;
import pt.cm_vila_do_conde.artesanato_2.repository.ArtisanRepository;

public class ArtisansListViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Artisan>> artisansList;
    private ArtisanRepository artisanRepository;

    public ArtisansListViewModel(@NonNull Application application) {
        super(application);
        artisanRepository = new ArtisanRepository();
    }


    public void fetchArtisansList(Query query){
        artisansList = artisanRepository.fetchArtisansList(query);
    }

    public LiveData<ArrayList<Artisan>> getArtisansList() {
        return artisansList;
    }
}
