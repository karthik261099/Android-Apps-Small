package com.nimbleq.rollthedie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView numberTextView;
    Button button;

    int min = 1;
    int max = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberTextView=findViewById(R.id.numberTextView);
        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int random = new Random().nextInt((max - min) + 1) + min;

                numberTextView.setText(String.valueOf(random));
            }
        });

    }
}