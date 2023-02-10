package com.blockent.memoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    EditText editTitle;
    EditText editContent;
    Button btnDate;
    Button btnTime;
    Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        editTitle = findViewById(R.id.editTitle);
        editContent = findViewById(R.id.editContent);
        btnDate = findViewById(R.id.btnDate);
        btnTime = findViewById(R.id.btnTime);
        btnSave = findViewById(R.id.btnSave);

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 오늘 날짜 가져오기
                Calendar current = Calendar.getInstance();

                new DatePickerDialog(
                        AddActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                Log.i("MEMO_APP", "년도 : " + i + ", 월 :"+i1+", 일 :"+i2);
                                // i : 년도, i1 : 월(0~11) , i2 : 일

                                int month = i1 + 1;
                                String strMonth;
                                if(month < 10){
                                    strMonth = "0"+month;
                                }else{
                                    strMonth = ""+month;
                                }

                                String strDay;
                                if(i2 < 10){
                                    strDay = "0"+i2;
                                }else{
                                    strDay= ""+i2;
                                }

                                String date = i + "-" + strMonth + "-" + strDay;
                                btnDate.setText(date);

                            }
                        },
                        current.get(Calendar.YEAR),
                        current.get(Calendar.MONTH),
                        current.get(Calendar.DAY_OF_MONTH)
                ).show();

            }
        });

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar current = Calendar.getInstance();

                new TimePickerDialog(
                        AddActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                // i : hour , i1 : minutes
                                String strHour;
                                if(i < 10){
                                    strHour = "0"+i;
                                }else{
                                    strHour = ""+i;
                                }
                                String strMin;
                                if(i1 < 10){
                                    strMin = "0"+i1;
                                }else{
                                    strMin = ""+i1;
                                }
                                String time = strHour+":"+strMin;
                                btnTime.setText(time);
                            }
                        },
                        current.get(Calendar.HOUR_OF_DAY),
                        current.get(Calendar.MINUTE),
                        true
                ).show();

            }
        });
    }
}



