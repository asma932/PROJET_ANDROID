package com.example.food_application;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity {
    private ImageButton tryAgainButton;
    private ImageButton exitButton;
    private ImageButton nextButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        nextButton = findViewById(R.id.nextButton);

        exitButton = findViewById(R.id.exitButton);
        tryAgainButton = findViewById(R.id.tryAgainButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToNextQuiznext();
            }
        });

        // Retrieve data from the intent
        int score = getIntent().getIntExtra("FINAL_SCORE", 0);
        int numCorrect = getIntent().getIntExtra("NUM_CORRECT", 0);
        int numIncorrect = getIntent().getIntExtra("NUM_INCORRECT", 0);
        int totalQuestions = getIntent().getIntExtra("TOTAL_QUESTIONS", 0);

// Assuming maxScore is 4 based on your switch case

        // Calculate the percentage
        int percentage = (int) (((float) numCorrect / totalQuestions) * 100);
        // Display the results
        TextView scoreTextView = findViewById(R.id.scoreTextView);
        TextView correctTextView = findViewById(R.id.correctTextView);
        TextView incorrectTextView = findViewById(R.id.incorrectTextView);

// Set text color for correct TextView to green
        correctTextView.setTextColor(Color.parseColor("#196F3D"));

// Set text color for incorrect TextView to red
        incorrectTextView.setTextColor(Color.RED);


        scoreTextView.setText("Score: " + percentage + "%");
        correctTextView.setText("Réponses correctes: " + numCorrect);
        incorrectTextView.setText("Réponses incorrectes: " + numIncorrect);

        tryAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });

        // Load and display the image based on the score
        ImageView resultImage = findViewById(R.id.resultImage);

        if (percentage >= 0 && percentage <= 50) {
            // Display Image 1 for scores 0-50
            resultImage.setImageBitmap(getCircleBitmap(R.drawable.triste));
        } else if (percentage > 50 && percentage <= 85) {
            // Display Image 2 for scores 51-85
            resultImage.setImageBitmap(getCircleBitmap(R.drawable.ooo));
        } else if (percentage > 85 && percentage <= 100) {
            // Display Image 3 for scores 86-100
            resultImage.setImageBitmap(getCircleBitmap(R.drawable.iuii));
        } else {
            // Handle other cases or provide a default image
            resultImage.setImageBitmap(getCircleBitmap(R.drawable.body));
        }

    }

    private void moveToNextQuiznext() {
        // Start Quiz 2 activity
        Intent quiz2Intent = new Intent(this, Quiz2.class);
        startActivity(quiz2Intent);
        finish(); // Terminez cette activité pour ne pas conserver plusieurs instances
    }

    private Bitmap getCircleBitmap(int imageResource) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageResource);
        Bitmap circleBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(circleBitmap);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(android.graphics.Color.WHITE);

        float centerX = bitmap.getWidth() / 2f;
        float centerY = bitmap.getHeight() / 2f;
        float radius = Math.min(centerX, centerY);

        canvas.drawCircle(centerX, centerY, radius, paint);

        paint.setXfermode(new android.graphics.PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC_IN));

        canvas.drawBitmap(bitmap, 0, 0, paint);

        return circleBitmap;
    }
}
