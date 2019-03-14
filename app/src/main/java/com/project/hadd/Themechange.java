package com.project.hadd;

import android.arch.persistence.room.Room;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.hadd.database.ThemeDatabase;
import com.project.hadd.database.model.Theme;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static android.content.ContentValues.TAG;

/**
 * Settings page that changes the application design
 */
public class Themechange extends Fragment implements View.OnClickListener {

    // region: fields

    private static final String DATABASE_NAME = "themes_db";
    private ThemeDatabase themeDatabase;

    // endregion

    /**
     * @param inflater           LayoutInflater
     * @param container          ViewGroup
     * @param savedInstanceState Bundle
     * @return View
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_themechange, container, false);

        view.findViewById(R.id.darkBlue).setOnClickListener(this);
        view.findViewById(R.id.lightBlue).setOnClickListener(this);
        view.findViewById(R.id.darkGreen).setOnClickListener(this);
        view.findViewById(R.id.lightGreen).setOnClickListener(this);
        view.findViewById(R.id.yellow).setOnClickListener(this);
        view.findViewById(R.id.pink).setOnClickListener(this);
        view.findViewById(R.id.black).setOnClickListener(this);
        view.findViewById(R.id.grey).setOnClickListener(this);
        view.findViewById(R.id.red).setOnClickListener(this);
        view.findViewById(R.id.purple).setOnClickListener(this);
        view.findViewById(R.id.orange).setOnClickListener(this);
        view.findViewById(R.id.brown).setOnClickListener(this);
        view.findViewById(R.id.defaultTheme).setOnClickListener(this);

        // region: database

        // TODO:: REMOVE FOR PRODUCTION RELEASE
        themeDatabase = Room.databaseBuilder(Objects.requireNonNull(getActivity()).getApplicationContext(), ThemeDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Theme theme = new Theme();
                theme.setThemeId("1");
                theme.setThemeName(R.id.brown);
                themeDatabase.daoTheme().insertOnlySingleTheme(theme);
            }
        }).start();

        // endregion

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(final View v) {
        Log.d("TAG", "onClick called on themeChange");


        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<Theme> currentThemeCallable = new Callable<Theme>() {
            @Override
            public Theme call() {
                return themeDatabase.daoTheme().fetchOneThemesbyThemeName(v.getId());
            }
        };
        Future<Theme> currentTheme = executor.submit(currentThemeCallable);

        try {
            Log.d(TAG, "onClick: " + currentTheme.get());
            if (currentTheme.get() != null) {
                Log.d(TAG, "onClick: " + v.getId());
                v.setId(currentTheme.get().getThemeName());
                Log.d(TAG, "onClick: " + v.getId());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();

        Log.d(TAG, "onClick: ---- " + v.getId());
        switch (v.getId()) {
            case R.id.darkBlue:
                ThemeUtils.changeToTheme(Objects.requireNonNull(getActivity()), ThemeUtils.DARK_BLUE);
                break;
            case R.id.lightBlue:
                ThemeUtils.changeToTheme(Objects.requireNonNull(getActivity()), ThemeUtils.LIGHT_BLUE);
                break;
            case R.id.darkGreen:
                ThemeUtils.changeToTheme(Objects.requireNonNull(getActivity()), ThemeUtils.DARK_GREEN);
                break;
            case R.id.lightGreen:
                ThemeUtils.changeToTheme(Objects.requireNonNull(getActivity()), ThemeUtils.LIGHT_GREEN);
                break;
            case R.id.yellow:
                ThemeUtils.changeToTheme(Objects.requireNonNull(getActivity()), ThemeUtils.YELLOW);
                break;
            case R.id.pink:
                ThemeUtils.changeToTheme(Objects.requireNonNull(getActivity()), ThemeUtils.PINK);
                break;
            case R.id.black:
                ThemeUtils.changeToTheme(Objects.requireNonNull(getActivity()), ThemeUtils.BLACK);
                break;
            case R.id.grey:
                ThemeUtils.changeToTheme(Objects.requireNonNull(getActivity()), ThemeUtils.GREY);
                break;
            case R.id.red:
                ThemeUtils.changeToTheme(Objects.requireNonNull(getActivity()), ThemeUtils.RED);
                break;
            case R.id.purple:
                ThemeUtils.changeToTheme(Objects.requireNonNull(getActivity()), ThemeUtils.PURPLE);
                break;
            case R.id.orange:
                ThemeUtils.changeToTheme(Objects.requireNonNull(getActivity()), ThemeUtils.ORANGE);
                break;
            case R.id.brown:
                ThemeUtils.changeToTheme(Objects.requireNonNull(getActivity()), ThemeUtils.BROWN);
                break;
            default:
            case R.id.defaultTheme:
                ThemeUtils.changeToTheme(Objects.requireNonNull(getActivity()), ThemeUtils.DEFAULT);
                break;
        }
    }
}
