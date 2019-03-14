package com.project.hadd;

import android.app.Activity;

import android.content.Intent;

/**
 * ThemeUtils is a utility class that changes the theme.
 */
public class ThemeUtils {

    // region: fields

    private static int cTheme;
    public final static int BLACK = 0;
    public final static int BLUE = 1;
    public final static int DARK_BLUE = 2;
    public final static int LIGHT_BLUE = 3;
    public final static int DARK_GREEN = 4;
    public final static int LIGHT_GREEN = 5;
    public final static int YELLOW = 6;
    public final static int PINK = 7;
    public final static int GREY = 8;
    public final static int RED = 9;
    public final static int PURPLE = 10;
    public final static int ORANGE = 11;
    public final static int BROWN = 12;
    public final static int DEFAULT = 13;

    // endregion

    // region: methods

    /**
     * @param activity Activity
     * @param theme    int
     */
    public static void changeToTheme(Activity activity, int theme) {
        cTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }

    /**
     * @param activity Activity
     */
    public static void onActivityCreateSetTheme(Activity activity) {
        switch (cTheme) {
            case BLACK:
                activity.setTheme(R.style.BlackTheme);
                break;
            case DARK_BLUE:
                activity.setTheme(R.style.DarkBlueTheme);
                break;
            case LIGHT_BLUE:
                activity.setTheme(R.style.LightBlueTheme);
                break;
            case DARK_GREEN:
                activity.setTheme(R.style.DarkGreenTheme);
                break;
            case LIGHT_GREEN:
                activity.setTheme(R.style.LightGreenTheme);
                break;
            case YELLOW:
                activity.setTheme(R.style.YellowTheme);
                break;
            case PINK:
                activity.setTheme(R.style.PinkTheme);
                break;
            case GREY:
                activity.setTheme(R.style.GreyTheme);
                break;
            case RED:
                activity.setTheme(R.style.RedTheme);
                break;
            case PURPLE:
                activity.setTheme(R.style.PurpleTheme);
                break;
            case ORANGE:
                activity.setTheme(R.style.OrangeTme);
                break;
            case BROWN:
                activity.setTheme(R.style.BrownTheme);
                break;
            default:
            case DEFAULT:
                activity.setTheme(R.style.DefaultTheme);
                break;
        }
    }

    // endregion

}
