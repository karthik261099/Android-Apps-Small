package com.nimbleq.maxhitgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView;
    TextView scoreTextView;

    Button hitMeButton;
    Button resetButton;

    int score=0;

    boolean isPlaying=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView=findViewById(R.id.timerTextView);
        scoreTextView=findViewById(R.id.scoreTextView);

        hitMeButton=findViewById(R.id.hitMeButton);
        resetButton=findViewById(R.id.resetButton);


        final CountDownTimer countDownTimer = new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                timerTextView.setText("TIME LEFT : "+millisUntilFinished/1000+" seconds");
            }

            public void onFinish() {
                isPlaying=false;
                timerTextView.setText("TIME IS UP!!");
            }
        };

        countDownTimer.start();


        hitMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isPlaying==true){
                    score=score+1;
                    scoreTextView.setText(String.valueOf(score));
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score=0;
                scoreTextView.setText(String.valueOf(score));
                isPlaying=true;
                countDownTimer.start();
            }
        });




    }
}
