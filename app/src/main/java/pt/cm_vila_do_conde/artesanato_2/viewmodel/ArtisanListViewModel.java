package pt.cm_vila_do_conde.artesanato_2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import pt.cm_vila_do_conde.artesanato_2.model.Artisan;
import pt.cm_vila_do_conde.artesanato_2.repository.ArtisanRepository;

public class ArtisanListViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Artisan>> artisansList;
    private ArtisanRepository artisanRepository;

    public ArtisanListViewModel(@NonNull Application application) {
        super(application);
        artisanRepository = new ArtisanRepository();
    }


    public void fetchArtisansList(){
        artisansList = artisanRepository.fetchArtisansList();
    }

    public LiveData<ArrayList<Artisan>> getArtisansList() {
        return artisansList;
    }
}
