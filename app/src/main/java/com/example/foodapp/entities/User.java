package com.example.foodapp.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String fullName;
    @ColumnInfo
    private long phone;
    @ColumnInfo
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @ColumnInfo
    private String password;
    public User() {
    }

    public User(int id,String fullName,long phone,String location,String password) {
        this.id = id;
        this.fullName=fullName;
        this.phone=phone;
        this.location=location;
        this.password=password;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
