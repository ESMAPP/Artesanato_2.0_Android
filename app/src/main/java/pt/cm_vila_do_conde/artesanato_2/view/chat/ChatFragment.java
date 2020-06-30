package pt.cm_vila_do_conde.artesanato_2.view.chat;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pt.cm_vila_do_conde.artesanato_2.R;
import pt.cm_vila_do_conde.artesanato_2.databinding.FragmentChatBinding;

public class ChatFragment extends Fragment {

    private static final String ARTISAN_ID = "artisanId";
    private static final String USER_ID = "userId";

    private String artisanId;
    private String userId;

    private FragmentChatBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            artisanId = getArguments().getString(ARTISAN_ID);
            userId = getArguments().getString(USER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChatBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        placeholderTest();
    }

    private void placeholderTest(){
        binding.userIdText.setText(userId);
        binding.artisanIdText.setText(artisanId);
    }
}