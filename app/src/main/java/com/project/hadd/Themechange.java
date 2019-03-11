package com.project.hadd;

import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Settings page that changes the application design
 */
public class Themechange extends Fragment {

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
        view.findViewById(R.id.bdonkerblauw);
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
        return view;

    }
}
