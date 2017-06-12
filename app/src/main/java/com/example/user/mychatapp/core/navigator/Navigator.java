package com.example.user.mychatapp.core.navigator;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.user.mychatapp.core.base.BaseApplication;

/**
 * Created by User on 8/11/2016.
 */

public class Navigator implements INavigator {
    //region Properties

    private BaseApplication mApplication;

    //endregion

    //region Constructors

    /**
     * Constructor.
     * @param application
     */
    public Navigator(BaseApplication application) {
        mApplication = application;
    }

    //endregion

    //region Implement INavigator

    /**
     * Go back activity.
     */
    @Override
    public void goBack() {
        goBack(false);
    }

    @Override
    public void goBack(boolean fromStack) {
        if (mApplication.isCurrentActivityAvailable()) {
            AppCompatActivity currentActivity = mApplication.getCurrentActivity();

            if (fromStack) {
                currentActivity.getSupportFragmentManager()
                        .popBackStack();
            } else {
                ActivityCompat.finishAfterTransition(currentActivity);
            }
        }
    }

    /**
     * Navigate to activity.
     * @param activity
     */
    @Override
    public void navigateTo(Class activity) {
        if (mApplication.isCurrentActivityAvailable()) {
            AppCompatActivity currentActivity = mApplication.getCurrentActivity();

            Intent intent = new Intent(currentActivity, activity);
            ActivityCompat.startActivity(currentActivity, intent, null);
        }
    }

    /**
     * Navigate to activity.
     * @param activity
     * @param optionsCompat
     */
    @Override
    public void navigateTo(Class activity, ActivityOptionsCompat optionsCompat) {
        if (mApplication.isCurrentActivityAvailable()) {
            AppCompatActivity currentActivity = mApplication.getCurrentActivity();

            Intent intent = new Intent(currentActivity, activity);
            ActivityCompat.startActivity(currentActivity, intent, optionsCompat.toBundle());
        }
    }

    /**
     * Navigate to fragment.
     * @param fragment
     * @param resId
     * @param addStack
     */
    @Override
    public void navigateTo(Fragment fragment, int resId, boolean addStack) {
        if (mApplication.isCurrentActivityAvailable()) {
            AppCompatActivity currentActivity = mApplication.getCurrentActivity();

            if (addStack) {
                currentActivity.getSupportFragmentManager()
                        .beginTransaction()
                        .add(resId, fragment)
                        .commit();
            } else {
                currentActivity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(resId, fragment)
                        .commit();
            }
        }
    }

    /**
     * Get current application.
     * @return
     */
    @Override
    public BaseApplication getApplication() {
        return mApplication;
    }


    //endregion
}
