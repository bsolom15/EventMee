package com.eventmeapp.eventmeapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Locale;

/**
 * Created by Erik Fok on 11/4/2016.
 */

public class CreateEvent extends AppCompatActivity {

    EditText etTitle, etLocation, etDescription;
    TextView tvDate, tvTime, tvAMPM;
    Calendar myCalendar, myTimeCalendar;
    private int day, month, year, mHour, mMinute;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_bar_layout);
        setContentView(R.layout.activity_create_event);

        etTitle = (EditText) findViewById(R.id.et_title);
        etTitle = (EditText) findViewById(R.id.et_location);
        etDescription = (EditText) findViewById(R.id.et_description);
        tvDate = (TextView) findViewById(R.id.tv_date);
        tvTime = (TextView) findViewById(R.id.tv_time);
        tvAMPM = (TextView) findViewById(R.id.tv_am_pm);
        myCalendar = Calendar.getInstance();
        myTimeCalendar = Calendar.getInstance();
        Button btCreateEvent = (Button) findViewById(R.id.bt_create_event);



        //DatePicker
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int yearPicked, int monthOfYear,
                                  int dayOfMonth) {
                day = dayOfMonth;
                month = monthOfYear;
                year = yearPicked;

                myCalendar.set(Calendar.YEAR, yearPicked);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelDate();
            }

        };

        tvDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(CreateEvent.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //TimePicker
        tvTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                mHour = 12;
                mMinute = 0;
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(CreateEvent.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        mHour = selectedHour;
                        mMinute = selectedMinute;
                        tvTime.setText(pad(selectedHour, true) + ":" + pad(selectedMinute, false));
                        updateAMPM();
                    }
                }, mHour, mMinute, false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        //CreateButton
        btCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    private void updateLabelDate() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tvDate.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateAMPM() {
        if (mHour < 12)
            tvAMPM.setText("AM");
        else
            tvAMPM.setText("PM");
    }

    private static String pad(int c, boolean isHour) {
        if (c == 0 && isHour)
            return "12";
        else if (c > 12 && isHour)
            return String.valueOf(c - 12);
        else if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

}