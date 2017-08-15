package com.example.admin.servicesexample.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import java.util.ArrayList;
import java.util.Random;

public class MyBoundService extends Service {
    public static final String TAG = "MyBoundService";

/*
    private MediaPlayer player;
*/
    IBinder iBinder = new MyBinder();

    public MyBoundService() {
    }

/*    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        player.setLooping(true);
        player.start();

        return START_STICKY;
    }*/

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    public class MyBinder extends Binder {

        public MyBoundService getService(){
            return MyBoundService.this;
        }
    }

    public int getRandomData(){
        Random r = new Random();
        return r.nextInt(100);
    }

    public ArrayList<String> getStringData(int size){
        ArrayList<String> randString = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Random r =new Random();
            randString.add(String.valueOf(r.nextInt(100000)));
        }

        return randString;
    }
}
