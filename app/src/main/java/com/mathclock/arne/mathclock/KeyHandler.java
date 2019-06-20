package com.mathclock.arne.mathclock;

import java.util.Random;

public class KeyHandler {

    public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final String lower = upper.toLowerCase();

    public static final String digits = "0123456789";

    public static final String alphanum = upper + lower + digits;
    public String getRandomKey(int length)
    {
        Random r = new Random();
        String rndString="";
        for(int i=0;i<length;i++)
        {
            int i1 = new Random().nextInt(alphanum.length());
            rndString+=alphanum.charAt(i1);
        }
        return rndString;

    }
}
