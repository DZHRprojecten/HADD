package com.project.hadd;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

public class SettingsFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_message, container, false);
        final Button button = view.findViewById(R.id.Opslaan);
        final SeekBar seekBar = view.findViewById(R.id.Vibrationseekbar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "onClick: Enable/disable bluetooth");

                    }
                });

                // seekBar.value

                // log

                // maak een json string aan

                // log

                // send to rasberry pi

                // port 1
                // mac address: ...
                //
            }
        });        return view;
    }
}
