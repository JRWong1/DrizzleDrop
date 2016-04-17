package com.drizzledrop.drizzledrop;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateAndLocSelect extends AppCompatActivity {

    public static String location = "Touch to select location";
    public static String date = "";
    private  TextView t;
    private CalendarView calendarView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_and_loc_select);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        t=(TextView)findViewById(R.id.selectLocation);
        t.setText(location);
        calendarView = (CalendarView)findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                date = String.valueOf((month+1)+"/"+dayOfMonth+"/"+year);
            }
        });
    }
    public void onClickSelect(View view) {

        DialogFragment newFragment = new SelectDestinationDialogFragment();
        newFragment.show(getSupportFragmentManager(), "Location");
    }

    public void onClickCalculate(View view) {
        if (date == "") {

            String tempDate = new SimpleDateFormat("MM/dd/yyyy").format(new Date());

            date = String.valueOf(tempDate);
        }
        Intent intent = new Intent(this, DelayCalc.class);
        startActivity(intent);
    }

    @SuppressLint("ValidFragment")
    public class SelectDestinationDialogFragment extends DialogFragment {
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.selectPlace).setItems(R.array.stops_array, new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    String[] stops = getResources().getStringArray(R.array.stops_array);
                    System.out.println("Clicked: " + stops[which]);
                    location = stops[which];
                    t.setText(location);
                    System.out.println(t.getText());
                    t.invalidate();
                }
            });

            return builder.create();
        }

    }

}
