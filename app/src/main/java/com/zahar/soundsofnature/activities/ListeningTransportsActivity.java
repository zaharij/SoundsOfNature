package com.zahar.soundsofnature.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zahar.soundsofnature.R;
import com.zahar.soundsofnature.enums.SoundMakerEntityEnum;
import com.zahar.soundsofnature.objects.Squad;

import static com.zahar.soundsofnature.constants.ConstantsConfig.*;

public class ListeningTransportsActivity extends AppCompatActivity {
    private TextView textView;
    private RelativeLayout gameBoard;
    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening_transports);

        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.parseColor(LISTENING_SCREEN_BACKGROUND_COLOR));

        gameBoard = (RelativeLayout) findViewById(R.id.activity_listening_transports);
        textView = (TextView) findViewById(R.id.textView);
        backBtn = (Button) findViewById(R.id.buttonBack);
        backBtn.setBackgroundColor(Color.parseColor(BACK_BTN_COLOR));
        backBtn.setTextColor(Color.WHITE);

        textView.setTextColor(Color.GREEN);
        textView.setTextSize(TEXT_SIZE_DEFAULT);

        View.OnClickListener entityOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(TRANSPORTS_MAP.get(v.getId()).getName());
                Squad.setSound(ListeningTransportsActivity.this, v.getId(), Squad.getCurrentSoundMakerMap(SoundMakerEntityEnum.TRANSPORTS));
            }
        };

        Squad.setPictures(this, entityOnClickListener, gameBoard, Squad.getCurrentSoundMakerMap(SoundMakerEntityEnum.TRANSPORTS)
                , COLUMN_IMAGES_NUMBER, ROW_IMAGES_NUMBER);
    }

    /**
     * returns to main activity
     * @param view
     */
    public void backClick(View view){
        Squad.stopSound();
        Intent homeIntent = new Intent(ListeningTransportsActivity.this, MainActivity.class);
        startActivity(homeIntent);
        finish();
    }
}
