package com.mathclock.arne.mathclock;

import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.Callable;

public class OnChangeHandler extends MainActivity implements CompoundButton.OnCheckedChangeListener {
    String key;
    public OnChangeHandler(String key) {
        this.key=key;

    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        JsonHandler jh=new JsonHandler();
        FileHandler fh=new FileHandler();
        try {
            fh.writeFile(jh.setValue(jh.create(fh.readFile()),this.key,"status", isChecked ? "on" : "off").toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
