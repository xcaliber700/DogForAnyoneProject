package com.example.dogforanyone;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.dogforanyone.User;
import com.example.dogforanyone.UserDao;


@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
}