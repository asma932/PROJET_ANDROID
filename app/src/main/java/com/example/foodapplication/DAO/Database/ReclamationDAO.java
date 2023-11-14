package com.example.foodapplication.DAO.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ReclamationDAO {
    @Insert
    void insert(Reclamation reclamation);

    @Update
    void update(Reclamation reclamation);
    @Query("SELECT * FROM Reclamation WHERE id = :reclamationId")
    Reclamation getById(int reclamationId);


    @Query("delete from Reclamation where id=:id")
    void delete(int id);

    @Query("Select * from Reclamation")
    List<Reclamation> getAll();
}
