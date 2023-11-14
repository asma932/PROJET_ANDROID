package com.example.food_application;

import androidx.appcompat.app.AppCompatActivity;

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

public class Quiz4 extends AppCompatActivity {
    private TextView scoreTextView;
    private TextView questionTextView;
    private RadioGroup radioGroup;
    private ImageView questionImageView;
    private RadioButton option1, option2, option3;
    private Button submitButton;
    private androidx.appcompat.widget.Toolbar quizToolbar;


    private String[] questions = {
            "Quel ingrédient ne fait généralement pas partie d'une salade grecque traditionnelle?",
            "Quelle herbe aromatique est souvent utilisée dans les salades méditerranéennes?",
            "Qu'est-ce qui distingue la salade César classique des autres salades?",
            "Quel est l'ingrédient principal de la salade niçoise?",
            "Quel type de salade est souvent associé à la cuisine asiatique?"
    };

    private String[][] options = {
            {"Feta", "Olives noires", "Avocat"},
            {"Basilic", "Menthe", "Estragon"},
            {"Les croûtons à l'ail", "La vinaigrette au miel", "Les câpres"},
            {"Saumon", "Thon", "Crevettes"},
            {"Salade grecque", "Salade de chou", "Salade César"}
    };

    private int[] correctAnswers = {2,1,0,1,1};
    private int currentQuestion = 0;
    private int score = 0;
    private int numCorrect = 0;
    private int numIncorrect = 0;
    private int totalQuestions= 5;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);

        questionTextView = findViewById(R.id.questionTextView);
        radioGroup = findViewById(R.id.radioGroup);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        questionImageView=findViewById(R.id.questionImageView);
        submitButton = findViewById(R.id.submitButton);
        scoreTextView = findViewById(R.id.scoreTextView);
        quizToolbar = findViewById(R.id.quizToolbar);
        setSupportActionBar(quizToolbar);
        updateQuestion();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
            }
        });
    }

    private void updateQuestion() {
        questionTextView.setText(questions[currentQuestion]);
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
                return R.drawable.ingredient;
            case 1:
                return R.drawable.herbearomatique;
            case 2:
                return R.drawable.salad;
            case 3:
                return R.drawable.salade;
            case 4:
                return R.drawable.asiatique;
            default:
                return R.drawable.image2;
        }
    }
    private void checkAnswer() {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId != -1) {
            final RadioButton selectedOption = findViewById(selectedId);
            int selectedOptionIndex = radioGroup.indexOfChild(selectedOption);
            boolean isCorrect = selectedOptionIndex == correctAnswers[currentQuestion];

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
            Intent intent = new Intent(this, ResultsActivity.class);
            intent.putExtra("FINAL_SCORE", score);
            intent.putExtra("NUM_CORRECT", numCorrect);
            intent.putExtra("NUM_INCORRECT", numIncorrect);
            intent.putExtra("TOTAL_QUESTIONS", totalQuestions);

            startActivity(intent);
            finish();
        }
    }


}