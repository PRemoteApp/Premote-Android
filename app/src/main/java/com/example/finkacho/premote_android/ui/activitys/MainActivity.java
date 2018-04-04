package com.example.finkacho.premote_android.ui.activitys;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.finkacho.premote_android.R;
import com.example.finkacho.premote_android.ui.fragments.CommandsFragment;
import com.example.finkacho.premote_android.ui.fragments.MainFragment;
import com.example.finkacho.premote_android.ui.fragments.SettingsFragment;
import com.google.firebase.auth.FirebaseAuth;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.toolbar_signout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                overridePendingTransition(R.anim.slide_right, R.anim.slide_left);
                finish();
        }
        return true;
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
