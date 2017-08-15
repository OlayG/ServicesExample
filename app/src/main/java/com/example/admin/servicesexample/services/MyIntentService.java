package com.example.admin.servicesexample.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {
public static final String TAG = "MyIntentServiceTAG";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        switch (intent.getAction()){

            case "getRepo":
                Log.d(TAG, "onHandleIntent: " + intent.getStringExtra("data"));
                break;
            case "getProfile":
                Log.d(TAG, "onHandleIntent: " + intent.getStringExtra("data"));
                break;
        }
    }
}
