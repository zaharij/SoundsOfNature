package com.zahar.soundsofnature.activities;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;

import com.zahar.soundsofnature.R;
import com.zahar.soundsofnature.enums.SoundMakerEntityEnum;
import com.zahar.soundsofnature.objects.Squad;

import static com.zahar.soundsofnature.constants.ConstantsConfig.TABS_LISTENING_SCREEN_BACKGROUND_COLOR;

public class TabsListeningActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.parseColor(TABS_LISTENING_SCREEN_BACKGROUND_COLOR));

        TabHost tabHost = getTabHost();
        TabHost.TabSpec tabSpec;
        tabSpec = tabHost.newTabSpec(SoundMakerEntityEnum.ANIMALS.toString());
        tabSpec.setIndicator(SoundMakerEntityEnum.ANIMALS.toString());
        tabSpec.setContent(new Intent(this, ListeningAnimalsActivity.class));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec(SoundMakerEntityEnum.TRANSPORTS.toString());
        tabSpec.setIndicator(SoundMakerEntityEnum.TRANSPORTS.toString());
        tabSpec.setContent(new Intent(this, ListeningTransportsActivity.class));
        tabHost.addTab(tabSpec);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                Squad.stopSound();
            }
        });
    }
}
