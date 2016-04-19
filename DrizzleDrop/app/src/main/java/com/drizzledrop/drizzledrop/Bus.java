package com.drizzledrop.drizzledrop;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by David on 4/18/2016.
 */
public class Bus extends Fragment {
    public static Bus newInstance(){
        Bus bus = new Bus();
        return bus;
    }
    public Bus(){

    }
    private String local = "Click to select location";
    TextView location;
    CalendarView calendarView;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.bus, container, false);
        location = (TextView)rootView.findViewById(R.id.TextViewLocationBus);
        location.setText(local);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment selectDestinationBus = new SelectDestinationTrain();
                selectDestinationBus.show(getActivity().getSupportFragmentManager(), "Location");
            }
        });

        calendarView = (CalendarView)rootView.findViewById(R.id.calendarViewBus);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                MainActivity.date = String.valueOf((month + 1) + "/" + dayOfMonth + "/" + year);
            }
        });

        button = (Button)rootView.findViewById(R.id.buttonCalculateDelayBus);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View view){
                MainActivity.vehicle = 1;
                if (MainActivity.date == "") {

                    String tempDate = new SimpleDateFormat("MM/dd/yyyy").format(new Date());

                    MainActivity.date = String.valueOf(tempDate);
                    System.out.println(tempDate);
                }
                Intent intent = new Intent(getContext(), DelayCalc.class);
                startActivity(intent);

            }
        });
        return rootView;
    }

    @SuppressLint("ValidFragment")
    private class SelectDestinationTrain extends DialogFragment {
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.selectPlace).setItems(R.array.stops_array, new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    String[] stops = getResources().getStringArray(R.array.stops_array);
                    System.out.println("Clicked: " + stops[which]);



                    local = stops[which];
                    location.setText(local);
                    System.out.println(location.getText());
                    location.invalidate();
                }
            });

            return builder.create();
        }
    }
}
