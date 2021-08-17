package com.example.xcrews.db;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.xcrews.model.Crew;

import java.util.List;

@Dao
public interface CrewDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCrew(Crew crew);

    @Query("SELECT * FROM crew_table")
    LiveData<List<Crew>> getAllCrew();

    @Query("DELETE FROM crew_table")
    void deleteAllCrew();
}
