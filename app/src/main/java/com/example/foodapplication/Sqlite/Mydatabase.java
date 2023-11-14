package com.example.foodapplication.Sqlite;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodapplication.DAO.Database.Reclamation;
import com.example.foodapplication.DAO.Database.ReclamationDAO;

@Database(entities = {Reclamation.class}, version = 1)
public abstract class Mydatabase extends RoomDatabase {
    public abstract ReclamationDAO getDao() ;

    public static Mydatabase INSTANCE;
    public  static  Mydatabase getInstance( Context context) {
       if(INSTANCE ==null)
       {INSTANCE= Room.databaseBuilder(context,Mydatabase.class,"ReclamationList").allowMainThreadQueries().fallbackToDestructiveMigration().build();}
   return INSTANCE;
    }

  
}
