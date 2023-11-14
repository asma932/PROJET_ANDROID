package com.example.foodapplication.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapplication.DAO.Database.Reclamation;
import com.example.foodapplication.DAO.Database.Status;
import com.example.foodapplication.R;

import java.util.ArrayList;
import java.util.List;


public class ReclamationAdapter extends RecyclerView.Adapter<ReclamationAdapter.MyViewHolder>{
private Context context;
private List<Reclamation>reclamationList;

private AdapterListener  adapterListener;
public ReclamationAdapter(Context context,AdapterListener adapterListener)
{this.context=context;
reclamationList=new ArrayList<>();
this.adapterListener=adapterListener;
}
public void addreclamation(Reclamation reclamation)
{reclamationList.add(reclamation);
notifyDataSetChanged();}
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listreclamation,parent,false);
        return new MyViewHolder(view);
    }
public void remove(int position)
{reclamationList.remove(position);
notifyDataSetChanged();}



    public void update(int position) {
        reclamationList.get(position).setStatus(Status.TREATED);
        notifyItemChanged(position);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
Reclamation reclamation=reclamationList.get(position);
holder.textfeedback.setText(reclamation.getTextfeedback());
        holder.status.setText("Status: " + reclamation.getStatus().toString());
        holder.reactionType.setText("Reaction Type: " + reclamation.getReactionType().toString());

        holder.empty.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {
                                                 adapterListener.OnUpdate(reclamation.getId(),position);



                                             }
                                         });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterListener.OnDelete(reclamation.getId(),position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return reclamationList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
    private TextView textfeedback,status,reactionType;
    private ImageView empty,delete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textfeedback=itemView.findViewById(R.id.textfeedback);
            status = itemView.findViewById(R.id.status);
            reactionType = itemView.findViewById(R.id.reactionType);
            empty = itemView.findViewById(R.id.empty);
            delete = itemView.findViewById(R.id.delete);

        }
    }


}
