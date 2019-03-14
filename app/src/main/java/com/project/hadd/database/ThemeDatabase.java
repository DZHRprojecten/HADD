package com.project.hadd.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.project.hadd.database.dao.DaoTheme;
import com.project.hadd.database.model.Theme;

@Database(entities = {Theme.class}, version = 1, exportSchema = false)
public abstract class ThemeDatabase extends RoomDatabase {
    public abstract DaoTheme daoTheme();
}
