package com.example.user.mychatapp.mvvm.Service;

import com.example.user.mychatapp.mvvm.Model.Control;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by User on 10/10/2016.
 */

public interface RestApi {
        @GET("control.json")
        Call<Control> getControl();
        @GET("/")
        Call<Control> postUseNet(@Query("use_net") String use);
        @GET("/")
        Call<Control> turnLivingRoom(@Query("living_room") String use);
        @GET("/")
        Call<Control> turnDinnerRoom(@Query("dinner_room") String use);
        @GET("/")
        Call<Control> turnToiletRoom(@Query("toilet_room") String use);
        @GET("/")
        Call<Control> turnGarageRoom(@Query("garage_room") String use);
        @GET("/")
        Call<Control> turnBedRoom(@Query("bed_room") String use);
        @GET("/")
        Call<Control> turnMainDoor(@Query("main_door") String use);
        @GET("/")
        Call<Control> turnGarageDoor(@Query("garage_door") String use);
        @GET("/")
        Call<Control> turnYard(@Query("yard") String use);
        @GET("/?use_net=1&living_room=0&bed_room=0&dinner_room=0&garage_room=0&toilet_room=0&garage_door=0&main_door=0&yard=0")
        Call<Control> reset();

}
