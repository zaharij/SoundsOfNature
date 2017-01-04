package com.zahar.soundsofnature.save;

import android.content.SharedPreferences;

import com.zahar.soundsofnature.enums.SoundMakerEntityEnum;

/**
 * Created by Zakhar on 04.01.2017.
 */

public interface SaveGameHelperInt {
    public void updPicNumbers(SharedPreferences sharedPreferences,SoundMakerEntityEnum soundMakerEntityEnum);
    public int getPicNumbers(SharedPreferences sharedPreferences, SoundMakerEntityEnum soundMakerEntityEnum);
    public void nullPicNumbers(SharedPreferences sharedPreferences, SoundMakerEntityEnum soundMakerEntityEnum);
}
