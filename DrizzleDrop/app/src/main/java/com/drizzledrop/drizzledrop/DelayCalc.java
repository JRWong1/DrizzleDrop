package com.drizzledrop.drizzledrop;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class DelayCalc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delay_calc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView vehiclePic = (ImageView)findViewById(R.id.VehiclePic);
        if(MainActivity.vehicle == 0)
            vehiclePic.setImageResource(R.drawable.train);
        else if(MainActivity.vehicle == 1)
            vehiclePic.setImageResource(R.drawable.bus);
        else
            System.out.println("Could not display image");
    }

}
