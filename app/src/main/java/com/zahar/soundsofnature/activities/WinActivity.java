package com.zahar.soundsofnature.activities;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.zahar.soundsofnature.R;
import com.zahar.soundsofnature.objects.AnimationObj;
import com.zahar.soundsofnature.objects.Squad;

import static com.zahar.soundsofnature.constants.ConstantsConfig.*;

public class WinActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = WIN_SCREEN_TIME;
    private ImageView winImageView;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.parseColor(LISTENING_SCREEN_BACKGROUND_COLOR));

        winImageView = (ImageView) findViewById(R.id.imageViewWin);

        mediaPlayer = MediaPlayer.create(this, R.raw.win);

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                mp.release();
            }
        });

        AnimationObj.rotationInfinity(winImageView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Squad.returnMainActivity(WinActivity.this);
            }
        }, SPLASH_TIME_OUT);
    }
}
