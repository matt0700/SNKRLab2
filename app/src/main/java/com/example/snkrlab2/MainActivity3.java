package com.example.snkrlab2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity3 extends AppCompatActivity {

    private boolean isMicClicked = false;
    private boolean isHideClicked = false;
    private boolean isSettingsClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        final ImageButton micButton = findViewById(R.id.voiceButton);
        final ImageButton hideButton = findViewById(R.id.hideUnhideButton);
        final ImageButton settingsButton = findViewById(R.id.settingsButton);

        micButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMicClicked) {
                    micButton.setImageResource(R.drawable.mic);
                } else {
                    micButton.setImageResource(R.drawable.blue_mic);
                }
                isMicClicked = !isMicClicked;
            }
        });

        hideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHideClicked) {
                    hideButton.setImageResource(R.drawable.hide);
                } else {
                    hideButton.setImageResource(R.drawable.blue_hide);
                }
                isHideClicked = !isHideClicked;
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSettingsClicked) {
                    settingsButton.setImageResource(R.drawable.setting);
                } else {
                    settingsButton.setImageResource(R.drawable.blue_setting);
                }
                isSettingsClicked = !isSettingsClicked;
            }
        });
    }
}
