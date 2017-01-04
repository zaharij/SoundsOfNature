package com.zahar.soundsofnature.activities;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zahar.soundsofnature.R;
import com.zahar.soundsofnature.objects.AnimationObj;
import com.zahar.soundsofnature.objects.Squad;

import static com.zahar.soundsofnature.constants.ConstantsConfig.*;

public class SplashScreenActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = SPLASH_SCREEN_TIME;
    private ImageView splashImageView, imgLogoView;
    private TextView textViewGameVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.parseColor(SPLASH_SCREEN_BACKGROUND_COLOR));

        splashImageView = (ImageView) findViewById(R.id.splashImageView);
        imgLogoView = (ImageView) findViewById(R.id.imageViewLogo);

        textViewGameVersion = (TextView) findViewById(R.id.textViewGameVersion);
        textViewGameVersion.setText(GAME_NAME_VERSION);
        textViewGameVersion.setTextColor(Color.parseColor(GAME_NAME_VERSION_COLOR));

        imgLogoView.setImageResource(R.drawable.logo);
        AnimationObj.rotateAnim(splashImageView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Squad.returnMainActivity(SplashScreenActivity.this);
            }
        }, SPLASH_TIME_OUT);
    }
}
