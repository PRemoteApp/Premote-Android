package com.example.finkacho.premote_android.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.finkacho.premote_android.LoginActivity;
import com.example.finkacho.premote_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreen extends AppCompatActivity {

    @BindView(R.id.logo)
    ImageView image;

    ActivityOptionsCompat options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);

        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        image.startAnimation(fadeInAnimation);

        (new Handler()).postDelayed(this::transate, 2500);

    }

    private void transate() {
        startActivity(new Intent(this, LoginActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

}
