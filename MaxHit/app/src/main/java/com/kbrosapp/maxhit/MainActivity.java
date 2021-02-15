package com.kbrosapp.maxhit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView scoreTextView;
    TextView timerTextView;
    Button hitMeButton;
    Button resetBtn;
    int score=0;

    boolean timeUp=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hitMeButton =findViewById(R.id.hitMeButton);
        resetBtn=findViewById(R.id.resetBtn);
        scoreTextView=findViewById(R.id.scoreTextView);
        timerTextView=findViewById(R.id.timerTextView);

        scoreTextView.setText(String.valueOf(score));

        hitMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!timeUp){
                    score=score+1;
                    scoreTextView.setText(String.valueOf(score));
                }

            }
        });


        final CountDownTimer countDownTimer=new CountDownTimer(11000, 1000) {

            public void onTick(long millisUntilFinished) {
                timerTextView.setText("Time left : " + millisUntilFinished / 1000+" seconds");
            }

            public void onFinish() {
                timerTextView.setText("TIME UP!!");
                timeUp=true;
            }
        };
        countDownTimer.start();

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score=0;
                scoreTextView.setText(String.valueOf(score));
                countDownTimer.start();
                timeUp=false;
            }
        });


    }
}
