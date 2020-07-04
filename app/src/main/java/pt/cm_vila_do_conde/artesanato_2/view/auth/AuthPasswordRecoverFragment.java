package pt.cm_vila_do_conde.artesanato_2.view.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import pt.cm_vila_do_conde.artesanato_2.R;


public class AuthPasswordRecoverFragment extends Fragment {

    public AuthPasswordRecoverFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auth_password_recover, container, false);
    }
}
