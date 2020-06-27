package pt.cm_vila_do_conde.artesanato_2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import pt.cm_vila_do_conde.artesanato_2.model.Artisan;
import pt.cm_vila_do_conde.artesanato_2.repository.ArtisanRepository;

public class ArtisanPageViewModel extends AndroidViewModel {
    private LiveData<Artisan> artisan;
    private ArtisanRepository artisanRepository;

    public ArtisanPageViewModel(@NonNull Application application) {
        super(application);
        artisanRepository = new ArtisanRepository();
    }

    public void fetchArtisanById(String id) {
        artisan = artisanRepository.fetchArtisanById(id);
    }

    public LiveData<Artisan> getArtisan() {
        return artisan;
    }
}
