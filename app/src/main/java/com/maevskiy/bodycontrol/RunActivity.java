package com.maevskiy.bodycontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import java.text.DecimalFormat;

public class RunActivity extends AppCompatActivity {
    MediaPlayer pianoMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
        Intent caller = getIntent();
        double calories = caller.getIntExtra("calories", 0);
        EditText haveToRunKm = (EditText) findViewById(R.id.editText);
        DecimalFormat formatVal = new DecimalFormat("##.##");
        double kmForRun = calories/800;
        haveToRunKm.setText(formatVal.format(kmForRun) + " km.");
        ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setImageResource(R.drawable.runner);
        if (pianoMusic == null) {
            pianoMusic = MediaPlayer.create(this, R.raw.piano);
        }
        Switch switch_looping = (Switch) findViewById(R.id.switch_looping);
        switch_looping.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                pianoMusic.setLooping(isChecked);
            }
        });
    }

    public void playMusic(View view) {
        if (pianoMusic != null) {
            pianoMusic.start();
        }
    }

    public void pauseMusic (View view) {
        if (pianoMusic.isPlaying()) {
            pianoMusic.pause();
        }
    }

    public void backToMain (View view) {
        Intent goToMain = new Intent();
        goToMain.setClass(this, MainActivity.class);
        startActivity(goToMain);
    }
}
