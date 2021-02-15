package com.kbrosapp.nimbleqecomm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BuyActivity extends AppCompatActivity {

    ImageView image;
    TextView productNameTextView;
    TextView productPriceTextView;
    EditText personNameEditText;
    EditText personAddressEditText;
    Button orderButton;

    DatabaseReference databaseReference;

    Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        String name= getIntent().getStringExtra("name");
        String url=getIntent().getStringExtra("url");
        String price=getIntent().getStringExtra("price");

        order=new Order(name,url,price);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Orders");

        image=findViewById(R.id.image);
        productNameTextView=findViewById(R.id.productNameTextView);
        productPriceTextView=findViewById(R.id.productPriceTextView);

        personNameEditText=findViewById(R.id.personNameEditText);
        personAddressEditText=findViewById(R.id.personAddressEditText);
        orderButton=findViewById(R.id.orderButton);


        Glide.with(this).load(url).into(image);
        productNameTextView.setText(name);
        productPriceTextView.setText(price);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String personName= personNameEditText.getText().toString();
                String personAddress= personAddressEditText.getText().toString();

                order.setPersonName(personName);
                order.setPersonAddress(personAddress);

                databaseReference.push().setValue(order);

                Toast.makeText(BuyActivity.this, "ORDER PLACED!", Toast.LENGTH_SHORT).show();

                finish();
            }
        });



    }
}