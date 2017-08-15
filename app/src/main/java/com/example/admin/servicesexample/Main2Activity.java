package com.example.admin.servicesexample;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.admin.servicesexample.services.MyBoundService;
import com.example.admin.servicesexample.services.MyNormalService;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {
    public static final String TAG = "Main2Activity";
    MyBoundService myBoundService;
    @BindView(R.id.tvDisplaySum)
    TextView tvDisplaySum;

    ArrayList<String> stringList;
    @BindView(R.id.rvStringList)
    RecyclerView rvStringList;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;
    RecyclerViewAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);


        Intent boundIntent = new Intent(this, MyBoundService.class);
        bindService(boundIntent, serviceConnection, Context.BIND_AUTO_CREATE);

        startService(new Intent(this, MyNormalService.class));

        layoutManager = new LinearLayoutManager(this);
        itemAnimator = new DefaultItemAnimator();
        rvStringList.setLayoutManager(layoutManager);
        rvStringList.setItemAnimator(itemAnimator);



    }

    public ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected: ");
            MyBoundService.MyBinder myBinder = (MyBoundService.MyBinder) iBinder;
            myBoundService = myBinder.getService();
            Log.d(TAG, "onServiceConnected: " + myBoundService.getRandomData());

            Intent in = getIntent();
            Integer a = in.getIntExtra("data", -1);
            Integer b = myBoundService.getRandomData();
            tvDisplaySum.setText(a.toString() + " + " + b.toString() + " = " + (a + b));
            // RECYCLER VIEW STUFF
            stringList = myBoundService.getStringData(a);
            rvAdapter = new RecyclerViewAdapter(stringList);
            rvStringList.setAdapter(rvAdapter);
            rvAdapter.notifyDataSetChanged();


        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: ");
        }
    };

}
