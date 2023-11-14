package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.ColumnInfo;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodapp.dao.UserDao;
import com.example.foodapp.database.UserDataBase;
import com.example.foodapp.entities.User;

public class RegistrationActivity extends AppCompatActivity {

        EditText name, address, phone, password;
        Button insertBtn;
        private UserDataBase userDataBase;
        private UserDao userDao;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_registration);

            insertBtn = findViewById(R.id.insertBtn);
            userDataBase = UserDataBase.getInstance(this);
            userDao = userDataBase.getDao();

            name = findViewById(R.id.fullname);
            address = findViewById(R.id.address);
            phone = findViewById(R.id.phone);
            password = findViewById(R.id.password);

            insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String fullName = name.getText().toString();
                    long phone1 = Long.parseLong(phone.getText().toString());
                    String location = address.getText().toString();
                    String password1 = password.getText().toString();

                    User user = new User(0, fullName, phone1, location, password1);
                    userDao.addUser(user);
                    name.setText("");
                    phone.setText("");
                    address.setText("");
                    password.setText("");
                    Toast.makeText(RegistrationActivity.this, "inserted", Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void login(View view) {
            startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
        }
    }

