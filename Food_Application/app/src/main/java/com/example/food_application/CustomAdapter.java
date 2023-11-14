package com.example.food_application;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private final Context context;
    private Activity activity;
    private final ArrayList id, title, question, option1, option2, option3;

    CustomAdapter(Activity activity, Context context, ArrayList id, ArrayList title, ArrayList question,
                  ArrayList option1, ArrayList option2, ArrayList option3) {
        this.activity = activity;
        this.context = context;
        this.id = id;
        this.title = title;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.question_input.setText(String.valueOf(question.get(position)));

        // Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("title", String.valueOf(title.get(position)));
                intent.putExtra("author", String.valueOf(question.get(position)));
                intent.putExtra("yy", String.valueOf(option1.get(position)));
                intent.putExtra("uu", String.valueOf(option2.get(position)));
                intent.putExtra("ii", String.valueOf(option3.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView question_input;
        ImageView questionImageView;
        LinearLayout mainLayout;
        Button startQuizBtn;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            question_input = itemView.findViewById(R.id.question_input);
            startQuizBtn = itemView.findViewById(R.id.startQuizBtn);
            mainLayout = itemView.findViewById(R.id.mainLayout);

            // Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);

            // Set onClickListener for the startQuizBtn
            startQuizBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int adapterPosition = getAdapterPosition();
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        startQuiz(adapterPosition);
                    }
                }
            });
        }

        // Add a method to handle the startQuizBtn click event
        private void startQuiz(int position) {
            // Add your logic to start the quiz here
            // You can use the position parameter to get information about the clicked item
            // For example, id.get(position), title.get(position), etc.
            Intent intent = new Intent(context, Quiz1.class);
            // Pass relevant data to the Quiz1 activity
            intent.putExtra("question", String.valueOf(question.get(position)));
            intent.putExtra("option1", String.valueOf(option1.get(position)));
            intent.putExtra("option2", String.valueOf(option2.get(position)));
            intent.putExtra("option3", String.valueOf(option3.get(position)));
            context.startActivity(intent);
        }
    }
}
