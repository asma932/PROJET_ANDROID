package com.example.food_application;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class Quiz1 extends AppCompatActivity {
    private TextView scoreTextView;
    private TextView questionTextView,questionTextView1;
    private RadioGroup radioGroup;
    private RadioButton option1, option2, option3;
    private ImageView questionImageView;
    private Button submitButton;
    private androidx.appcompat.widget.Toolbar quizToolbar;


    private String[] questions = {
            "Quels sont les ingrédients de base d'une pizza Margherita ?",
            "La pizza Hawaïenne contient-elle des ananas ?",
            "La pizza Pepperoni est-elle une pizza épicée ?"
    };

    private String[][] options = {
            {"Tomate, Mozzarella, Basilic", "Champignons, Poivrons, Oignons", "Anchois, Olives, Capres"},
            {"Oui", "Non", "Parfois"},
            {"Oui", "Non", "Ça dépend du restaurant"}
    };

    private boolean[] correctAnswers = {true, true, false};

    private int currentQuestion = 0;
    private int totalQuestions = 3; // Set the total number of questions here


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);

        questionTextView = findViewById(R.id.questionTextView);
        questionTextView1 = findViewById(R.id.questionTextView1);

        radioGroup = findViewById(R.id.radioGroup);
        quizToolbar = findViewById(R.id.quizToolbar);
        setSupportActionBar(quizToolbar);
        questionImageView = findViewById(R.id.questionImageView);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        submitButton = findViewById(R.id.submitButton);
        scoreTextView = findViewById(R.id.scoreTextView);

        updateQuestion();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
                //  showCustomAlert();
            }
        });
    }

    private void updateQuestion() {
        questionTextView.setText(questions[currentQuestion]);
        //getSupportActionBar().setTitle("Question " + (currentQuestion + 1));
        getSupportActionBar().setTitle("Question " + (currentQuestion + 1) + "/" + totalQuestions);

        questionImageView.setImageResource(getImageResourceForQuestion(currentQuestion));
        option1.setText(options[currentQuestion][0]);
        option2.setText(options[currentQuestion][1]);
        option3.setText(options[currentQuestion][2]);
    }

    private int getImageResourceForQuestion(int questionIndex) {
        // Replace this with your logic to map each question to the corresponding image resource
        switch (questionIndex) {
            case 0:
                return R.drawable.pizza;
            case 1:
                return R.drawable.pizzahawaienne;
            case 2:
                return R.drawable.pizzapepperoni;
            default:
                return R.drawable.quiz;
        }
    }







    private int score = 0;  // Déclarer la variable de score à un niveau plus élevé

    // ... (le reste du code)

    private int numCorrect = 0;  // Ajouter une variable pour le nombre de réponses correctes
    private int numIncorrect = 0;  // Ajouter une variable pour le nombre de réponses incorrectes

    // ... (le reste du code)

    private void checkAnswer() {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId != -1) {
            final RadioButton selectedOption = findViewById(selectedId);
            int selectedOptionIndex = radioGroup.indexOfChild(selectedOption);
            boolean isCorrect = (selectedOptionIndex == 0) == correctAnswers[currentQuestion];

            String questionContent = questions[currentQuestion];
            String correctAnswer = options[currentQuestion][0];

            if (isCorrect) {
                String correctMessage = "Correct! La réponse correcte pour \"" + questionContent + "\" est : " + correctAnswer;
                Toast.makeText(this, correctMessage, Toast.LENGTH_SHORT).show();
                score++; // Augmenter le score en cas de réponse correcte
                numCorrect++; // Augmenter le nombre de réponses correctes

                // Change background color to green for correct answer
                selectedOption.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);

                // Automatically move to the next question after a delay (e.g., 1000 milliseconds)
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Revert the background color back to the initial color
                        selectedOption.getBackground().clearColorFilter();

                        // Move to the next question
                        moveToNextQuestion();
                    }
                }, 1000); // 1000 milliseconds = 1 second
            } else {
                String incorrectMessage = "Incorrect. La réponse correcte pour \"" + questionContent + "\" est : " + correctAnswer;
                Toast.makeText(this, incorrectMessage, Toast.LENGTH_SHORT).show();
                numIncorrect++; // Augmenter le nombre de réponses incorrectes

                // Change background color to red for incorrect answer
                selectedOption.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);

                // Revert the background color back to the initial color after a delay
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Revert the background color back to the initial color
                        selectedOption.getBackground().clearColorFilter();

                        // Move to the next question
                        moveToNextQuestion();
                    }
                }, 1000); // 1000 milliseconds = 1 second
            }

            // Mettre à jour le TextView du score
            scoreTextView.setText("Score: " + score);
        } else {
            Toast.makeText(this, "Veuillez sélectionner une réponse", Toast.LENGTH_SHORT).show();
        }
    }


    private void moveToNextQuestion() {
        currentQuestion++;

        if (currentQuestion < questions.length) {
            updateQuestion();
            radioGroup.clearCheck();
        } else {
            // All questions answered, start the ResultsActivity

            // Create an intent to start the ResultsActivity
            Intent intent = new Intent(Quiz1.this, ResultsActivity.class);
            // Pass data to the results activity
            intent.putExtra("FINAL_SCORE", score);
            intent.putExtra("NUM_CORRECT", numCorrect);
            intent.putExtra("NUM_INCORRECT", numIncorrect);
            intent.putExtra("TOTAL_QUESTIONS", totalQuestions);

            // Start the results activity
            startActivity(intent);
            // Finish the current activity to prevent going back to the quiz
            finish();
        }
    }

}





