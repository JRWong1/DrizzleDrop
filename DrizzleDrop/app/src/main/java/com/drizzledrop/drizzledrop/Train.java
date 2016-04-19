package com.drizzledrop.drizzledrop;


import android.annotation.SuppressLint;
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
import android.support.v4.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by David on 4/18/2016.
 */
public class Train extends Fragment {
   public static Train newInstance(){
       Train train = new Train();
       return train;
   }

    public Train(){

    }

    private String local = "Click to select location";
    TextView location;
    CalendarView calendarView;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View rootView = inflater.inflate(R.layout.train, container, false);
        location = (TextView)rootView.findViewById(R.id.TextViewLocationTrain);
        location.setText(local);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment selectDestinationBus = new SelectDestinationBus();
                selectDestinationBus.show(getActivity().getSupportFragmentManager(), "Location");
            }
        });

        calendarView = (CalendarView)rootView.findViewById(R.id.calendarViewTrain);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                MainActivity.date = String.valueOf((month + 1) + "/" + dayOfMonth + "/" + year);
            }
        });

        button = (Button)rootView.findViewById(R.id.buttonCalculateDelayTrain);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                MainActivity.vehicle = 0;
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
    public class SelectDestinationBus extends DialogFragment {
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
