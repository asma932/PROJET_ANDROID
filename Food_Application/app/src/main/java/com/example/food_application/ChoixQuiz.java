package com.example.food_application;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;
public class ChoixQuiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choix_quiz);

        ImageView imageOption1 = findViewById(R.id.imageOption1);
        ImageView imageOption2 = findViewById(R.id.imageOption2);
        ImageView imageOption3 = findViewById(R.id.imageOption3);
        ImageView imageOption4 = findViewById(R.id.imageOption4);
        ImageView imageOption5 = findViewById(R.id.imageOption5);
        ImageView imageOption6 = findViewById(R.id.imageOption6);

        imageOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click on Option 1
                moveToMainActivity();
            }
        });

        imageOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click on Option 2
                moveToMainActivity2();
            }
        });
        imageOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click on Option 2
                moveToMainActivity3();
            }
        });

        imageOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click on Option 1
                moveToMainActivity4();
            }
        });

        imageOption5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click on Option 2
                moveToMainActivity5();
            }
        });
        imageOption6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click on Option 2
                moveToMainActivity6();
            }
        });

    }


    public void moveToMainActivity() {
        // Start Quiz 1 activity
        Intent quiz1Intent = new Intent(this, Quiz1.class);
        startActivity(quiz1Intent);
    }

    public void moveToMainActivity2() {
        // Start Quiz 2 activity
        Intent quiz2Intent = new Intent(this, Quiz2.class);
        startActivity(quiz2Intent);
    }
    public void moveToMainActivity3() {
        // Start Quiz 2 activity
        Intent quiz3Intent = new Intent(this, Quiz3.class);
        startActivity(quiz3Intent);
    }
    public void moveToMainActivity4() {
        // Start Quiz 1 activity
        Intent quiz4Intent = new Intent(this, Quiz4.class);
        startActivity(quiz4Intent);
    }

    public void moveToMainActivity5() {
        // Start Quiz 2 activity
        Intent quiz5Intent = new Intent(this, Quiz5.class);
        startActivity(quiz5Intent);
    }
    public void moveToMainActivity6() {
        // Start Quiz 2 activity
        Intent quiz6Intent = new Intent(this, Quiz6.class);
        startActivity(quiz6Intent);
    }
}



