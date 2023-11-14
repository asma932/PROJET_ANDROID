package com.example.foodapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodapp.entities.User;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface UserDao {
    @Insert
    public void addUser(User user);
    @Delete
    public void deleteUser(User user);
    @Update
    public void updateUser(User user);
    @Query("Select * from User")
    List<User> getAllUsers();
    @Query("SELECT * FROM User WHERE fullName = :username AND password = :password")
    User login(String username, String password);
    @Query("SELECT * FROM user WHERE fullName = :username LIMIT 1")
    User getUserByUsername(String username);
    @Query("SELECT * FROM user WHERE id = :userId LIMIT 1")
    User getUserById(long userId);




}
