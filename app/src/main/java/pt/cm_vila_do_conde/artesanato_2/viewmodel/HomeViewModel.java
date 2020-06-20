package pt.cm_vila_do_conde.artesanato_2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import pt.cm_vila_do_conde.artesanato_2.model.Artisan;
import pt.cm_vila_do_conde.artesanato_2.model.Event;
import pt.cm_vila_do_conde.artesanato_2.repository.HomeRepository;

public class HomeViewModel extends AndroidViewModel {

    public LiveData<Event> upcomingEvent;
    public LiveData<Event> fairEvent;
    public LiveData<Artisan> featuredArtisan;

    private HomeRepository homeRepository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        homeRepository = new HomeRepository();
    }

    /*public void addUserFromNavigation(User user){
        userMutableLiveData.setValue(user);
    }*/

/*    public void getUser() {
        userRole = homeRepository.getUserRole();
    }*/

    public void getUpComingEvent() {
        upcomingEvent = homeRepository.getUpcomingEvent();
        /*updateArrayList();*/
    }


    public void getFeaturedArtisan() {
        featuredArtisan = homeRepository.getFeaturedArtisan();
    }

    public void getFairEvent() {
        fairEvent = homeRepository.getFairEvent();
    }


    /*public void updateArrayList() {
        ArrayList toUpdate = new ArrayList<>();
        if (featuredEvent != null) {
            featuredEvent.observeForever(toUpdate::add);
            featuredList.setValue(toUpdate);
        }
    }*/
}
