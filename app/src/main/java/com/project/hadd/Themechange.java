package com.project.hadd;

import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
        Button blue = view.findViewById(R.id.bdonkerblauw);
        view.findViewById(R.id.blichtblauw);
        view.findViewById(R.id.bdonkergroen);
        view.findViewById(R.id.blichtgroen);
        view.findViewById(R.id.bgeel);
        view.findViewById(R.id.broze);
        view.findViewById(R.id.bzwart);
        view.findViewById(R.id.bgrijs);
        view.findViewById(R.id.brood);
        view.findViewById(R.id.bpaars);
        view.findViewById(R.id.boranje);
        view.findViewById(R.id.bbruin);
        view.findViewById(R.id.bbasisthema);

        blue.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                ThemeUtils.changeToTheme(Objects.requireNonNull(getActivity()), ThemeUtils.BLUE);
            }
        });
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        Log.d("ThemeChange", "----------------------------- Them has been changed" + Objects.requireNonNull(getActivity()));
        switch (v.getId()) {
            case R.id.bzwart:
                ThemeUtils.changeToTheme(Objects.requireNonNull(getActivity()), ThemeUtils.BLACK);
                break;
            case R.id.bdonkerblauw:
                ThemeUtils.changeToTheme(Objects.requireNonNull(getActivity()), ThemeUtils.BLUE);
                break;
        }
    }
}
