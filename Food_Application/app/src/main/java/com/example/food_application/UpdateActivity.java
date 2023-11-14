package com.example.food_application;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText title_input, question_input, option1_input, option2_input, option3_input;
    Button update_button, delete_button;
    ImageView questionImageView,quiz_image;


    String id, title, question,image,option1,option2,option3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input2);
        question_input=findViewById(R.id.question_input2);
        option1_input=findViewById(R.id.option1_input2);
        option2_input=findViewById(R.id.option2_input2);
        option3_input=findViewById(R.id.option3_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                title = title_input.getText().toString().trim();
                question = question_input.getText().toString().trim();
                option1 = option1_input.getText().toString().trim();
                option2=option2_input.getText().toString().trim();
                option3=option3_input.getText().toString().trim();
                myDB.updateData(id, title, question, option1,option2,option3);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") && getIntent().hasExtra("yy") && getIntent().hasExtra("uu") && getIntent().hasExtra("ii")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            question = getIntent().getStringExtra("author");
            option1 = getIntent().getStringExtra("yy");
            option2 = getIntent().getStringExtra("uu");
            option3 = getIntent().getStringExtra("ii");
            //Setting Intent Data
            title_input.setText(title);
            question_input.setText(question);
            option1_input.setText(option1);
            option2_input.setText(option2);
            option3_input.setText(option3);
            Log.d("stev", title+" "+question+" "+option1+" "+option2+" "+option3);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}