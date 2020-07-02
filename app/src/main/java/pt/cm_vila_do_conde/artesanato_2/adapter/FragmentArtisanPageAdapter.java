package pt.cm_vila_do_conde.artesanato_2.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import pt.cm_vila_do_conde.artesanato_2.view.artisan.constests.RootArtisanContestFragment;
import pt.cm_vila_do_conde.artesanato_2.view.artisan.gallery.ArtisanGalleryFragment;
import pt.cm_vila_do_conde.artesanato_2.view.artisan.gallery.RootGalleryFragment;
import pt.cm_vila_do_conde.artesanato_2.view.artisan.reviews.ArtisanReviewsFragment;
import pt.cm_vila_do_conde.artesanato_2.view.auth.SignInFragment;

public class FragmentArtisanPageAdapter extends FragmentPagerAdapter {

    public FragmentArtisanPageAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new RootGalleryFragment(); //ChildFragment1 at position 0
            case 1:
                return new ArtisanReviewsFragment(); //ChildFragment2 at position 1
            case 2:
                return new RootArtisanContestFragment(); //ChildFragment3 at position 2
            case 3:
                return new RootArtisanContestFragment(); //ChildFragment3 at position 3
        }
        return null; //does not happen
    }

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
        return 4;
    }
}
