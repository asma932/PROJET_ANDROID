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

public class Quiz6 extends AppCompatActivity {

    private TextView scoreTextView;
    private TextView questionTextView;
    private RadioGroup radioGroup;
    private RadioButton option1, option2, option3,option4;
    private ImageView questionImageView;

    private androidx.appcompat.widget.Toolbar quizToolbar;

    private Button submitButton;

    private String[] questions = {
            "Quel est l'ingrédient principal dans un gâteau au chocolat ?",
            "Quelle est la différence entre un biscuit et un gâteau ?",
            "Quel type de gâteau est traditionnellement servi lors des anniversaires ?",
            "Quelle est l'origine du tiramisu ?",
            "Quel est l'ingrédient clé dans un gâteau à la carotte ?",
            "Quel est le nom d'un gâteau français composé de couches de crêpes et de crème ?",
            "Quel est l'agent levant couramment utilisé dans la préparation de gâteaux ?",
            "Quelle est l'effet de l'ajout de bicarbonate de soude dans la préparation d'un gâteau ?",
            "Quelle est la fonction principale du zeste de citron dans la préparation d'un gâteau ?",
            "Quel est l'ingrédient clé pour rendre un gâteau moelleux ?",
            "Quel type de farine est généralement utilisé dans la préparation des gâteaux ?",
            "Quelle est la fonction de la vanille dans un gâteau ?",
            "Quel est l'effet de l'ajout de yaourt dans la préparation d'un gâteau ?",
            "Quel ingrédient est généralement utilisé pour faire cuire des crêpes?",
            "Quel est l'ingrédient principal dans un pound cake?",
            "Quel est l'ingrédient clé dans la préparation de la glace à la vanille?"
    };

    private String[][] options = {
            {"Farine", "Sucre", "Chocolat", "Beurre"},
            {"La taille", "La texture", "La forme", "L'ingrédient principal"},
            {"Cheesecake", "Gâteau au fromage", "Gâteau au citron", "Gâteau au chocolat"},
            {"France", "Italie", "Espagne", "Grèce"},
            {"Carottes", "Chocolat", "Noix", "Citrons"},
            {"Tarte Tatin", "Macaron", "Mille-feuille", "Opéra"},
            {"Levure chimique", "Bicarbonate de soude", "Levure de boulanger", "Crème de tartre"},
            {"Ajouter de la douceur", "Apporter de l'acidité", "Augmenter la texture", "Servir comme garniture"},
            {"Augmenter la levée", "Ajouter de la saveur", "Améliorer la couleur", "Épaissir la texture"},
            {"Ajouter de la couleur", "Apporter de l'arôme", "Augmenter la texture", "Épaissir la consistance"},
            {"Farine tout usage", "Farine de blé entier", "Farine d'amande", "Farine de maïs"},
            {"Ajouter de la couleur", "Apporter de l'arôme", "Augmenter la texture", "Épaissir la consistance"},
            {"Augmenter l'humidité", "Ajouter de la saveur", "Accélérer la cuisson", "Améliorer la couleur"},
            {"Farine", "Sucre", "Riz","Beurre"},
            {"Beurre", "Œufs", "Chocolat","Crème fraîche"},
            {"Lait", "Crème fraîche", "Jus d'orange","Sucre"}
    };

private int[] correctAnswers = {2, 1, 3, 1, 0, 2, 0, 1, 1, 0, 0, 1, 0, 0,0, 1};


    private int currentQuestion = 0;
    private int score = 0;
    private int numCorrect = 0;
    private int numIncorrect = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz6);

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
private int totalQuestions=16;
    private void updateQuestion() {
        questionTextView.setText(questions[currentQuestion]);
        getSupportActionBar().setTitle("Question " + (currentQuestion + 1) + "/" + totalQuestions);
        option1.setText(options[currentQuestion][0]);
        option2.setText(options[currentQuestion][1]);
        option3.setText(options[currentQuestion][2]);
        option4.setText(options[currentQuestion][3]);
        questionImageView.setImageResource(getImageResourceForQuestion(currentQuestion));



    }
    private int getImageResourceForQuestion(int questionIndex) {
        // Replace this with your logic to map each question to the corresponding image resource
        switch (questionIndex) {
            case 0:
                return R.drawable.chocolat;
            case 1:
                return R.drawable.deff;
            case 2:
                return R.drawable.tradi;
            case 3:
                return R.drawable.tiramisu;
            case 4:
                return R.drawable.carottegat;
            case 5:
                return R.drawable.crepe;
            case 6:
                return R.drawable.couramment;
            case 7:
                return R.drawable.effet  ;
            case 8:
                return R.drawable.zeste;
            case 9:
                return R.drawable.moelleux;
            case 10:
                return R.drawable.farine;
            case 11:
                return R.drawable.vanille;
            case 12:
                return R.drawable.yaourt;
            case 13:
                return R.drawable.cuire;
            case 14:
                return R.drawable.pound;
            case 15:
                return R.drawable.glace;
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
