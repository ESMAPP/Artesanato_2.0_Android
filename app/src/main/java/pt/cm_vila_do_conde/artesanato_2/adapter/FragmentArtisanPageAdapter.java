package pt.cm_vila_do_conde.artesanato_2.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import pt.cm_vila_do_conde.artesanato_2.view.SignInFragment;
import pt.cm_vila_do_conde.artesanato_2.view.SignUpFragment;

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
                return new SignInFragment(); //ChildFragment1 at position 0
            case 1:
                return new SignUpFragment(); //ChildFragment2 at position 1
            case 2:
                return new SignInFragment(); //ChildFragment3 at position 2
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
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
