package com.example.fitnespro;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleObserver;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, LifecycleObserver {

    long appLifeTime = 0L;
    MyLocationListener myLocationListener;
    private static final String key = "key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            Fragment newFragment = new HomeFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.content_frame, newFragment);
            ft.addToBackStack(null);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        appLifeTime = System.currentTimeMillis();

        myLocationListener = new MyLocationListener(this.getLifecycle());
        Timber.e("onCreate method called");
        
        if(Objects.nonNull(savedInstanceState)){
            String savedValue = (String) Objects.requireNonNull(savedInstanceState).get(key);
            Timber.e("savedValue : %s", savedValue);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        Bundle bundle = new Bundle();

        if (id == R.id.home) {
            fragment = new HomeFragment();
        } else if (id == R.id.about) {
            fragment = new AboutFragment();
        } else if (id == R.id.termsOfUse) {
            fragment = new TermsOfUseFragment();
        } else if (id == R.id.github) {
            bundle.putString("url", "https://github.com/SteVich");
            fragment = new WebViewFragment();
            fragment.setArguments(bundle);
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public void openLinkedIn(View view) {
        Fragment fragment;
        Bundle bundle = new Bundle();

        bundle.putString("url", "https://www.linkedin.com/in/vitaliy-stefanchak-991002150/");
        fragment = new WebViewFragment();
        fragment.setArguments(bundle);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
    }

    public void openStrengthTrainingFragment(View view) {
        Fragment fragment;
        fragment = new StrengthTrainingFragment();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Timber.e("onSave method called");
        
        outState.putString(key, "value");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Timber.e("onStart method called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.e("onResume method called");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Timber.e("onPause method called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Timber.e("onStop method called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.e("onDestroy method called");

        appLifeTime = (System.currentTimeMillis() - appLifeTime) / 1000;

        double time = (double) myLocationListener.secondsCount / (double) appLifeTime;

        Timber.i("%s/%s. Z=%s", myLocationListener.secondsCount, appLifeTime, time*100);
        Timber.i("Application life time in sec: %s", appLifeTime);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Timber.e("onRestart method called");
    }
}
