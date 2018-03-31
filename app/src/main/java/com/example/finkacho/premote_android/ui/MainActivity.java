package com.example.finkacho.premote_android.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.finkacho.premote_android.R;
import com.example.finkacho.premote_android.ui.fragments.CommandsFragment;
import com.example.finkacho.premote_android.ui.fragments.MainFragment;
import com.example.finkacho.premote_android.ui.fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        if(savedInstanceState == null){
            fragmentTransaction.replace(R.id.main_fragment_container, new MainFragment()).commit();
        }

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment;
            fragmentTransaction = fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new MainFragment();
                    break;
                case R.id.navigation_dashboard:
                    fragment = new CommandsFragment();
                    break;
                case R.id.navigation_notifications:
                    fragmentTransaction.replace(R.id.main_fragment_container, new SettingsFragment()).commit();
                    return true;
                default:
                    throw new IllegalArgumentException();
            }

            fragmentTransaction.replace(R.id.main_fragment_container, fragment).commit();
            return true;
        }
    };

}
