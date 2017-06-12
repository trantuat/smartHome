package com.example.user.mychatapp.mvvm.view.activitys;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SwitchCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.mychatapp.R;
import com.example.user.mychatapp.config.Config;
import com.example.user.mychatapp.core.base.BaseActivity;
import com.example.user.mychatapp.databinding.ActivityMainBinding;
import com.example.user.mychatapp.mvvm.App;
import com.example.user.mychatapp.mvvm.Model.Control;
import com.example.user.mychatapp.mvvm.Service.RestApi;
import com.example.user.mychatapp.mvvm.viewmodel.MainViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * Created by User on 8/12/2016.
 */

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements CompoundButton.OnCheckedChangeListener, View.OnTouchListener {

    private SwitchCompat mSw_living_room;
    private SwitchCompat mSw_bed_room;
    private SwitchCompat mSw_garage_room;
    private SwitchCompat mSw_dinner_room;
    private SwitchCompat mSw_wc_room;
    private SwitchCompat mSw_garage_door;
    private SwitchCompat mSw_main_door;
    private SwitchCompat mSw_side_door;

    private Retrofit retrofit;
    private RestApi api;
    private ProgressDialog progress;
    private Control mControl = new Control();
    private boolean isTouch = false;

    private boolean mConnected;
    private TextView mTvStatus;

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getComponent().inject(this);
        super.onCreate(savedInstanceState);
        setBindingContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
        init();
    }

    void init() {
        actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mTvStatus = (TextView) findViewById(R.id.tittle);
        mSw_dinner_room = (SwitchCompat) findViewById(R.id.sw_dinner_room);
        mSw_bed_room = (SwitchCompat) findViewById(R.id.sw_bed_room);
        mSw_living_room = (SwitchCompat) findViewById(R.id.sw_living_room);
        mSw_garage_room = (SwitchCompat) findViewById(R.id.sw_garage_room);
        mSw_wc_room = (SwitchCompat) findViewById(R.id.sw_wc);
        mSw_garage_door = (SwitchCompat) findViewById(R.id.sw_garage_door);
        mSw_main_door = (SwitchCompat) findViewById(R.id.sw_main_door);
        mSw_side_door = (SwitchCompat) findViewById(R.id.sw_side_door);

        mSw_dinner_room.setOnCheckedChangeListener(this);
        mSw_living_room.setOnCheckedChangeListener(this);
        mSw_bed_room.setOnCheckedChangeListener(this);
        mSw_garage_room.setOnCheckedChangeListener(this);
        mSw_wc_room.setOnCheckedChangeListener(this);
        mSw_garage_door.setOnCheckedChangeListener(this);
        mSw_main_door.setOnCheckedChangeListener(this);
        mSw_side_door.setOnCheckedChangeListener(this);

        mSw_living_room.setOnTouchListener(this);
        mSw_bed_room.setOnTouchListener(this);
        mSw_garage_room.setOnTouchListener(this);
        mSw_wc_room.setOnTouchListener(this);
        mSw_dinner_room.setOnTouchListener(this);
        mSw_side_door.setOnTouchListener(this);
        mSw_garage_door.setOnTouchListener(this);
        mSw_main_door.setOnTouchListener(this);

        retrofit = new Retrofit.Builder().baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(RestApi.class);


    }

    private void updateStatus() {
        int status[] = mControl.getSatus();
        setCheck(mSw_living_room, status[0]);
        setCheck(mSw_bed_room, status[1]);
        setCheck(mSw_dinner_room, status[2]);
        setCheck(mSw_garage_room, status[3]);
        setCheck(mSw_wc_room, status[4]);
        setCheck(mSw_garage_door, status[5]);
        setCheck(mSw_main_door, status[6]);
        setCheck(mSw_side_door, status[7]);

    }

    private void setCheck(SwitchCompat switchCompat, int i) {
        if (i == 1) switchCompat.setChecked(true);
        else switchCompat.setChecked(false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    private void sendData(Call<Control> call) {
        progress = new ProgressDialog(this);
        progress.setMessage("Sending...");
        progress.setCancelable(false);
        progress.show();


        call.enqueue(new Callback<Control>() {
            @Override
            public void onResponse(Response<Control> response, Retrofit retrofit) {
                mControl = response.body();
                if (response.body().getConnect() == 1){
                    mTvStatus.setText("Connected");
                    mConnected=true;
                }else{
                    mConnected=false;
                    mTvStatus.setText("Disconnected");
                }
                Toast.makeText(getBaseContext(), "Success", Toast.LENGTH_SHORT).show();
                progress.dismiss();
                updateStatus();

            }

            @Override
            public void onFailure(Throwable t) {
                mTvStatus.setText("Disconnected");
                progress.dismiss();
                Toast.makeText(getBaseContext(), "Fail", Toast.LENGTH_SHORT).show();
                updateStatus();
            }
        });


    }

    public void controlLightLivingRoom() {
        int status = mControl.getSatus()[0];
        if (status == 0) status = 1;
        else status = 0;
        sendData(api.turnLivingRoom(String.valueOf(status)));
    }

    public void controlLightBedRoom() {
        int status = mControl.getSatus()[1];
        if (status == 0) status = 1;
        else status = 0;
        sendData(api.turnBedRoom(String.valueOf(status)));

    }

    public void controlLightDinnerRoom() {
        int status = mControl.getSatus()[2];
        if (status == 0) status = 1;
        else status = 0;
        sendData(api.turnDinnerRoom(String.valueOf(status)));

    }

    public void controlLightGarageRoom() {
        int status = mControl.getSatus()[3];
        if (status == 0) status = 1;
        else status = 0;
        sendData(api.turnGarageRoom(String.valueOf(status)));

    }

    public void controlLightWCRoom() {
        int status = mControl.getSatus()[4];
        if (status == 0) status = 1;
        else status = 0;
        sendData(api.turnToiletRoom(String.valueOf(status)));

    }

    public void controlGarageDoor() {
        int status = mControl.getSatus()[5];
        if (status == 0) status = 1;
        else status = 0;
        sendData(api.turnGarageDoor(String.valueOf(status)));

    }

    public void controlMainDoor() {
        int status = mControl.getSatus()[6];
        if (status == 0) status = 1;
        else status = 0;
        sendData(api.turnMainDoor(String.valueOf(status)));

    }

    public void controlYard() {
        int status = mControl.getSatus()[7];
        if (status == 0) status = 1;
        else status = 0;
        sendData(api.turnYard(String.valueOf(status)));

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isTouch) {
            isTouch = false;
            if (!mConnected) {
                updateStatus();
                Toast.makeText(getBaseContext(), "Connect before control", Toast.LENGTH_SHORT).show();
            } else {

                int id = buttonView.getId();
                switch (id) {
                    case R.id.sw_living_room:
                        controlLightLivingRoom();
                        break;
                    case R.id.sw_dinner_room:
                        controlLightDinnerRoom();
                        break;
                    case R.id.sw_garage_room:
                        controlLightGarageRoom();
                        break;
                    case R.id.sw_bed_room:
                        controlLightBedRoom();
                        break;
                    case R.id.sw_wc:
                        controlLightWCRoom();
                        break;
                    case R.id.sw_garage_door:
                        controlGarageDoor();
                        break;
                    case R.id.sw_main_door:
                        controlMainDoor();
                        break;
                    case R.id.sw_side_door:
                        controlYard();
                        break;
                }
            }

        }

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(Control event) {
        mControl = event;
        mTvStatus.setText("Connected");
        mConnected=true;
        updateStatus();
    }

    @Override
    protected void onResume() {
        EventBus.getDefault().register(this);
        super.onResume();
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        isTouch = true;
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.exit:
                sendData(api.postUseNet("0"));
                break;
            case R.id.connect:
                sendData(api.postUseNet("1"));
                break;
            case android.R.id.home:
                finish();
                break;
            case R.id.reset:
                sendData(api.reset());
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
