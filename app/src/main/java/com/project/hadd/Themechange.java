package com.project.hadd;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
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
import com.project.hadd.database.repository.ThemeRepository;

import java.util.Objects;

/**
 * SettingsFragment page that changes the application design
 */
public class Themechange extends Fragment implements View.OnClickListener {

    // region: fields

    private static final String DATABASE_NAME = "themes_db";
    private ThemeDatabase themeDatabase;
    private int colorBtnId;

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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
        themeDatabase = ThemeDatabase.getInstance(Objects.requireNonNull(getActivity()).getApplication());

        ThemeRepository themeRepository = new ThemeRepository(ThemeDatabase.getInstance(Objects.requireNonNull(getActivity()).getApplicationContext()));

        LiveData<Theme> theme = themeRepository.getThemeById(1);
        theme.observe(getActivity(), new Observer<Theme>() {
            @Override
            public void onChanged(@Nullable Theme theme) {
                colorBtnId = theme != null ? theme.getThemeName() : 0;
            }
        });

        // endregion

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(final View v) {
        Log.d("TAG", "onClick called on themeChange");

        ThemeRepository themeRepository = new ThemeRepository(ThemeDatabase.getInstance(Objects.requireNonNull(getActivity()).getApplicationContext()));

        if (colorBtnId != 0) {
            colorBtnId = v.getId();
        }

        switch (colorBtnId) {
            case R.id.darkBlue:
                themeRepository.insertOrReplaceTheme(new Theme(1, ThemeUtils.DARK_BLUE));
                ThemeUtils.changeToTheme(Objects.requireNonNull(getActivity()), ThemeUtils.DARK_BLUE);
                break;
            case R.id.lightBlue:
                themeRepository.insertOrReplaceTheme(new Theme(1, ThemeUtils.LIGHT_BLUE));
                ThemeUtils.changeToTheme(Objects.requireNonNull(getActivity()), ThemeUtils.LIGHT_BLUE);
                break;
            case R.id.darkGreen:
                themeRepository.insertOrReplaceTheme(new Theme(1, ThemeUtils.DARK_GREEN));
                ThemeUtils.changeToTheme(Objects.requireNonNull(getActivity()), ThemeUtils.DARK_GREEN);
                break;
            case R.id.lightGreen:
                themeRepository.insertOrReplaceTheme(new Theme(1, ThemeUtils.LIGHT_GREEN));
                ThemeUtils.changeToTheme(Objects.requireNonNull(getActivity()), ThemeUtils.LIGHT_GREEN);
                break;
            case R.id.yellow:
                themeRepository.insertOrReplaceTheme(new Theme(1, ThemeUtils.YELLOW));
                ThemeUtils.changeToTheme(Objects.requireNonNull(getActivity()), ThemeUtils.YELLOW);
                break;
            case R.id.pink:
                themeRepository.insertOrReplaceTheme(new Theme(1, ThemeUtils.PINK));
                ThemeUtils.changeToTheme(Objects.requireNonNull(getActivity()), ThemeUtils.PINK);
                break;
            case R.id.black:
                themeRepository.insertOrReplaceTheme(new Theme(1, ThemeUtils.BLACK));
                ThemeUtils.changeToTheme(Objects.requireNonNull(getActivity()), ThemeUtils.BLACK);
                break;
            case R.id.grey:
                themeRepository.insertOrReplaceTheme(new Theme(1, ThemeUtils.GREY));
                ThemeUtils.changeToTheme(Objects.requireNonNull(getActivity()), ThemeUtils.GREY);
                break;
            case R.id.red:
                themeRepository.insertOrReplaceTheme(new Theme(1, ThemeUtils.RED));
                ThemeUtils.changeToTheme(Objects.requireNonNull(getActivity()), ThemeUtils.RED);
                break;
            case R.id.purple:
                themeRepository.insertOrReplaceTheme(new Theme(1, ThemeUtils.PURPLE));
                ThemeUtils.changeToTheme(Objects.requireNonNull(getActivity()), ThemeUtils.PURPLE);
                break;
            case R.id.orange:
                themeRepository.insertOrReplaceTheme(new Theme(1, ThemeUtils.ORANGE));
                ThemeUtils.changeToTheme(Objects.requireNonNull(getActivity()), ThemeUtils.ORANGE);
                break;
            case R.id.brown:
                themeRepository.insertOrReplaceTheme(new Theme(1, ThemeUtils.BROWN));
                ThemeUtils.changeToTheme(Objects.requireNonNull(getActivity()), ThemeUtils.BROWN);
                break;
            default:
            case R.id.defaultTheme:
                themeRepository.insertOrReplaceTheme(new Theme(1, ThemeUtils.DEFAULT));
                ThemeUtils.changeToTheme(Objects.requireNonNull(getActivity()), ThemeUtils.DEFAULT);
                break;
        }
    }
}
