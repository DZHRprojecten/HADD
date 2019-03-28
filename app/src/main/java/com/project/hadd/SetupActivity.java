package com.project.hadd;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.project.hadd.database.ThemeDatabase;
import com.project.hadd.database.model.Theme;
import com.project.hadd.database.repository.ThemeRepository;

import java.util.Objects;

public class SetupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeRepository themeRepository = new ThemeRepository(ThemeDatabase.getInstance(this.getApplicationContext()));

        LiveData<Theme> theme = themeRepository.getThemeById(1);
        theme.observe(this, new Observer<Theme>() {
            @Override
            public void onChanged(@Nullable Theme theme) {
                if (theme != null) {
                    Log.d("SETUP", "onChanged: Theme loaded: " + theme.getThemeName());
                    ThemeUtils.setcTheme(theme.getThemeName());
                    Intent intent = new Intent(SetupActivity.this, MainActivity.class);
                    startActivity(intent);
//                    ThemeUtils.changeToTheme(new MainActivity());
                }
            }
        });
        setContentView(R.layout.activity_setup);
    }
}
