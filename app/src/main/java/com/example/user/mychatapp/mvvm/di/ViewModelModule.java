package com.example.user.mychatapp.mvvm.di;

import com.example.user.mychatapp.core.navigator.Navigator;
import com.example.user.mychatapp.mvvm.viewmodel.LoginViewModel;
import com.example.user.mychatapp.mvvm.viewmodel.MainViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by User on 8/11/2016.
 */

@Module(includes = { AppModule.class })
public class ViewModelModule {

    //region Constructors

    public ViewModelModule() {

    }

    //endregion

    //region Provides

    @Provides
    @Singleton
    public MainViewModel providesMainViewModel(Navigator navigator) {
        return new MainViewModel(navigator);
    }

    @Provides
    @Singleton
    public LoginViewModel providesLoginViewModel(Navigator navigator) {
        return new LoginViewModel(navigator);
    }


    //endregion

}
