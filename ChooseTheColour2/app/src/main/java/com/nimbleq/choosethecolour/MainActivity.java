package com.nimbleq.choosethecolour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    TextView messageTextView;
    TextView timerTextView;
    TextView scoreTextView;
    TextView displayColourNameTextView;

    Button startButton;
    Button resetButton;

    Button redButton;
    Button greenButton;
    Button blueButton;
    Button yellowButton;

    CountDownTimer countDownTimer;
    boolean playingGame=false;
    int score=0;

    int randomColorIndex;
    int randomColorNameIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout=findViewById(R.id.constraintLayout);
        messageTextView=findViewById(R.id.messageTextView);
        timerTextView=findViewById(R.id.timerTextView);
        scoreTextView=findViewById(R.id.scoreTextView);
        displayColourNameTextView=findViewById(R.id.displayColourNameTextView);

        startButton=findViewById(R.id.startButton);
        resetButton=findViewById(R.id.resetButton);

        redButton=findViewById(R.id.redButton);
        greenButton=findViewById(R.id.greenButton);
        blueButton=findViewById(R.id.blueButton);
        yellowButton=findViewById(R.id.yellowButton);


        countDownTimer=new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long l) {
                timerTextView.setText("Time left : "+l/1000+"s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("Time Up!");
                score=0;
                playingGame=false;
                messageTextView.setText("Click START to play again!");
            }
        };

        final ArrayList<String> colorList=new ArrayList<>();
        colorList.add("#FF0000");
        colorList.add("#00FF00");
        colorList.add("#0000FF");
        colorList.add("#FFFF00");

        final ArrayList<String> colorNameList=new ArrayList<>();
        colorNameList.add("RED");
        colorNameList.add("GREEN");
        colorNameList.add("BLUE");
        colorNameList.add("YELLOW");

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownTimer.start();
                messageTextView.setText("Timer has started!");
                playingGame=true;
                score=0;
                scoreTextView.setText("Score : "+score);

                //CHANGE BACKGROUND
                randomColorIndex= new Random().nextInt((3-0)+1)+0;
                String colorName=colorList.get(randomColorIndex);
                constraintLayout.setBackgroundColor(Color.parseColor(colorName));

                //CHANGE COLOR NAME IN TEXTVIEW
                randomColorNameIndex=new Random().nextInt((3-0)+1)+0;
                String colorTextName=colorNameList.get(randomColorNameIndex);
                displayColourNameTextView.setText(colorTextName);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messageTextView.setText("Click start to play!");
                timerTextView.setText("Time left : 30s");
                scoreTextView.setText("Score : 0");
                displayColourNameTextView.setText("-");

                score=0;
                countDownTimer.cancel();
            }
        });

        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playingGame==true){
                    if(randomColorNameIndex==0){
                        score=score+1;
                        scoreTextView.setText("Score : "+score);

                        //CHANGE BACKGROUND
                        randomColorIndex= new Random().nextInt((3-0)+1)+0;
                        String colorName=colorList.get(randomColorIndex);
                        constraintLayout.setBackgroundColor(Color.parseColor(colorName));

                        //CHANGE COLOR NAME IN TEXTVIEW
                        randomColorNameIndex=new Random().nextInt((3-0)+1)+0;
                        String colorTextName=colorNameList.get(randomColorNameIndex);
                        displayColourNameTextView.setText(colorTextName);
                    }else{
                        playingGame=false;
                        countDownTimer.cancel();
                        messageTextView.setText("Wrong answer! RESET and START again!");
                    }
                }
            }
        });

        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playingGame==true){
                    if(randomColorNameIndex==1){
                        score=score+1;
                        scoreTextView.setText("Score : "+score);

                        //CHANGE BACKGROUND
                        randomColorIndex= new Random().nextInt((3-0)+1)+0;
                        String colorName=colorList.get(randomColorIndex);
                        constraintLayout.setBackgroundColor(Color.parseColor(colorName));

                        //CHANGE COLOR NAME IN TEXTVIEW
                        randomColorNameIndex=new Random().nextInt((3-0)+1)+0;
                        String colorTextName=colorNameList.get(randomColorNameIndex);
                        displayColourNameTextView.setText(colorTextName);
                    }else{
                        playingGame=false;
                        countDownTimer.cancel();
                        messageTextView.setText("Wrong answer! RESET and START again!");
                    }
                }
            }
        });

        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playingGame==true){
                    if(randomColorNameIndex==2){
                        score=score+1;
                        scoreTextView.setText("Score : "+score);

                        //CHANGE BACKGROUND
                        randomColorIndex= new Random().nextInt((3-0)+1)+0;
                        String colorName=colorList.get(randomColorIndex);
                        constraintLayout.setBackgroundColor(Color.parseColor(colorName));

                        //CHANGE COLOR NAME IN TEXTVIEW
                        randomColorNameIndex=new Random().nextInt((3-0)+1)+0;
                        String colorTextName=colorNameList.get(randomColorNameIndex);
                        displayColourNameTextView.setText(colorTextName);
                    }else{
                        playingGame=false;
                        countDownTimer.cancel();
                        messageTextView.setText("Wrong answer! RESET and START again!");
                    }
                }
            }
        });

        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playingGame==true){
                    if(randomColorNameIndex==3){
                        score=score+1;
                        scoreTextView.setText("Score : "+score);

                        //CHANGE BACKGROUND
                        randomColorIndex= new Random().nextInt((3-0)+1)+0;
                        String colorName=colorList.get(randomColorIndex);
                        constraintLayout.setBackgroundColor(Color.parseColor(colorName));

                        //CHANGE COLOR NAME IN TEXTVIEW
                        randomColorNameIndex=new Random().nextInt((3-0)+1)+0;
                        String colorTextName=colorNameList.get(randomColorNameIndex);
                        displayColourNameTextView.setText(colorTextName);
                    }else{
                        playingGame=false;
                        countDownTimer.cancel();
                        messageTextView.setText("Wrong answer! RESET and START again!");
                    }
                }
            }
        });

    }
}