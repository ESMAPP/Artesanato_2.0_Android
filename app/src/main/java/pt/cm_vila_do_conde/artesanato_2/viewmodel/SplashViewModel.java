package pt.cm_vila_do_conde.artesanato_2.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import pt.cm_vila_do_conde.artesanato_2.model.User;
import pt.cm_vila_do_conde.artesanato_2.repository.SplashRepository;

public class SplashViewModel extends AndroidViewModel {
    private SplashRepository splashRepository;
    public LiveData<User> isUserAuthenticatedLiveData;
    public LiveData<User> userLiveData;

    public SplashViewModel(Application app){
        super(app);
        splashRepository = new SplashRepository();
    }

    public void checkIfUserIsAuthenticated(){
        isUserAuthenticatedLiveData = splashRepository
                .checkIfUserIsAuthenticatedInFirebase();
    }

    public void setUid(String uid){
        userLiveData = splashRepository.addUserToLiveData(uid);
    }
}
