package com.example.fitnespro;

import android.os.Handler;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import timber.log.Timber;

public class MyLocationListener implements LifecycleObserver {

    public int secondsCount = 0;
    final Handler handler = new Handler();
    private Runnable runnable;

    public MyLocationListener(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void appInResumeState() {
        runnable = () -> {
            secondsCount++;
            Timber.i("Timer is at : %s", secondsCount);
            handler.postDelayed(runnable, 1000);
        };

        handler.postDelayed(runnable, 1000);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void appInPauseState() {
        handler.removeCallbacks(runnable);
    }
}

