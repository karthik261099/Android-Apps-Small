package com.kbrosapp.rollthedie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button rollButton;
    TextView numberTextView;

    int min = 1;
    int max = 6;
    int random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rollButton=findViewById(R.id.rollButton);
        numberTextView=findViewById(R.id.numberTextView);

        numberTextView.setText("0");

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                random= new Random().nextInt((max - min) + 1) + min;
                numberTextView.setText(String.valueOf(random));
            }
        });

    }
}
