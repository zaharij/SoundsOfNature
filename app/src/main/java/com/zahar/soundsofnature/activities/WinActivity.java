package com.zahar.soundsofnature.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.zahar.soundsofnature.R;
import com.zahar.soundsofnature.objects.AnimationObj;

import static com.zahar.soundsofnature.constants.ConstantsConfig.*;

public class WinActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = WIN_SCREEN_TIME;
    private ImageView winImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.parseColor(LISTENING_SCREEN_BACKGROUND_COLOR));

        winImageView = (ImageView) findViewById(R.id.imageViewWin);

        AnimationObj.rotationInfinity(winImageView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(WinActivity.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
