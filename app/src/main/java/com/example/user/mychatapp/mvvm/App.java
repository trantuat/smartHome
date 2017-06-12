package com.example.user.mychatapp.mvvm;

import com.example.user.mychatapp.core.base.BaseApplication;
import com.example.user.mychatapp.mvvm.di.AppComponent;
import com.example.user.mychatapp.mvvm.di.AppModule;
import com.example.user.mychatapp.mvvm.di.DaggerAppComponent;
import com.example.user.mychatapp.mvvm.di.ViewModelModule;


/**
 * Created by User on 8/11/2016.
 */

public class App extends BaseApplication {
    private static AppComponent mComponent;


    @Override
    public void onCreate() {
        super.onCreate();
      //  Firebase.setAndroidContext(getBaseContext());
        mComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).viewModelModule(new ViewModelModule()).build();

    }
    public static AppComponent getComponent(){
        return mComponent;
    }
}
