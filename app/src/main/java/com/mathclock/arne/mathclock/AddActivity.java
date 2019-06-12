
        package com.mathclock.arne.mathclock;

        import android.app.TimePickerDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Gravity;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.LinearLayout;
        import android.widget.RadioButton;
        import android.widget.TextView;
        import android.widget.TimePicker;

        import org.json.JSONException;

        import java.util.Arrays;
        import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    public TimePickerDialog tpd;
    TextView tc;
    RadioButton [] rbWeekdaysArray=new RadioButton[7];
    boolean[] weekdaysBool=new boolean[7];
    String [] weekdays={"M","D","M","D","F","S","S"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        init();
    }
    private void init()
    {
        initRadioButtons();
        initTimePicker();
        initAddButton();
    }
    private void initTimePicker()
    {
        tc = (TextView) findViewById(R.id.time);
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        tc.setText(String.format("%02d", hour)+":"+String.format("%02d", hour));


        tc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = Integer.parseInt(tc.getText().toString().split(":")[0]);
                int minute = Integer.parseInt(tc.getText().toString().split(":")[1]);
                tpd = new TimePickerDialog(AddActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tc.setText(String.format("%02d", selectedHour)+":"+String.format("%02d", selectedMinute));
                    }
                }, hour, minute, true);//Yes 24 hour time
                tpd.setTitle("Select Time");
                tpd.show();

            }
        });
    }
    private void initRadioButtons()
    {
        Arrays.fill(weekdaysBool, false);
        LinearLayout weekdaysLayout= findViewById(R.id.weekdays);
        for(int i=0;i<weekdays.length;i++)
        {
            RadioButton rb =new RadioButton(this);
            rb.setText(weekdays[i]);
            rb.setTextSize(24);
            rb.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            rb.setClickable(false);
            rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RadioButton rb=(RadioButton)v;
                    LinearLayout weekdaysLayout= findViewById(R.id.weekdays);
                    weekdaysBool[weekdaysLayout.indexOfChild(rb)]=!weekdaysBool[weekdaysLayout.indexOfChild(rb)];
                    rb.setChecked(weekdaysBool[weekdaysLayout.indexOfChild(rb)]);
                }
            });
            weekdaysLayout.addView(rb);
            weekdaysLayout.setGravity(Gravity.CENTER);
            rbWeekdaysArray[i]=rb;

        }

    }
    private void initAddButton()
    {
        Button addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileHandler fh = new FileHandler();
                JsonHandler jh = new JsonHandler();
                String test [][]=new String[2][2];
                test[0][0]="key";
                test[0][1]="value";
                test[1][0]="key";
                test[1][1]="value2";
                try {
                    jh.add(jh.create(fh.readFile()),test).toString();
                    //Log.d("arr", );
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });
    }
}