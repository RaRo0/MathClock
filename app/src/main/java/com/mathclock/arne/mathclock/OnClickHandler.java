package com.mathclock.arne.mathclock;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class OnClickHandler extends MainActivity implements View.OnClickListener
{
    String key;
    Callable<Void> function;
    public OnClickHandler(String key, Callable<Void> function) {
        this.key=key;
        this.function=function;

    }

    @Override
    public void onClick(View v)
    {
        JsonHandler jh=new JsonHandler();
        FileHandler fh=new FileHandler();
        try {
            fh.writeFile(jh.delete(jh.create(fh.readFile()),this.key).toString());
            function.call();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //fh.writeFile(jh.delete(this.key));
    }


}
