package com.example.food_application;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.food_application.MyDatabaseHelper;

public class AddActivity extends AppCompatActivity {

    EditText title_input, question_input, option1_input, option2_input, option3_input;
    ImageView quiz_image;
    Button add_button;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        question_input = findViewById(R.id.question_input);
        option1_input = findViewById(R.id.option1_input);
        option2_input = findViewById(R.id.option2_input);
        option3_input = findViewById(R.id.option3_input);
        quiz_image = findViewById(R.id.quiz_image);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Validate input fields
                if (validateInput()) {
                    // Input is valid, add the quiz to the database
                    MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                    myDB.addQuiz(
                            title_input.getText().toString().trim(),
                            question_input.getText().toString().trim(),
                            option1_input.getText().toString().trim(),
                            option2_input.getText().toString().trim(),
                            option3_input.getText().toString().trim(),
                            R.drawable.carotte // Use the image resource identifier
                    );
                }
            }
        });
    }

    private boolean validateInput() {
        // Perform validation checks here
        if (title_input.getText().toString().trim().isEmpty()
                || question_input.getText().toString().trim().isEmpty()
                || option1_input.getText().toString().trim().isEmpty()
                || option2_input.getText().toString().trim().isEmpty()
                || option3_input.getText().toString().trim().isEmpty()) {

            if (title_input.getText().toString().trim().isEmpty()) {
                title_input.setError("Title cannot be empty");
            }
            if (question_input.getText().toString().trim().isEmpty()) {
                question_input.setError("Question cannot be empty");
            }
            if (option1_input.getText().toString().trim().isEmpty()) {
                option1_input.setError("Option 1 cannot be empty");
            }
            if (option2_input.getText().toString().trim().isEmpty()) {
                option2_input.setError("Option 2 cannot be empty");
            }
            if (option3_input.getText().toString().trim().isEmpty()) {
                option3_input.setError("Option 3 cannot be empty");
            }

            return false;
        }

        // Add similar checks for other input fields as needed

        return true;
    }


}
