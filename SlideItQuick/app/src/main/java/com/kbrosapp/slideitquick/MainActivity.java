package com.kbrosapp.slideitquick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Chronometer chronometer;
    TextView randomNumberTextView;
    TextView answerTextView;
    SeekBar seekBar;
    Button button;
    Button resetButton;


    int min = 1;
    int max = 100;
    int random;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        randomNumberTextView=findViewById(R.id.randomNumberTextView);
        answerTextView=findViewById(R.id.answerTextView);
        seekBar=findViewById(R.id.seekBar);
        button=findViewById(R.id.button);
        chronometer=findViewById(R.id.chronometer);
        resetButton=findViewById(R.id.resetButton);

        random = new Random().nextInt((max - min) + 1) + min;
        randomNumberTextView.setText(String.valueOf(random));

        seekBar.setProgress(0);
        answerTextView.setText("0");
        randomNumberTextView.setText("0");
        chronometer.start();
        chronometer.stop();
        chronometer.setBase(SystemClock.elapsedRealtime());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                random = new Random().nextInt((max - min) + 1) + min;
                randomNumberTextView.setText(String.valueOf(random));

                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.stop();

                seekBar.setProgress(0);
                answerTextView.setText("0");
                randomNumberTextView.setText("0");

            }
        });


        seekBar.setMax(max);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                answerTextView.setText(String.valueOf(i));

                if(i==random){
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
