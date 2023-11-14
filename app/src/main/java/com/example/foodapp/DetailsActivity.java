package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodapp.dao.UserDao;
import com.example.foodapp.database.UserDataBase;
import com.example.foodapp.entities.User;

import java.util.concurrent.Executors;

public class DetailsActivity extends AppCompatActivity {
    private UserDataBase userDataBase;
    private UserDao userDao;
    private Button btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        userDataBase = UserDataBase.getInstance(this);
        userDao = userDataBase.getDao();

        int userId = getIntent().getIntExtra("userId",-1);


        Executors.newSingleThreadExecutor().execute(() -> {
            User userDetails = userDataBase.getDao().getUserById(userId);

            runOnUiThread(() -> {
                
                configureUI(userDetails);
            });
        });
    }

    @SuppressLint("SetTextI18n")
    private void configureUI(User userDetails) {

        TextView textViewFullName = findViewById(R.id.textViewFullName);
        TextView textViewPhone = findViewById(R.id.textViewPhone);
        TextView textViewLocation = findViewById(R.id.textViewLocation);
        TextView textViewPassword = findViewById(R.id.textViewPassword);


        textViewFullName.setText("Nom complet : " + userDetails.getFullName());
        textViewPhone.setText("Téléphone : " + userDetails.getPhone());
        textViewLocation.setText("Emplacement : " + userDetails.getLocation());
        textViewPassword.setText("Mot de passe : " + userDetails.getPassword());
    }
}

