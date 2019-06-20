package com.mathclock.arne.mathclock;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.concurrent.Callable;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            permissionChecker();
            init();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Button set_alarm = findViewById(R.id.add_alarm);

        set_alarm.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, AddActivity.class);
                        startActivity(intent);
                    }
                }
        );

    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            init();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void init() throws JSONException {
        FileHandler fh = new FileHandler();
        JsonHandler jh= new JsonHandler();
        createAlarms(jh.create(fh.readFile()));

    }
    private void createAlarms(JSONObject data) throws JSONException {
        LinearLayout linearLayout =  findViewById(R.id.alarmContainer);
        linearLayout.removeAllViews();
        LayoutInflater layoutInfralte = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Iterator<String> iter = data.getJSONObject("times").keys();
        /*
        while (iter.hasNext()) {
            String key = iter.next();
            try {
                JSONObject value =  data.getJSONObject("times").getJSONObject(key);
                final View[] view = {layoutInfralte.inflate(R.layout.alarm_dummy, null)};
                ViewGroup viewgroup = (ViewGroup) view[0];
                ((Switch) viewgroup.getChildAt(0)).setChecked(value.get("status").toString().contentEquals("on"));//switch
                ((Switch) viewgroup.getChildAt(0)).setOnCheckedChangeListener(new OnChangeHandler(key));
                ((RelativeLayout) viewgroup.getChildAt(1)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("test", "onClick: test");
                    }
                });//Relativ layout
                ((TextView) viewgroup.getChildAt(2)).setText(value.get("time").toString());//uhrzeit
                ((TextView) viewgroup.getChildAt(3)).setText(getWeekdays(value.get("weekdays").toString()));//Wochentage
                ((ImageButton) viewgroup.getChildAt(5)).setOnClickListener(new OnClickHandler(key,new Callable<Void>() {
                    public Void call() throws JSONException {
                        init();
                        return null;
                    }}));//DeletButton

                linearLayout.addView(viewgroup);
            } catch (JSONException e) {
                Log.d("err",e.toString());
            }
        }*/

    }
    private String getWeekdays(String data)
    {
        String [] weekdays={"Mo","Di","Mi","Do","Fr","Sa","So"};
        String value="";
        for(int i=0;i<weekdays.length;i++)
        {
            if(data.split(",")[i].contains("true")) {
                value+=weekdays[i]+",";
            }
        }
        value=value.substring(0,value.length()-1);
        Log.d("test",value);
        return value;
    }

    public boolean permissionChecker() {
        int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        if(permissionCheck==0)
        {
            return true;
        }
        else
        {
            return  false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}