package pt.cm_vila_do_conde.artesanato_2.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import pt.cm_vila_do_conde.artesanato_2.view.profile.ProfileBadgesFragment;
import pt.cm_vila_do_conde.artesanato_2.view.profile.activity.ProfileActivityRootFragment;
import pt.cm_vila_do_conde.artesanato_2.view.profile.contests.ProfileContestsRootFragment;


public class ProfileAdapter extends FragmentPagerAdapter {

    public ProfileAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ProfileBadgesFragment(); //ChildFragment1 at position 0
            case 1:
                return new ProfileActivityRootFragment(); //ChildFragment2 at position 1
            case 2:
                return new ProfileContestsRootFragment(); //ChildFragment3 at position 2
        }

        return null; //does not happen
    }


    // TODO: get text from strings.xml
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Medalhas";
            case 1:
                return "Atividade";
            case 2:
                return "Concursos";
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
