package com.drizzledrop.drizzledrop;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import Service.WeatherServiceCallback;
import Service.YahooWeatherService;
import data.Channel;

public class DelayCalc extends AppCompatActivity implements WeatherServiceCallback {

    private ImageView vehiclePic;
    private ImageView weatherPic;
    private TextView date;
    private TextView delay;

    private YahooWeatherService service;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delay_calc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        vehiclePic = (ImageView)findViewById(R.id.VehiclePic);
        if(MainActivity.vehicle == 0)
            vehiclePic.setImageResource(R.drawable.train);
        else if(MainActivity.vehicle == 1)
            vehiclePic.setImageResource(R.drawable.bus);
        else
            System.out.println("Could not display image");

        date = (TextView)findViewById(R.id.dateDisplay);
        date.setText(MainActivity.date);

        delay = (TextView)findViewById(R.id.DelayTime);
        weatherPic = (ImageView)findViewById(R.id.weatherIcon);

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();
        service.refreshWeather("Matawan, NJ");
    }

    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();
    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();

    }
}
