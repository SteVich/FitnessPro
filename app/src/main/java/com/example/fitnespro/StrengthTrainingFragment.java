package com.example.fitnespro;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import timber.log.Timber;

public class StrengthTrainingFragment extends Fragment {
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Timber.e("onCreate method called");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_strength_training, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        Timber.e("onStart method called");
    }

    @Override
    public void onResume() {
        super.onResume();

        Timber.e("onResume method called");
    }

    @Override
    public void onPause() {
        super.onPause();

        Timber.e("onPause method called");
    }

    @Override
    public void onStop() {
        super.onStop();

        Timber.e("onResume method called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Timber.e("onDestroy method called");
    }
}