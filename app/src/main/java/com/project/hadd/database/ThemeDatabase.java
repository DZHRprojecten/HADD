package com.project.hadd.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.project.hadd.database.dao.DaoTheme;
import com.project.hadd.database.model.Theme;

@Database(entities = {Theme.class}, version = 1, exportSchema = false)
public abstract class ThemeDatabase extends RoomDatabase {

    // region: fields

    private static ThemeDatabase INSTANCE;

    // endregion

    // region: methods

    public static ThemeDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ThemeDatabase.class,
                    "ThemeDatabase")
                    .build();
        }

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    // endregion

    // region: abstraction

    public abstract DaoTheme ThemeDoa();

    // endregion
}
