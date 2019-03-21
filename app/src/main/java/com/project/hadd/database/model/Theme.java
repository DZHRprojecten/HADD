package com.project.hadd.database.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Theme {

    // region: fields

    @PrimaryKey
    private int themeId;

    @ColumnInfo(name = "theme_name")
    private int themeName;

    // endregion

    // region: constructors

    /**
     * Default Constructor
     * <p>
     * Room Database will use this no-arg constructor by default.
     * The others are annotated with @Ignore,
     * so Room will not give a warning about "Multiple Good Constructors".
     */
    public Theme() {
    }

    @Ignore
    public Theme(int themeId, int themeName) {
        this.themeId = themeId;
        this.themeName = themeName;
    }

    // endregion

    // region: getters & setters

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
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