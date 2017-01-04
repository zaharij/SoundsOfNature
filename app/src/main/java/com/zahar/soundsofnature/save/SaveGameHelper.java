package com.zahar.soundsofnature.save;

import android.content.SharedPreferences;

import com.zahar.soundsofnature.enums.SoundMakerEntityEnum;
import static com.zahar.soundsofnature.constants.ConstantsConfig.*;

/**
 * Created by Zakhar on 04.01.2017.
 */

public class SaveGameHelper implements  SaveGameHelperInt{
    private final String PIC_NUM_ANIMALS_SH_PREF = "picNumAnimals";
    private final String PIC_NUM_TRANSPORTS_SH_PREF = "picNumTransports";
    private final int START_WIN_COUNT_NUMBER = 0;


    @Override
    public void updPicNumbers(SharedPreferences sharedPreferences, SoundMakerEntityEnum soundMakerEntityEnum) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int count = ADD_TO_WIN_COUNT_NUMBER;
        switch (soundMakerEntityEnum){
            case ANIMALS:
                count += getPicNumbers(sharedPreferences, soundMakerEntityEnum);
                editor.putInt(PIC_NUM_ANIMALS_SH_PREF, count);
                break;
            case TRANSPORTS:
                count += getPicNumbers(sharedPreferences, soundMakerEntityEnum);
                editor.putInt(PIC_NUM_TRANSPORTS_SH_PREF, count);
        }
        editor.commit();
    }

    @Override
    public int getPicNumbers(SharedPreferences sharedPreferences, SoundMakerEntityEnum soundMakerEntityEnum) {
        int resultPickNum = START_WIN_COUNT_NUMBER;
        switch (soundMakerEntityEnum) {
            case ANIMALS:
                resultPickNum = sharedPreferences.getInt(PIC_NUM_ANIMALS_SH_PREF, 0);
                if(resultPickNum > MAX_PICKS_NUMBER || resultPickNum < MIN_PICKS_NUMBER){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    resultPickNum = MIN_PICKS_NUMBER;
                    editor.putInt(PIC_NUM_ANIMALS_SH_PREF, resultPickNum);
                    editor.commit();
                }
                break;
            case TRANSPORTS:
                resultPickNum = sharedPreferences.getInt(PIC_NUM_TRANSPORTS_SH_PREF, 0);
                if(resultPickNum > MAX_PICKS_NUMBER || resultPickNum < MIN_PICKS_NUMBER){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    resultPickNum = MIN_PICKS_NUMBER;
                    editor.putInt(PIC_NUM_TRANSPORTS_SH_PREF, resultPickNum);
                    editor.commit();
                }
        }
        return resultPickNum;
    }

    @Override
    public void nullPicNumbers(SharedPreferences sharedPreferences, SoundMakerEntityEnum soundMakerEntityEnum) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        switch (soundMakerEntityEnum){
            case ANIMALS:
                editor.putInt(PIC_NUM_ANIMALS_SH_PREF, START_WIN_COUNT_NUMBER);
                break;
            case TRANSPORTS:
                editor.putInt(PIC_NUM_TRANSPORTS_SH_PREF, START_WIN_COUNT_NUMBER);
        }
        editor.commit();
    }
}
