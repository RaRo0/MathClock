
        package com.mathclock.arne.mathclock;

        import android.os.Environment;

        import java.io.BufferedReader;
 import java.io.File;
import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.FileReader;
import java.io.IOException;

public class FileHandler {
    public boolean writeFile(String data) throws IOException {
        boolean success=false;
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard.getPath() + "/MathClock", "alarms.conf");

        if (file.exists()) {
            FileOutputStream fileOutput = new FileOutputStream(file, false);
            fileOutput.write(data.getBytes(), 0, data.length());
        }
        return success;
    }
    public String readFile()
    {
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard.getPath() + "/MathClock", "alarms.conf");
        StringBuilder text=null;
        if (file.exists()) {
            String line = "";
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                text = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    text.append(line);
                }
                br.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return text.toString();
    }
}