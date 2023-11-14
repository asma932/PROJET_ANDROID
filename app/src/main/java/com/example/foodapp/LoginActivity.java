package com.example.foodapp;

import static java.lang.Math.log;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodapp.dao.UserDao;
import com.example.foodapp.database.UserDataBase;
import com.example.foodapp.entities.User;

import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button signInButton;
    private UserDataBase userDataBase;
    private UserDao userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameEditText = findViewById(R.id.fullname1);
        passwordEditText =findViewById(R.id.password2);
        signInButton = findViewById(R.id.sign_sec);
        userDataBase = UserDataBase.getInstance(this);
        userDao = userDataBase.getDao();

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                Executors.newSingleThreadExecutor().execute(() -> {
                    User user = userDataBase.getDao().login(username, password);
                    runOnUiThread(() -> {
                        if (user != null) {

                            showToast("Bienvenue"+username);
                            Intent intent = new Intent(LoginActivity.this, DetailsActivity.class);
                            intent.putExtra("userId", user.getId());
                            Log.d("LoginActivity","is"+user.getId());
                            startActivity(intent);
                        } else {

                            showToast("Nom d'utilisateur ou mot de passe incorrect");
                        }
                    });
                });
                usernameEditText.setText("");
                passwordEditText.setText("");
            }

        });
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void registre(View view) {
        startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
    }

    public void details(View view) {
        startActivity(new Intent(LoginActivity.this, DetailsActivity.class));
    }
}