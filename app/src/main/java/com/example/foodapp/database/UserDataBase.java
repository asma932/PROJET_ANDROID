package com.example.foodapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodapp.dao.UserDao;
import com.example.foodapp.entities.User;

@Database(entities = {User.class},version = 3,exportSchema = true)
public abstract class UserDataBase extends RoomDatabase {
    public abstract UserDao getDao();
    public static  UserDataBase instance;
    public static UserDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, UserDataBase.class, "userDatabase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return instance;
    }
}
