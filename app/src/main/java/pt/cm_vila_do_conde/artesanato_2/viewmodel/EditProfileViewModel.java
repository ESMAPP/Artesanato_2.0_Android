package pt.cm_vila_do_conde.artesanato_2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import pt.cm_vila_do_conde.artesanato_2.model.User;
import pt.cm_vila_do_conde.artesanato_2.repository.ProfileEditRepository;


public class EditProfileViewModel extends AndroidViewModel {

    private ProfileEditRepository profileEditRepository;

    public LiveData <User> editUserLiveData;
    public LiveData<User> editUserIdLiveData;


    public EditProfileViewModel(@NonNull Application application) {
        super(application);

        profileEditRepository = new ProfileEditRepository();
    }

    public void GetUserIdFromFirebase() {

        editUserIdLiveData = profileEditRepository.getUserIdFromFirebase();
    }
}
