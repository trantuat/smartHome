package com.example.user.mychatapp.mvvm.viewmodel;

import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.Bindable;
import android.widget.Toast;

import com.example.user.mychatapp.config.Config;
import com.example.user.mychatapp.core.navigator.INavigator;
import com.example.user.mychatapp.mvvm.Model.Control;
import com.example.user.mychatapp.mvvm.Service.RestApi;
import com.example.user.mychatapp.mvvm.view.activitys.MainActivity;
import com.example.user.mychatapp.mvvm.viewmodel.abstracts.AppViewModel;


import org.greenrobot.eventbus.EventBus;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * Created by User on 8/12/2016.
 */

public class LoginViewModel extends AppViewModel{
    private Retrofit retrofit = new Retrofit.Builder().baseUrl(Config.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public LoginViewModel(INavigator navigator) {
        super(navigator);
    }

    public void connect() {
        final ProgressDialog progressDialog=new ProgressDialog(navigator.getApplication().getCurrentActivity());
        progressDialog.setMessage("Connecting....");
        progressDialog.show();
        RestApi api=retrofit.create(RestApi.class);
        Call<Control> call=api.postUseNet("1");
        call.enqueue(new Callback<Control>() {
            @Override
            public void onResponse(Response<Control> response, Retrofit retrofit) {
                Control event=response.body();
                EventBus.getDefault().postSticky(event);
                progressDialog.dismiss();
                Toast.makeText(navigator.getApplication().getBaseContext(),"Connected",Toast.LENGTH_SHORT).show();
                navigator.navigateTo(MainActivity.class);
            }

            @Override
            public void onFailure(Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(navigator.getApplication().getBaseContext(),"No network",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void disconnect() {
        final ProgressDialog progressDialog=new ProgressDialog(navigator.getApplication().getCurrentActivity());
        progressDialog.setMessage("Disconnecting....");
        progressDialog.show();
        RestApi api=retrofit.create(RestApi.class);
        Call<Control> call=api.postUseNet("0");
        call.enqueue(new Callback<Control>() {
            @Override
            public void onResponse(Response<Control> response, Retrofit retrofit) {
                progressDialog.dismiss();
                Toast.makeText(navigator.getApplication().getBaseContext(),"Disconnected",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(navigator.getApplication().getBaseContext(),"No network",Toast.LENGTH_SHORT).show();
            }
        });

    }

}
