package com.project.hadd.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.project.hadd.database.model.Theme;

import java.util.List;

@Dao
public interface DaoTheme {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOnlySingleTheme(Theme theme);

    @Insert
    void insertMultipleThemes(List<Theme> themeList);

    @Query("SELECT*FROM Theme WHERE themeId =:themeId")
    Theme fetchOneThemesbyThemeId(int themeId);

    @Query("SELECT*FROM Theme WHERE themeName =:themeName")
    Theme fetchOneThemesbyThemeName(int themeName);

    @Update
    void updateTheme(Theme movies);

    @Delete
    void deleteTheme(Theme movies);
}
