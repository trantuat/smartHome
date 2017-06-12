package com.example.user.mychatapp.core.navigator;

import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;

import com.example.user.mychatapp.core.base.BaseApplication;

/**
 * Created by User on 8/11/2016.
 */

public interface INavigator {
    void goBack();

    void goBack(boolean fromStack);

    void navigateTo(Class activity);

    void navigateTo(Class activity, ActivityOptionsCompat optionsCompat);

    void navigateTo(Fragment fragment, int resId, boolean addStack);

    BaseApplication getApplication();
}
