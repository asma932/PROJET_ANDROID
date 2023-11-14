package com.example.foodapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapplication.Adapter.AdapterListener;
import com.example.foodapplication.Adapter.ReclamationAdapter;
import com.example.foodapplication.DAO.Database.Reclamation;
import com.example.foodapplication.DAO.Database.ReclamationDAO;
import com.example.foodapplication.DAO.Database.Status;
import com.example.foodapplication.DAO.Database.Typereaction;
import com.example.foodapplication.Sqlite.Mydatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity  implements AdapterListener {
    private ReclamationAdapter adapter;

    EditText textfeedbacked;
    TextView happyEmoji,normalEmoji,angryEmoji;
    Button insertbtn;
    private Mydatabase mydatabase;
    private ReclamationDAO reclamationDAO;
    private Typereaction selectedReaction = Typereaction.NEUTRAL; // Added declaration

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydatabase = Mydatabase.getInstance(this);

        reclamationDAO = mydatabase.getDao();
        adapter=new ReclamationAdapter(this,this);

        textfeedbacked = findViewById(R.id.textfeedback);
        insertbtn = findViewById(R.id.insert);

      happyEmoji = findViewById(R.id.happyEmoji);
      normalEmoji = findViewById(R.id.normalEmoji);
   angryEmoji = findViewById(R.id.angryEmoji);



        insertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                String textfeedback = textfeedbacked.getText().toString();

                Typereaction reactionType = getSelectedReactionType();
                    if (TextUtils.isEmpty(textfeedback) || reactionType == null) {
                        Toast.makeText(MainActivity.this, "Please fill both emoji and text", Toast.LENGTH_SHORT).show();
                    } else {
                        Status status=Status.PENDING;
                        Reclamation reclamation = new Reclamation(0,textfeedback, reactionType,status); // Updated constructor
                        adapter.addreclamation(reclamation);
                        reclamationDAO.insert(reclamation);
                        Intent intent = new Intent(MainActivity.this, ListreclamationActivity.class);
                        startActivity(intent);
                        textfeedbacked.setText("");
                        Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();

                    }




            } catch (Exception e) {
                e.printStackTrace();
                Log.e("MainActivity", "Error message", e);
            }}
        });

        happyEmoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setReactionType(Typereaction.SATISFIED);
                bounceEmoji(happyEmoji)    ;
            }
   });

        normalEmoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setReactionType(Typereaction.NEUTRAL);
                bounceEmoji(normalEmoji)    ;
                ;
            }
        });

        angryEmoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setReactionType(Typereaction.ANGRY);
                bounceEmoji(angryEmoji)    ;

            }
        });
    }

    private Typereaction getSelectedReactionType() {

        switch (selectedReaction) {
            case SATISFIED:
                return Typereaction.SATISFIED;
            case NEUTRAL:
                return Typereaction.NEUTRAL;
            case ANGRY:
                return Typereaction.ANGRY;
            default:
                return Typereaction.NEUTRAL;
        }
    }

    private void setReactionType(Typereaction type) {

        selectedReaction = type;
        updateEmojiUI();
    }

    private void updateEmojiUI() {
        Log.d("MainActivity", "Selected Reaction: " + selectedReaction);
        happyEmoji.setTextColor(selectedReaction == Typereaction.SATISFIED ? Color.RED : Color.BLACK);
        normalEmoji.setTextColor(selectedReaction == Typereaction.NEUTRAL ? Color.RED : Color.BLACK);
        angryEmoji.setTextColor(selectedReaction == Typereaction.ANGRY ? Color.RED : Color.BLACK);
    }
    private void bounceEmoji(TextView emojiView) {
    @SuppressLint("ResourceType") Animation bounceAnimation = AnimationUtils.loadAnimation(this, R.xml.anim);
        emojiView.startAnimation(bounceAnimation);
    }


    @Override
    public void OnUpdate(int id, int pos) {

    }

    @Override
    public void OnDelete(int id, int pos) {

    }
}
