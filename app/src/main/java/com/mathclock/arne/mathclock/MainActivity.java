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
import android.widget.LinearLayout;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    ViewGroup viewgroup;
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
    private void init() throws JSONException {
        readData();
        LinearLayout linearLayout =  findViewById(R.id.alarmContainer);
        LayoutInflater layoutInfralte = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View[] view = {layoutInfralte.inflate(R.layout.alarm_dummy, null)};
        linearLayout.addView(view[0]);
    }
    private void readData() throws JSONException {
        FileHandler fh = new FileHandler();
        JSONObject mainObject = new JSONObject(fh.readFile());
        Log.d("Json String", mainObject.getString("times"));
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