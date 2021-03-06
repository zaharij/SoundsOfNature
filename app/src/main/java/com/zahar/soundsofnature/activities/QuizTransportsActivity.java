package com.zahar.soundsofnature.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private Button backBtn, soundBtn;

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

        backBtn = (Button) findViewById(R.id.buttonBack);
        backBtn.setBackgroundColor(Color.parseColor(BACK_BTN_COLOR));
        backBtn.setTextColor(Color.WHITE);

        soundBtn = (Button) findViewById(R.id.buttonRepeat);
        soundBtn.setBackgroundColor(Color.parseColor(BACK_BTN_COLOR));
        soundBtn.setTextColor(Color.WHITE);

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
                        textView.setText(Squad.generateRightMessage());
                        Squad.nullRightText(textView);
                    } else {
                        Squad.getDialogWinWindow(QuizTransportsActivity.this);
                    }
                } else {
                    quiz.isVin(sharedPreferences, SoundMakerEntityEnum.TRANSPORTS, false);
                    textView.setText(NULL_MESSAGE);
                    quiz.startSound(QuizTransportsActivity.this);
                    Drawable drawable = null;
                    drawable = QuizTransportsActivity.this.getResources().getDrawable(quiz.getSoundMakerMapToOutput().get(quiz.getWinId()).getViewId());
                    Squad.dialogNotRight(QuizTransportsActivity.this, drawable, quiz.getSoundMakerMapToOutput().get(quiz.getWinId()).getName());
                }
            }
        };
        startQuiz(entityOnClickListener);
    }

    /**
     * starts quiz
     * @param entityOnClickListener
     */
    private void startQuiz(View.OnClickListener entityOnClickListener) {
        quiz.putRandomImages(this, entityOnClickListener, gameBoard, sharedPreferences, SoundMakerEntityEnum.TRANSPORTS);
        quiz.startSound(this);
    }

    /**
     * returns to main activity
     * @param view
     */
    public void backClick(View view){
        Squad.stopSound();
        Squad.returnMainActivity(QuizTransportsActivity.this);
    }

    /**
     * repeats the sound
     * @param view
     */
    public void repeatClick(View view){
        quiz.startSound(this);
    }
}
