package com.zahar.soundsofnature.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zahar.soundsofnature.R;
import com.zahar.soundsofnature.enums.SoundMakerEntityEnum;
import com.zahar.soundsofnature.objects.QuizObj;
import com.zahar.soundsofnature.objects.Squad;
import com.zahar.soundsofnature.save.SaveGameHelper;

import static com.zahar.soundsofnature.constants.ConstantsConfig.*;

public class QuizTransportsActivity extends AppCompatActivity {
    private TextView textView;
    private RelativeLayout gameBoard;
    private QuizObj quiz = new QuizObj();
    private SharedPreferences sharedPreferences;
    private SaveGameHelper saveGameHelper;
    private SoundMakerEntityEnum soundMakerEntityEnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_transports);

        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.parseColor(LISTENING_SCREEN_BACKGROUND_COLOR));

        sharedPreferences = getPreferences(MODE_PRIVATE);
        saveGameHelper = new SaveGameHelper();

        gameBoard = (RelativeLayout) findViewById(R.id.activity_quiz_transports);
        textView = (TextView) findViewById(R.id.textView);

        textView.setTextSize(TEXT_SIZE_DEFAULT);

        View.OnClickListener entityOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quiz.getResult(v.getId())){
                    if(!quiz.isVin(sharedPreferences, SoundMakerEntityEnum.TRANSPORTS, true)){
                        Squad.stopSound();
                        gameBoard.removeViewsInLayout(REMOVE_QUIZ_IMAGES_START_NUMBER, quiz.getPicNumsLength());
                        startQuiz(this);
                        textView.setTextColor(Color.GREEN);
                        textView.setText(RIGHT_MESSAGE);
                    } else {
                        Intent homeIntent = new Intent(QuizTransportsActivity.this, WinActivity.class);
                        startActivity(homeIntent);
                        finish();
                    }
                } else {
                    quiz.isVin(sharedPreferences, SoundMakerEntityEnum.TRANSPORTS, false);
                    textView.setTextColor(Color.RED);
                    textView.setText(quiz.getSoundMakerMapToOutput().get(quiz.getWinId()).getName());
                }
            }
        };
        startQuiz(entityOnClickListener);
    }

    private void startQuiz(View.OnClickListener entityOnClickListener) {
        quiz.putRandomImages(this, entityOnClickListener, gameBoard, sharedPreferences, SoundMakerEntityEnum.TRANSPORTS);
        quiz.startSound(this);
    }

    public void backClick(View view){
        Squad.stopSound();
        Intent homeIntent = new Intent(QuizTransportsActivity.this, MainActivity.class);
        startActivity(homeIntent);
        finish();
    }

    public void repeatClick(View view){
        quiz.startSound(this);
    }
}
