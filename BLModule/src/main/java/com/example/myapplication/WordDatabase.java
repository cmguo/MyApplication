package com.example.myapplication;

import androidx.room.Database;

@Database(entities = {Word.class},version = 1, exportSchema = false)
public abstract class WordDatabase extends androidx.room.RoomDatabase {
    public abstract WordDao getWordDao();
}
