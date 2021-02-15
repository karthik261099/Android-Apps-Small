package com.kbrosapp.clickchangebackground;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button;
    ConstraintLayout constraintLayout;

    int min = 0;
    int max = 3;
    int random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.button);
        constraintLayout=findViewById(R.id.layout);

        final ArrayList<String> colours=new ArrayList<>();
        colours.add("#FF0000");
        colours.add("#00FF00");
        colours.add("#0000FF");
        colours.add("#FFFF00");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                random = new Random().nextInt((max - min) + 1) + min;

                constraintLayout.setBackgroundColor(Color.parseColor(colours.get(random)));
            }
        });

    }
}