package ru.shmakova.evotortest.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.shmakova.evotortest.R;
import ru.shmakova.evotortest.ui.fragments.GroupsPagerFragment;

/**
 * Created by shmakova on 16.10.16.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_frame_layout, new GroupsPagerFragment())
                    .commit();
        }
    }
}
