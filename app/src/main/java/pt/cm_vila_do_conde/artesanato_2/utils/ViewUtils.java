package pt.cm_vila_do_conde.artesanato_2.utils;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import pt.cm_vila_do_conde.artesanato_2.model.User;
import pt.cm_vila_do_conde.artesanato_2.view.MainActivity;

// TODO: Try to make this work
public class ViewUtils extends AppCompatActivity {
    public void goToMainActivity(Context context, User user) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("user", user);
        System.out.println("got here");
        startActivity(intent);
        System.out.println("Maybe here");
    }
}
