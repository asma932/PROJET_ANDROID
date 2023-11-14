package com.example.foodapplication.DAO.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Reclamation {


    @PrimaryKey(autoGenerate =true)
    public int id;
    @ColumnInfo
    public String textfeedback;

    @ColumnInfo(name = "reaction_type")
    public Typereaction reactionType;

    public void setId(int id) {
        this.id = id;
    }

    public void setTextfeedback(String textfeedback) {
        this.textfeedback = textfeedback;
    }

    public void setReactionType(Typereaction reactionType) {
        this.reactionType = reactionType;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @ColumnInfo(name = "Status")
    public Status status;

    public Reclamation() {
    }

    public int getId() {
        return id;
    }

    public String getTextfeedback() {
        return textfeedback;
    }

    public Typereaction getReactionType() {
        return reactionType;
    }

    public Status getStatus() {
        return status;
    }



    public Reclamation(int id ,String textfeedback, Typereaction reactionType,Status status) {
        this.id=id;
        this.textfeedback = textfeedback;
        this.reactionType = reactionType;
        this.status=status;
    }





}
