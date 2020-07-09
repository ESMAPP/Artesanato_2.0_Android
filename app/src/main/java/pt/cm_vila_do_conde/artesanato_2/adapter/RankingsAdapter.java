package pt.cm_vila_do_conde.artesanato_2.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

import pt.cm_vila_do_conde.artesanato_2.view.rankings.RankingsArtisansFragment;
import pt.cm_vila_do_conde.artesanato_2.view.rankings.RankingsUsersFragment;


public class RankingsAdapter extends FragmentPagerAdapter {

    public RankingsAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new RankingsArtisansFragment(); //ChildFragment1 at position 0
            case 1:
                return new RankingsUsersFragment(); //ChildFragment2 at position 1
        }

        return null; //does not happen
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Artesãos";
            case 1:
                return "Visitantes";
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2; //two fragments
    }
}