package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomActivity extends AppCompatActivity {
    Button register;
    TextView firstSign;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        register =findViewById(R.id.button);
        register.setOnClickListener(e->{
            Intent intent=new Intent(this,RegistrationActivity.class);
            startActivity(intent);
        });
        firstSign=findViewById(R.id.firstsign);
    }



}