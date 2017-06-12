package com.example.user.mychatapp.mvvm.di;

import com.example.user.mychatapp.core.base.BaseApplication;
import com.example.user.mychatapp.core.navigator.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by User on 8/11/2016.
 */
@Module
public class AppModule {
    //region Properties

    private Navigator mNavigator;

    //endregion

    //region Constructors

    public AppModule(BaseApplication application) {
        mNavigator = new Navigator(application);
    }

    //endregion

    //region Provides

    @Provides
    @Singleton
    BaseApplication providesApplication() {
        return mNavigator.getApplication();
    }

    @Provides
    @Singleton
    Navigator providesNavigator() {
        return mNavigator;
    }

    //endregion
}
