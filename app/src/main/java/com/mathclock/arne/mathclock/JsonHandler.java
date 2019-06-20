package com.mathclock.arne.mathclock;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class JsonHandler {
    public JSONObject create(String data) throws JSONException {
        return new JSONObject(data);
    }
    public JSONObject add(JSONObject data,Object [][] values) throws JSONException {
        JSONObject newdata = new JSONObject();
        for(int i=0;i<values.length;i++) {
            newdata.put(values[i][0].toString(),values[i][1]);
        }
        KeyHandler kh = new KeyHandler();
        data.getJSONObject("times").put(kh.getRandomKey(10),newdata);
        Log.d("arr", data.toString());
        return data;
    }
    public JSONObject delete(JSONObject data,String key) throws JSONException {
        Iterator<String> iter = data.getJSONObject("times").keys();
        data.getJSONObject("times").remove(key);
        return data;
    }
    public JSONObject setValue(JSONObject data,String key,String valueKey,String value) throws JSONException {
        Iterator<String> iter = data.getJSONObject("times").keys();
        data.getJSONObject("times").getJSONObject(key).put(valueKey,value);
        Log.d("test", data.getJSONObject("times").getJSONObject(key).toString());
        return data;
    }

}