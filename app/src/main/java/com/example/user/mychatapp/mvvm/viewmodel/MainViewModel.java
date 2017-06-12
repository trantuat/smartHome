package com.example.user.mychatapp.mvvm.viewmodel;

import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.v7.widget.SwitchCompat;
import android.widget.Toast;

import com.example.user.mychatapp.core.navigator.INavigator;
import com.example.user.mychatapp.mvvm.viewmodel.abstracts.AppViewModel;

/**
 * Created by User on 8/11/2016.
 */

public class MainViewModel extends AppViewModel{

    public MainViewModel(INavigator navigator) {

        super(navigator);
    }


}
