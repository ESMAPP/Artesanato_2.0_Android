package pt.cm_vila_do_conde.artesanato_2.viewmodel;

import android.app.Application;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import pt.cm_vila_do_conde.artesanato_2.model.Badge;
import pt.cm_vila_do_conde.artesanato_2.model.User;
import pt.cm_vila_do_conde.artesanato_2.repository.BadgesRepository;
import pt.cm_vila_do_conde.artesanato_2.repository.ProfileRepository;


public class ProfileViewModel extends AndroidViewModel {
    private MutableLiveData<List<Badge>> badges;
    private MutableLiveData<Boolean> editSuccess;

    private ProfileRepository profileRepository;
    private BadgesRepository badgesRepository;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        profileRepository = new ProfileRepository();
        badgesRepository = new BadgesRepository();

    }

    public void fetchBadges() {
        badges = badgesRepository.fetchBadgesList();
    }

    public void updateProfile(String username, String password, Bitmap image, User currentUser) {
        editSuccess = profileRepository.updateProfile(username, password, image, currentUser);
    }

    public MutableLiveData<Boolean> getEditSuccess() {
        return editSuccess;
    }

    public LiveData<List<Badge>> getBadges() {
        return badges;
    }
}
