package com.example.fitnespro.main;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.fitnespro.R;
import com.example.fitnespro.bmi.BMIActivity;
import com.example.fitnespro.exercise.ExerciseActivity;
import com.example.fitnespro.image.ImageFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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
        } else if (id == R.id.bmi) {
            Intent intent = new Intent(this, BMIActivity.class);
            startActivity(intent);
        } else if (id == R.id.about) {
            fragment = new AboutFragment();
        } else if (id == R.id.getImage) {
            fragment = new ImageFragment();
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

    public void openChestTricepsExerciseTraining(View view) {
        Intent intent = new Intent(this, ExerciseActivity.class);
        intent.putExtra("type", "ChestTriceps");
        startActivity(intent);
    }
}
