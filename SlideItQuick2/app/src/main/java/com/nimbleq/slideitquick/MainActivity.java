package com.nimbleq.slideitquick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Chronometer chronometer;
    TextView randomTextView;
    TextView silderTextView;
    SeekBar seekBar;
    Button playButton;
    Button resetButton;

    int min = 1;
    int max = 100;
    int random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer=findViewById(R.id.chronometer);
        randomTextView=findViewById(R.id.randomTextView);
        silderTextView=findViewById(R.id.silderTextView);
        seekBar=findViewById(R.id.seekBar);
        playButton=findViewById(R.id.playButton);
        resetButton=findViewById(R.id.resetButton);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                random = new Random().nextInt((max - min) + 1) + min;
                randomTextView.setText(String.valueOf(random));

                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.stop();

                randomTextView.setText("0");

                seekBar.setProgress(0);
                silderTextView.setText("0");
            }
        });

        seekBar.setMax(max);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                silderTextView.setText(String.valueOf(i));

                if(random==i){
                    chronometer.stop();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




    }
}
