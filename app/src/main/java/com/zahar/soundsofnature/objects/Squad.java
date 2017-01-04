package com.zahar.soundsofnature.objects;

import android.content.res.Resources;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zahar.soundsofnature.entities.SoundMakerEntity;
import com.zahar.soundsofnature.enums.SoundMakerEntityEnum;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import static com.zahar.soundsofnature.constants.ConstantsConfig.*;

/**
 * Created by Zakhar on 03.01.2017.
 */

public class Squad {
    private static MediaPlayer mediaPlayer;

    public static void setPictures(AppCompatActivity appCompatActivity, View.OnClickListener onClickListener
            , RelativeLayout relativeLayout, HashMap<Integer, SoundMakerEntity> soundMakerMap, int columnsNumber, int rowsNumber) {

        int[] imgSize = appropriateImageSize(columnsNumber, rowsNumber);
        int idCount = ID_START_NUMBER;
        for(int i = 0 ; i <  columnsNumber ; i++){
            for (int j = 0; j < rowsNumber; j++) {
                int viewId;
                try{
                    viewId = soundMakerMap.get(idCount).getViewId();
                } catch (NullPointerException e){
                    break;
                }
                ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(0);
                ImageButton soundMakerImageBtn = new ImageButton(appCompatActivity);
                soundMakerImageBtn.setImageResource(viewId);
                soundMakerImageBtn.setOnClickListener(onClickListener);
                soundMakerImageBtn.setBackgroundColor(Color.TRANSPARENT);
                soundMakerImageBtn.setTag(idCount);
                soundMakerImageBtn.setId(idCount);
                soundMakerImageBtn.setX(i * imgSize[1]);
                soundMakerImageBtn.setY(j * imgSize[0]);
                soundMakerImageBtn.setLayoutParams(layoutParams);

                soundMakerImageBtn.getLayoutParams().height = imgSize[0];//set appropriate sizes
                soundMakerImageBtn.getLayoutParams().width = imgSize[1];

                soundMakerImageBtn.setScaleType(ImageView.ScaleType.CENTER_CROP);

                relativeLayout.addView(soundMakerImageBtn);
                idCount++;
            }
        }
    }

    public static void setSound(AppCompatActivity appCompatActivity, int id, HashMap<Integer, SoundMakerEntity> soundMakerMap){
        mediaPlayer = MediaPlayer.create(appCompatActivity.getBaseContext()
                , soundMakerMap.get(id).getSoundIdArr()[getRandomToGivenNumber(0, soundMakerMap.get(id).getSoundIdArr().length - 1)]);
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
    }

    public static void stopSound(){
        try{
            mediaPlayer.stop();
        } catch (NullPointerException e){
            return;
        } catch (IllegalStateException ex){
            return;
        }
    }

    public static HashMap<Integer, SoundMakerEntity> getCurrentSoundMakerMap(SoundMakerEntityEnum soundMakerEntityEnum) {
        HashMap<Integer, SoundMakerEntity> soundMakerMap;

        switch(soundMakerEntityEnum){
            case ANIMALS:
                soundMakerMap = ANIMALS_MAP;
                break;
            case TRANSPORTS:
                soundMakerMap = TRANSPORTS_MAP;
                break;
            default:
                soundMakerMap = ANIMALS_MAP;
        }
        return soundMakerMap;
    }

    private static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    private static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    private static int[] appropriateImageSize(int rowImagesNumber, int columnImagesNumber){
        int height;
        if (rowImagesNumber > 2){
            height = (getScreenHeight() / (columnImagesNumber)) * 70 / 100;
        } else {
            height = (getScreenHeight() / (columnImagesNumber)) * 90 / 100;
        }
        int width = (getScreenWidth() / (rowImagesNumber)) * 50 / 100;
        int[] size = new int[]{height, width};
        return size;
    }

    /**
     * returns the random number in given range
     * @param minNumber - min number
     * @param maxNumber - max number
     * @return - random number (int)
     */
    public static int getRandomToGivenNumber(int minNumber, int maxNumber){
        int maxn = maxNumber;
        Random ran = new Random();
        int random = ran.nextInt(maxNumber - minNumber + 1) + minNumber;
        return random;
    }
}
