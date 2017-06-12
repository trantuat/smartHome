package com.example.user.mychatapp.core.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by User on 8/11/2016.
 */

public class BaseApplication extends Application implements Application.ActivityLifecycleCallbacks{
    //region Properties

    private AppCompatActivity mCurrentActivity;

    //endregion

    //region Lifecycle

    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(this);
    }

    //endregion

    //region ActivityLifecycleCallbacks

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        mCurrentActivity = (AppCompatActivity) activity;
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    //endregion

    //region Public Methods

    public AppCompatActivity getCurrentActivity() {
        return mCurrentActivity;
    }

    public boolean isCurrentActivityAvailable() {
        return mCurrentActivity != null;
    }

    //endregion

}
