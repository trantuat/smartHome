package com.example.user.mychatapp.core.base;

/**
 * Created by User on 8/11/2016.
 */

public interface IViewModelLifecycle {
    void onCreate();

    void onStart();

    void onStop();

    void onDestroy();

}
