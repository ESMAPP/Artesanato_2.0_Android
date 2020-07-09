package pt.cm_vila_do_conde.artesanato_2.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import pt.cm_vila_do_conde.artesanato_2.view.artisans.ArtisanAboutFragment;
import pt.cm_vila_do_conde.artesanato_2.view.artisans.contests.ArtisanContestsRootFragment;
import pt.cm_vila_do_conde.artesanato_2.view.artisans.gallery.ArtisanGalleryRootFragment;
import pt.cm_vila_do_conde.artesanato_2.view.artisans.reviews.ArtisanReviewsFragment;


public class EventPageAdapter extends FragmentPagerAdapter {
    static final int NUM_ITEMS = 4;

    public EventPageAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ArtisanGalleryRootFragment(); //ChildFragment1 at position 0
            case 1:
                return new ArtisanReviewsFragment(); //ChildFragment2 at position 1
            case 2:
                return new ArtisanContestsRootFragment(); //ChildFragment3 at position 2
            case 3:
                return new ArtisanAboutFragment(); //ChildFragment3 at position 3
        }

        return null; //does not happen
    }

    // TODO: get text from strings.xml
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Galeria";
            case 1:
                return "Recomendações";
            case 2:
                return "Concursos";
            case 3:
                return "Sobre";
        }

        return null;
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
