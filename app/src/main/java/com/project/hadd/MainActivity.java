package com.project.hadd;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

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
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
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
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
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

   /* @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        ThemeUtils.onActivityCreateSetTheme(this);

        setContentView(R.layout.activity_main);

        findViewById(R.id.bzwart).setOnClickListener(this);

        findViewById(R.id.bdonkerblauw).setOnClickListener(this);
    }

    @Override

    public void onClick(View v)

    {

        switch (v.getId())

        {

            case R.id.bzwart:

                ThemeUtils.changeToTheme(this, ThemeUtils.BLACK);

                break;

            case R.id.bdonkerblauw:

                ThemeUtils.changeToTheme(this, ThemeUtils.BLUE);

                break;

        }

    }*/

}

