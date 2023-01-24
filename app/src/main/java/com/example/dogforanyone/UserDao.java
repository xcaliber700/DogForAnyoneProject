package com.example.dogforanyone;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = IGNORE)
    void insertOneUser(User user);

}