package com.example.foodapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter< UserAdapter.MyViewHolder> {

    private Context context;
    private List<User> usersList;
    public UserAdapter(Context context){
        this.context=context;
        usersList=new ArrayList<>();
    }
    public void addUser(User user){
        usersList.add(user);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user=usersList.get(position);
        holder.name.setText(user.getFullName());
        holder.phone1.setText((int) user.getPhone());
        holder.address1.setText(user.getLocation());
        holder.password1.setText(user.getPassword());
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        private TextView name,phone1,address1,password1;
        public MyViewHolder(@NonNull View itenView){
            super(itenView);
            name=itenView.findViewById(R.id.name);
            phone1=itenView.findViewById(R.id.phone1);
            address1=itenView.findViewById(R.id.address1);
            password1=itenView.findViewById(R.id.password1);
        }
    }
}
