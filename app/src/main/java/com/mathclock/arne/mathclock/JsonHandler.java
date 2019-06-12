package com.mathclock.arne.mathclock;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonHandler {
    public JSONObject create(String data) throws JSONException {
        return new JSONObject(data);
    }
    public JSONObject add(JSONObject data,String [][] values) throws JSONException {
        JSONObject newdata = new JSONObject();
        for(int i=0;i<values.length;i++) {
            newdata.put(values[i][0],values[i][1]);
        }
        data.getJSONObject("times").put("times",newdata);
        Log.d("arr", data.getJSONObject("times").toString());
        return data;
    }
}