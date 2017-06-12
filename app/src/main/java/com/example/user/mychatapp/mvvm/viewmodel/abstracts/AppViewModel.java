package com.example.user.mychatapp.mvvm.viewmodel.abstracts;

import com.example.user.mychatapp.core.base.BaseViewModel;
import com.example.user.mychatapp.core.navigator.INavigator;

/**
 * Created by User on 8/11/2016.
 */

public abstract class AppViewModel extends BaseViewModel {
//region Constructors

    public AppViewModel(INavigator navigator) {
        super(navigator);
    }

    //endregion

    //region Actions

    public void goBack() {
        navigator.goBack();
    }

    //endregion
}
