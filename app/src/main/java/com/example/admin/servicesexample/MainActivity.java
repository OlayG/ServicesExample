package com.example.admin.servicesexample;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.admin.servicesexample.services.MyBoundService;
import com.example.admin.servicesexample.services.MyIntentService;
import com.example.admin.servicesexample.services.MyNormalService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    @BindView(R.id.btnStartNormalService)
    Button btnStartNormalService;
    @BindView(R.id.btnStopNormalService)
    Button btnStopNormalService;
    @BindView(R.id.btnStartIntentService)
    Button btnStartIntentService;
    @BindView(R.id.btnBindService)
    Button btnBindService;
    @BindView(R.id.btnUnBindService)
    Button btnUnBindService;

    MyBoundService myBoundService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    public void startServices(View view) {

        Intent normalIntent = new Intent(this, MyNormalService.class);
        Intent intIntent = new Intent(this, MyIntentService.class);

        Intent boundIntent = new Intent(this, MyBoundService.class);

        switch (view.getId()){

            case R.id.btnStartNormalService:
                normalIntent.putExtra("data", "This is a normal service");
                startService(normalIntent);
                break;
            case R.id.btnStopNormalService:
                stopService(normalIntent);
                break;
            case R.id.btnStartIntentService:
                intIntent.putExtra("data", "This is an intent service");
                intIntent.setAction("");
                startService(intIntent);
                break;
            case R.id.btnBindService:
                bindService(boundIntent, serviceConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnUnBindService:
                unbindService(serviceConnection);
                break;
        }
    }

    public ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected: ");
            MyBoundService.MyBinder myBinder = (MyBoundService.MyBinder) iBinder;
            myBoundService = myBinder.getService();
            Log.d(TAG, "onServiceConnected: " + myBoundService.getRandomData());
            Intent in = new Intent(MainActivity.this, Main2Activity.class);
            in.putExtra("data", myBoundService.getRandomData());
            startActivity(in);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: ");
        }
    };
}
