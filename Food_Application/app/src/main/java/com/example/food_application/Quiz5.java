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

public class Quiz5 extends AppCompatActivity {

    private TextView scoreTextView;
    private TextView questionTextView;
    private RadioGroup radioGroup;
    private ImageView questionImageView;
    private RadioButton option1, option2, option3,option4;
    private androidx.appcompat.widget.Toolbar quizToolbar;

    private Button submitButton;

    private String[] questions = {
            "Quel est l'ingrédient principal dans le jus d'orange frais ?",
            "Comment préparez-vous un jus de pomme fait maison ?",
            "Quel est l'ingrédient clé dans un smoothie aux baies ?",
            "Quels ingrédients sont nécessaires pour faire un jus de carotte ?",
            "Quel fruit est souvent utilisé dans les jus détox ?",
            "Quelle est l'ingrédient principal dans un jus de jugo de sandía ?",
            "Comment préparez-vous une Limonade Naturelle ?",
            "Quel est l'ingrédient principal dans le jus de citron vert ?",
            "Quels fruits sont généralement utilisés pour préparer un smoothie tropical ?",
            "Quel est l'ingrédient clé dans un jus de céleri ?",
            "Comment préparez-vous un jus de raisin rouge ?",
            "Quelle baie est souvent incluse dans les jus antioxydants ?",
            "Quel est l'ingrédient de base dans un jus de canneberge ?",
            "Comment préparez-vous un jus de grenade ?"
    };

    private String[][] options = {
            {"Orange", "Pomme", "Banane", "Raisin"},
            {"Presser les pommes", "Mixer les pommes avec de l'eau", "Faire bouillir les pommes", "Écraser les pommes"},
            {"Banane", "Fraises", "Myrtilles", "Ananas"},
            {"Carottes, Pommes, Gingembre", "Tomates, Oranges, Céleri", "Épinards, Pommes, Concombre", "Ananas, Mangue, Kiwi"},
            {"Framboises", "Ananas", "Citron", "Concombre"},
            {"Pastèque", "Orange", "Pomme", "Cerise"},
            {"Presser les citrons", "Mixer les citrons avec de l'eau", "Faire bouillir les citrons", "Écraser les citrons"},
            {"Citron vert", "Orange", "Ananas", "Mangue"},
            {"Banane, Ananas, Mangue", "Fraises, Myrtilles, Framboises", "Papaye, Grenade, Noix de coco", "Pêche, Kiwi, Melon"},
            {"Céleri", "Carottes", "Épinards", "Concombre"},
            {"Presser les raisins rouges", "Faire bouillir les raisins rouges", "Mixer les raisins rouges avec de l'eau", "Écraser les raisins rouges"},
            {"Myrtille", "Framboise", "Cassis", "Airelle"},
            {"Canneberge", "Pomme", "Cerise", "Orange"},
            {"Presser les graines de grenade", "Mixer les graines de grenade avec de l'eau", "Faire bouillir les graines de grenade", "Écraser les graines de grenade"}



    };

// ...


    private int[] correctAnswers = {0, 0, 1, 0, 3, 0, 0, 0, 0, 2, 0, 1, 0, 0, 0};

    private int currentQuestion = 0;
    private int score = 0;
    private int numCorrect = 0;
    private int numIncorrect = 0;
    private int totalQuestions= 14;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz5);

        questionTextView = findViewById(R.id.questionTextView);
        radioGroup = findViewById(R.id.radioGroup);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        questionImageView= findViewById(R.id.questionImageView);
        quizToolbar = findViewById(R.id.quizToolbar);
        setSupportActionBar(quizToolbar);

        submitButton = findViewById(R.id.submitButton);
        scoreTextView = findViewById(R.id.scoreTextView);

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
        option4.setText(options[currentQuestion][3]);


    }
    private int getImageResourceForQuestion(int questionIndex) {
        // Replace this with your logic to map each question to the corresponding image resource
        switch (questionIndex) {
            case 0:
                return R.drawable.orange;
            case 1:
                return R.drawable.pomme;
            case 2:
                return R.drawable.smoothie;
            case 3:
                return R.drawable.carotte;
            case 4:
                return R.drawable.detox;
            case 5:
                return R.drawable.sanda;
            case 6:
                return R.drawable.limonade;
            case 7:
                return R.drawable.fruits;
            case 8:
                return R.drawable.tropical;
            case 9:
                return R.drawable.creli;
            case 10:
                return R.drawable.raisin ;
            case 11:
                return R.drawable.antioxydants;
            case 12:
                return R.drawable.canneberge;
            case 13:
                return R.drawable.gernade;
            default:
                return R.drawable.image1;
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
