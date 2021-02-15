package com.kbrosapp.bmiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView resultTextView;
    EditText weightEditText;
    SeekBar heightSeekBar;
    TextView heightTextView;
    Button calculateButton;

    float height;
    float weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView=findViewById(R.id.resultTextView);
        weightEditText=findViewById(R.id.weightEditText);
        heightSeekBar=findViewById(R.id.heightSeekBar);
        heightTextView=findViewById(R.id.heightTextView);
        calculateButton=findViewById(R.id.calculateButton);

        heightSeekBar.setProgress(0);
        heightSeekBar.setMax(300);
        heightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                heightTextView.setText(i+" cms");
                height=i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weight=Float.parseFloat(weightEditText.getText().toString());
                float heightInMeters=height/100;

                float bmi=weight/(heightInMeters*heightInMeters);
                String bmiString="Your BMI is : "+bmi;

                String exercise="";
                String summary="";
                if(bmi<16){
                    summary="Your BMI comes under Severe Thinness Category.";
                }else if(bmi>=16 && bmi<=17){
                    summary="Your BMI comes under Moderate Thinness Category.";
                }else if(bmi>=17 && bmi<=18.5){
                    summary="Your BMI comes under Mild Thinness Category.";
                }else if(bmi>=18.5 && bmi<=25){
                    summary="Your BMI comes under Normal Category.";
                    exercise="You should walk everyday.";
                }else if(bmi>=25 && bmi<=30){
                    summary="Your BMI comes under Overweight Category.";
                }else if(bmi>30){
                    summary="Your BMI comes under Obese Category.";
                }

                resultTextView.setText(bmiString+"\n"+summary+"\n"+exercise);
            }
        });




    }
}