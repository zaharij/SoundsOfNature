package com.zahar.soundsofnature.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.zahar.soundsofnature.R;

import static com.zahar.soundsofnature.constants.ConstantsConfig.*;

public class MainActivity extends AppCompatActivity {
    private ImageButton listeningBtn, quizBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.parseColor(MAIN_SCREEN_BACKGROUND_COLOR));

        listeningBtn = (ImageButton) findViewById(R.id.imageButtonListening);
        quizBtn = (ImageButton) findViewById(R.id.imageButtonQuiz);

        listeningBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(MainActivity.this, TabsListeningActivity.class);
                startActivity(homeIntent);
                finish();
            }
        });

        quizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(MainActivity.this, TabsQuizActivity.class);
                startActivity(homeIntent);
                finish();
            }
        });
    }
}
