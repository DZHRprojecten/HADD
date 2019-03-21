package com.project.hadd.database.dao;

import android.arch.lifecycle.LiveData;
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

    // region: select

    @Query("SELECT * FROM Theme")
    Theme[] getAllThemes();

    // endregion


    // region: insert

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrReplaceTheme(Theme theme);

    // endregion

    @Insert
    void insertMultipleThemes(List<Theme> themeList);

    @Query("SELECT * FROM Theme WHERE themeId = :themeId")
    LiveData<Theme> getThemeById(int themeId);

    @Query("SELECT * FROM Theme WHERE theme_name = :themeName")
    LiveData<Theme> getThemeByName(int themeName);

    @Update
    void updateTheme(Theme movies);

    @Delete
    void deleteTheme(Theme movies);

}
