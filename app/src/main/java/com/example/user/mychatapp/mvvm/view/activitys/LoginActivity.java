package com.example.user.mychatapp.mvvm.view.activitys;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.mychatapp.R;
import com.example.user.mychatapp.config.Config;
import com.example.user.mychatapp.core.base.BaseActivity;
import com.example.user.mychatapp.databinding.ActivityLoginBinding;
import com.example.user.mychatapp.mvvm.App;
import com.example.user.mychatapp.mvvm.Model.Control;
import com.example.user.mychatapp.mvvm.Service.RestApi;
import com.example.user.mychatapp.mvvm.viewmodel.LoginViewModel;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginActivity extends BaseActivity<ActivityLoginBinding,LoginViewModel> {
    private boolean isBack=false;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getComponent().inject(this);
        super.onCreate(savedInstanceState);
        setBindingContentView(R.layout.activity_login);
        actionBar =getSupportActionBar();
        actionBar.hide();
    }
    private void disconnect(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestApi api=retrofit.create(RestApi.class);
        Call<Control> call=api.postUseNet("0");
        call.enqueue(new Callback<Control>() {
            @Override
            public void onResponse(Response<Control> response, Retrofit retrofit) {
                Toast.makeText(getBaseContext(),"Disconnected",Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getBaseContext(),"Fail disconnected",Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void showAlert(AppCompatActivity context){
        Button btnOK;
        Button btnCancel;
        TextView tvTitle;
        TextView tvMessage;
        isBack=false;
        AlertDialog.Builder dialog=new AlertDialog.Builder(context);
        View dialogview = context.getLayoutInflater().inflate(R.layout.alert_custom,null);
        dialog.setView(dialogview);
        btnCancel=(Button) dialogview.findViewById(R.id.btn_cancel_alert);
        btnOK=(Button) dialogview.findViewById(R.id.btn_ok_alert);
        tvMessage=(TextView) dialogview.findViewById(R.id.message_alert);
        tvTitle=(TextView) dialogview.findViewById(R.id.title_alert);
        tvMessage.setText("Do you want to quit");
        tvTitle.setText("Alert");
        dialog.setCancelable(false);
        final AlertDialog alertDialog=dialog.create();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isBack=false;
                alertDialog.dismiss();
            }
        });
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                disconnect();
                isBack=true;
            }
        });

        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        showAlert(this);
        if (isBack)
            super.onBackPressed();
    }
}
