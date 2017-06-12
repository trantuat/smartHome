package com.example.user.mychatapp.core.base;

import android.databinding.BaseObservable;

import com.example.user.mychatapp.core.navigator.INavigator;

/**
 * Created by User on 8/11/2016.
 */

public abstract class BaseViewModel extends BaseObservable implements IViewModelLifecycle{
    //region Properties

    protected INavigator navigator;

    //endregion

    //region Constructors

    public BaseViewModel(INavigator navigator) {
        this.navigator = navigator;
    }

    //endregion

    //region Implement IViewModelLifecycle

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    //endregion

}
