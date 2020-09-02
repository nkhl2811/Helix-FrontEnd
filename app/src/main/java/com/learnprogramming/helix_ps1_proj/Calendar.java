package com.learnprogramming.helix_ps1_proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

public class Calendar extends AppCompatActivity {

    CalendarView calendarView;
    TextView mydate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView= (CalendarView) findViewById(R.id.calender);
        mydate=(TextView) findViewById(R.id.Mydate);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date= (dayOfMonth+1) + "/"+ month + "/" + year;
                mydate.setText(date);
            }
        });

    }
}