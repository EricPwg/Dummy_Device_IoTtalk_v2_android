//package com.example.dantest;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dantest.R;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONArray;
import org.json.JSONException;

import iottalk.DAN;
import iottalk.DeviceFeature;

public class SA_Dummy_Device {
    private Activity activity;
    private Context context;

    // IoTtalk setting
    public String api_url = "http://localhost:9992/csm";
    public String device_model = "Dummy_Device";
    public String device_name = "Dummy_Test_Android";
    //Set IDFs in this format
    public DeviceFeature DummySensor_I = new DeviceFeature("DummySensor-I", "idf"){
        @Override
        public JSONArray getPushData() throws JSONException {
            JSONArray r = new JSONArray();
            try {
                EditText sensorEditVText = activity.findViewById(R.id.dummy_sensor_text);
                int numPub = Integer.parseInt(sensorEditVText.getText().toString());
                int[] pushData = {numPub};
                r = new JSONArray(pushData);
            } catch (Exception e){
                e.printStackTrace();
                //return null;
            }
            return r;
        }
    };
    public DeviceFeature DummyControl_O = new DeviceFeature("DummyControl-O", "odf"){
        @Override
        public void pullDataCB(MqttMessage message, String df_name) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONArray pd = new JSONArray(new String(message.getPayload(), "UTF-8"));
                        TextView controlTextView = activity.findViewById(R.id.dummy_control_text);
                        controlTextView.setText("Dummy Control : " + pd);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
    };

    //Set the push interval
    public double push_interval = 2;

    DAN dan;

    //invoke after DAN finish register
    public void on_register(DAN dan){
        this.dan = dan;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initUIComponent();
            }
        });
        System.out.println("register successfully");
    }

    //invoke after DAN finish deregister
    public void on_deregister(){
        System.out.println("deregister successfully");
    }

    //invoke after DAN finish connect to server
    public void on_connect(){
        System.out.println("connect successfully");
    }

    //invoke after DAN finish disconnect to server, but NOT INCLUDED unexpected disconnection
    public void on_disconnect(){
        System.out.println("disconnect successfully");
    }

    private void initUIComponent() {
        TextView connectTextView = activity.findViewById(R.id.text_connect);
        connectTextView.setText("Server : "+api_url+"\nDevice model : "+device_model+"\nDevice name : "+device_name);
    }

    public SA_Dummy_Device(Context context){
        this.context = context;
        this.activity = (Activity) context;
    }
}
