package com.project.hadd;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.project.hadd.database.ThemeDatabase;
import com.project.hadd.database.model.Theme;
import com.project.hadd.database.repository.ThemeRepository;

/**
 * MainActivity class handles the main navigation trough the application
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // region: fields

    private DrawerLayout drawer;

    // endregion

    // region: methods


    /**
     * initialization of fragment
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ThemeDatabase themeDatabase = ThemeDatabase.getInstance(MainActivity.this.getApplication());
        ThemeRepository themeRepository = new ThemeRepository(ThemeDatabase.getInstance(MainActivity.this.getApplicationContext()));

        LiveData<Theme> theme = themeRepository.getThemeById(1);
        theme.observe(MainActivity.this, new Observer<Theme>() {
            @Override
            public void onChanged(@Nullable Theme theme) {
                if (theme != null) {
                    Log.d("SUPERDUPER", "onChanged: " + theme.getThemeName());
                    ThemeUtils.setcTheme(theme.getThemeName());
                }
            }
        });

        ThemeUtils.onActivityCreateSetTheme(MainActivity.this);

        TypedArray typedArray = this.getTheme().obtainStyledAttributes(R.styleable.ViewStyle);
        int themeColor = typedArray.getColor(R.styleable.ViewStyle_theme_color, 1);
        int themeTextColor = typedArray.getColor(R.styleable.ViewStyle_theme_text_color, 2);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(themeColor);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.getHeaderView(0).findViewById(R.id.nav_background).setBackgroundColor(themeColor);

        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

    }

    /**
     * This method handles the navigation between fragments
     *
     * @param item MenuItem
     * @return bool
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                break;
            case R.id.nav_theme:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Themechange()).commit();
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutFragment()).commit();
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * This method presents the back button from closing the app.
     */
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // endregion

}

