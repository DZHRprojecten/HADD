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
            default:

            case BLACK:

                activity.setTheme(R.style.BlackTheme);

                break;

            case BLUE:

                activity.setTheme(R.style.DarkBlueTheme);

                break;
        }
    }

    // endregion

}
