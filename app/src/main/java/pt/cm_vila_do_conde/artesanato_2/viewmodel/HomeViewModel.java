package pt.cm_vila_do_conde.artesanato_2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import pt.cm_vila_do_conde.artesanato_2.model.Event;
import pt.cm_vila_do_conde.artesanato_2.repository.HomeRepository;

public class HomeViewModel extends AndroidViewModel {
    public LiveData<Integer> userRole;
    public LiveData<Event> featuredEvent;

    private HomeRepository homeRepository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        homeRepository = new HomeRepository();
    }

    /*public void addUserFromNavigation(User user){
        userMutableLiveData.setValue(user);
    }*/

    public void getUserRole() {
        userRole = homeRepository.getUserRole();
    }

    public void getFeatureEvent() {
        featuredEvent = homeRepository.getFeaturedEvent();
        /*updateArrayList();*/
    }


    /*public void updateArrayList() {
        ArrayList toUpdate = new ArrayList<>();
        if (featuredEvent != null) {
            featuredEvent.observeForever(toUpdate::add);
            featuredList.setValue(toUpdate);
        }
    }*/
}
