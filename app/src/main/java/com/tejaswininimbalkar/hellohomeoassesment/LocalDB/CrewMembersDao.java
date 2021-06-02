package com.tejaswininimbalkar.hellohomeoassesment.LocalDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CrewMembersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CrewMembersEntity entity);

    @Query("SELECT * FROM CrewMembersEntity")
    List<CrewMembersEntity> getAllRecords();

    @Query("DELETE FROM CrewMembersEntity")
    void deleteAll();
}
