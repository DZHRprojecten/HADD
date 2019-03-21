package com.project.hadd.database.repository;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.project.hadd.database.ThemeDatabase;
import com.project.hadd.database.model.Theme;

public class ThemeRepository {

    // region: fields

    private ThemeDatabase themeDatabase;

    // endregion

    // region: constructors

    public ThemeRepository(ThemeDatabase themeDatabase) {
        this.themeDatabase = themeDatabase;
    }

    // endregion

    public LiveData<Theme> getThemeByName(int name) {
        return themeDatabase.ThemeDoa().getThemeByName(name);
    }

    public LiveData<Theme> getThemeById(int id) {
        return themeDatabase.ThemeDoa().getThemeById(id);
    }

    @SuppressLint("StaticFieldLeak")
    public void insertOrReplaceTheme(final Theme theme) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                themeDatabase.ThemeDoa().insertOrReplaceTheme(theme);
                return null;
            }
        }.execute();
    }

    // region: methods

    // endregion

}
