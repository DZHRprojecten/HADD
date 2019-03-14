package com.project.hadd;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

/**
 * Settings page that changes the application design
 */
public class Themechange extends Fragment implements View.OnClickListener {

    /**
     * @param inflater           LayoutInflater
     * @param container          ViewGroup
     * @param savedInstanceState Bundle
     * @return View
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_themechange, container, false);

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

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
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
