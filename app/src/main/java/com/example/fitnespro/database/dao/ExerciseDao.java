package com.example.fitnespro.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.fitnespro.database.model.Exercise;

import java.util.List;

@Dao
public interface ExerciseDao {

    @Query("SELECT * FROM exercise")
    List<Exercise> getAll();

    @Query("SELECT * FROM exercise WHERE type = :type AND isDeleted = 0")
    List<Exercise> getAllByType(String type);

    @Insert
    void insert(Exercise exercise);

    @Query("UPDATE Exercise SET isDeleted = 1 WHERE id = :id")
    void delete(long id);

}
