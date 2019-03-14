package com.project.hadd.database.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Theme {

    // region: fields

    @NonNull
    @PrimaryKey
    private String themeId;
    private int themeName;

    // endregion

    // region: constructors

    public Theme() {
    }

    // endregion

    // region: getters & setters

    @NonNull
    public String getThemeId() {
        return themeId;
    }

    public void setThemeId(@NonNull String themeId) {
        this.themeId = themeId;
    }

    public int getThemeName() {
        return themeName;
    }

    public void setThemeName(int themeName) {
        this.themeName = themeName;
    }

    // endregion

}