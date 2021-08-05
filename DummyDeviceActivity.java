package com.example.dantest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import iottalk.DAI;

public class DummyDeviceActivity extends AppCompatActivity {

    private SA_Dummy_Device sa;
    private DAI dai;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_device);
        sa = new SA_Dummy_Device(context);
        dai = new DAI(sa);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dai.start();
    }

    @Override
    protected void onPause(){
        super.onPause();
        dai.terminate();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}