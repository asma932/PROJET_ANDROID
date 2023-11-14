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

public class Quiz3 extends AppCompatActivity {
    private TextView scoreTextView;
    private TextView questionTextView;
    private RadioGroup radioGroup;
    private ImageView questionImageView;
    private RadioButton option1, option2, option3;
    private Button submitButton;
    private androidx.appcompat.widget.Toolbar quizToolbar;


    private String[] questions = {
            "Quel type de viande est généralement utilisé dans la chorba ch3ir?",
            "Quel est l'ingrédient principal qui donne de la consistance à la chorba ch3ir?",
            "Quel légume est souvent ajouté à la chorba ch3ir pour plus de saveur?",
            "Quel est l'assaisonnement de base utilisé dans la préparation de la chorba ch3ir?",
            "Laquelle de ces légumineuses est couramment incluse dans la chorba ch3ir?",
            "Quelle est la première étape de la préparation de la chorba ch3ir dans une grande casserole ?",
            "Quand doit-on ajouter les pois chiches et les lentilles dans la préparation de la chorba ch3ir ?",
            "Quel est l'objectif d'incorporer les tomates, le concentré de tomate et les épices dans la chorba ch3ir ?",
            "Quelle est la dernière étape avant de servir la chorba ch3ir ?",
            "Pourquoi doit-on réduire le feu après avoir amené la préparation à ébullition ?",
            "Quelle herbe aromatique est spécifiquement associée à la chorba marocaine et absente dans la chorba algérienne"
    };

    private String[][] options = {
            {"Agneau", "Poulet", "Bœuf"},
            {"Pois chiches", "Lentilles", "Haricots"},
            {"Carottes", "Courgettes", "Poireaux"},
            {"Cumin", "Curcuma", "Paprika"},
            {"Fèves", "Lentilles", "Haricots rouges"},
            {"Faire mijoter les légumes", "Faire revenir les oignons dans de l'huile d'olive", "Ajouter la viande"},
            {"Au début du processus de cuisson", "À la fin du processus de cuisson", "Après avoir fait dorer la viande"},
            {"Ajouter de la couleur", "Donner de la saveur", "c) Faire épaissir la soupe"},
            {"Garnir de persil frais", "Assaisonner avec du sel et du poivre", "Laisser mijoter jusqu'à ce que la viande soit tendre"},
            {"Pour accélérer le processus de cuisson", "Pour éviter que la soupe ne déborde", "Pour intensifier les saveurs"},
            {"Basilic","Menthe","Cilantro (coriandre)"}
    };







    private boolean[] correctAnswers = {true, false, true,true,false,false,true,false,false,false,false};

    private int currentQuestion = 0;
    private int score = 0;
    private int numCorrect = 0;
    private int numIncorrect = 0;
    private int totalQuestions = 11; // Set the total number of questions here

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
                return R.drawable.chorbaa;
            case 1:
                return R.drawable.ingredient;
            case 2:
                return R.drawable.legume;
            case 3:
                return R.drawable.assaisonnement ;
            case 4:
                return R.drawable.legumineuses;
            case 5:
                return R.drawable.etape;
            case 6:
                return R.drawable.temps;
            case 7:
                return R.drawable.objectif;
            case 8:
                return R.drawable.derniere;
            case 9:
                return R.drawable.astus;
            case 10:
                return R.drawable.herbearomatique;
            default:
                return R.drawable.image1;
        }
    }

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
