package com.dzone.xposed.demo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class AbstractLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    @Override
    public void onActivityPreCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityPostCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityPreStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPostStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPreResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPostResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPrePaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPostPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPreStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPostStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPreSaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityPostSaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityPreDestroyed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPostDestroyed(@NonNull Activity activity) {

    }
}
