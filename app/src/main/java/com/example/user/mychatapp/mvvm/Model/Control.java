package com.example.user.mychatapp.mvvm.Model;

/**
 * Created by User on 10/10/2016.
 */

public class Control {
    private String use_net;
    private String living_room;
    private String bed_room;
    private String dinner_room;
    private String garage_room;
    private String toilet_room;
    private String garage_door;
    private String main_door;
    private String yard;
    public String toString(){
        return "use_net "+use_net+" ; living_room "+living_room+" ; bed_room "+bed_room
                +"  ; dinner_room " +dinner_room+ " ; garage_room "+garage_room+"  ;toilet_room" +toilet_room
                +" ; garage_door "+garage_door+ " ; main_door "+main_door+" ; yard  "+yard;
    }
    public int getConnect(){
        return Integer.valueOf(use_net);
    }
    public int [] getSatus(){
        int st[]=new int[8];
        st[0]=Integer.parseInt(living_room);
        st[1]=Integer.parseInt(bed_room);
        st[2]=Integer.parseInt(dinner_room);
        st[3]=Integer.parseInt(garage_room);
        st[4]=Integer.parseInt(toilet_room);
        st[5]=Integer.parseInt(garage_door);
        st[6]=Integer.parseInt(main_door);
        st[7]=Integer.parseInt(yard);
        return st;
    }

}
