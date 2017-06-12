package com.example.user.mychatapp.mvvm.di;

import com.example.user.mychatapp.mvvm.view.activitys.LoginActivity;
import com.example.user.mychatapp.mvvm.view.activitys.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by User on 8/11/2016.
 */

@Singleton
@Component(modules = { AppModule.class, ViewModelModule.class })
public interface AppComponent {

    void inject(MainActivity activity);
    void inject(LoginActivity activity);


}