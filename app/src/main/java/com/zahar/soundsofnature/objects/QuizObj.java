package com.zahar.soundsofnature.objects;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.zahar.soundsofnature.entities.SoundMakerEntity;
import com.zahar.soundsofnature.enums.SoundMakerEntityEnum;
import com.zahar.soundsofnature.save.SaveGameHelper;

import java.util.HashMap;

import static com.zahar.soundsofnature.constants.ConstantsConfig.*;

/**
 * Created by Zakhar on 04.01.2017.
 */

public class QuizObj {
    private HashMap<Integer, SoundMakerEntity> soundMakerMap;
    private HashMap<Integer, SoundMakerEntity> soundMakerMapToOutput;
    private int[] picNums;
    private int winId;
    private int winNumber = DEFAULT_WIN_NUMBER;
    private SaveGameHelper saveGameHelper = new SaveGameHelper();

    public int getWinId(){
        return winId;
    }

    public int getPicNumsLength() {
        return picNums.length;
    }

    public HashMap<Integer, SoundMakerEntity> getSoundMakerMapToOutput() {
        return soundMakerMapToOutput;
    }


    public void putRandomImages(AppCompatActivity appCompatActivity, View.OnClickListener onClickListener
            , RelativeLayout relativeLayout, SharedPreferences sharedPreferences, SoundMakerEntityEnum soundMakerEntityEnum){
        soundMakerMapToOutput = new HashMap<Integer, SoundMakerEntity>();
        soundMakerMap = Squad.getCurrentSoundMakerMap(soundMakerEntityEnum);
        picNums = getPicturesNumbers(sharedPreferences, soundMakerEntityEnum);
        for(int i = 0; i < picNums.length; i++){
            soundMakerMapToOutput.put(i, soundMakerMap.get(picNums[i]));
        }
        winId = getVinIdNumber();
        Squad.setPictures(appCompatActivity, onClickListener, relativeLayout, soundMakerMapToOutput, QUIZ_COLUMN_IMAGES_NUMBER, QUIZ_ROW_IMAGES_NUMBER);
    }

    public void startSound(AppCompatActivity appCompatActivity){
        int id = picNums[winId];
        Squad.setSound(appCompatActivity, winId, soundMakerMapToOutput);
    }

    public boolean getResult(int id){
        if (id == winId){
            return true;
        } else {
            return false;
        }
    }

    public boolean isVin(SharedPreferences sharedPreferences, SoundMakerEntityEnum soundMakerEntityEnum, boolean isVin){
        boolean result = false;
        if(isVin){
            winNumber++;
        } else {
            winNumber = DEFAULT_WIN_NUMBER;
        }
        if(winNumber >= WIN_NUMBER_TO_PLUS_IMG){
            if (saveGameHelper.getPicNumbers(sharedPreferences, soundMakerEntityEnum) >= MAX_PICKS_NUMBER){
                result = true;
            }
            saveGameHelper.updPicNumbers(sharedPreferences, soundMakerEntityEnum);
            winNumber = DEFAULT_WIN_NUMBER;
        }
        return result;
    }

    private int getVinIdNumber(){
        int winId = Squad.getRandomToGivenNumber(ID_START_NUMBER, picNums.length - 1);
        return winId;
    }

    private int[] getPicturesNumbers(SharedPreferences sharedPreferences, SoundMakerEntityEnum soundMakerEntityEnum){
        int picNum = saveGameHelper.getPicNumbers(sharedPreferences, soundMakerEntityEnum);
        int[] picNums = new int[picNum];
        boolean zeroIsIn = false;
        start:for(int i = 0; i < picNum; i++){
            int number = Squad.getRandomToGivenNumber(ID_START_NUMBER, soundMakerMap.size() - 1);
            if (number == 0){
                zeroIsIn = true;
            }
            for(int j = 0; j < picNums.length; j++) {
                if(zeroIsIn ? number == picNums[j] : number != 0 && number == picNums[j]){
                    if (i > 0){
                        i--;
                    }
                    continue start;
                }
            }
            picNums[i] = number;
        }
        return picNums;
    }
}
