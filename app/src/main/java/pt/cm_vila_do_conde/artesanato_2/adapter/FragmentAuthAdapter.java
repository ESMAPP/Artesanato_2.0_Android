package pt.cm_vila_do_conde.artesanato_2.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

import pt.cm_vila_do_conde.artesanato_2.view.LoginFragment;
import pt.cm_vila_do_conde.artesanato_2.view.SignupFragment;

public class FragmentAuthAdapter extends FragmentPagerAdapter {

    public FragmentAuthAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new LoginFragment(); //ChildFragment1 at position 0
            case 1:
                return new SignupFragment(); //ChildFragment2 at position 1
        }
        return null; //does not happen
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Entrar";
            case 1:
                return "Registar";
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2; //two fragments
    }
}