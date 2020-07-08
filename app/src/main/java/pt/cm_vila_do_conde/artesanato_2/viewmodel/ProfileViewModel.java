package pt.cm_vila_do_conde.artesanato_2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import pt.cm_vila_do_conde.artesanato_2.model.Badge;
import pt.cm_vila_do_conde.artesanato_2.repository.AuthRepository;
import pt.cm_vila_do_conde.artesanato_2.repository.BadgesRepository;


public class ProfileViewModel extends AndroidViewModel {
    private MutableLiveData<List<Badge>> badges;

    private AuthRepository authRepository;
    private BadgesRepository badgesRepository;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        authRepository = new AuthRepository();
        badgesRepository = new BadgesRepository();
    }

    public void fetchBadges() {
        badges = badgesRepository.fetchBadgesList();
    }

    public LiveData<List<Badge>> getBadges() {
        return badges;
    }
}
