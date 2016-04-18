package com.drizzledrop.drizzledrop;

import android.annotation.SuppressLint;
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
import data.Item;

public class DelayCalc extends AppCompatActivity implements WeatherServiceCallback {

    private ImageView vehiclePic;
    private ImageView weatherPic;
    private TextView date;
    private TextView delayTime;
    private TextView temp;
    private TextView cond;
    private TextView local;

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

        delayTime = (TextView)findViewById(R.id.DelayTime);
        weatherPic = (ImageView)findViewById(R.id.weatherIcon);
        temp = (TextView)findViewById(R.id.tempTextView);
        cond = (TextView)findViewById(R.id.condTextView);
        local = (TextView)findViewById(R.id.lcoationTextView);
        local.setText(MainActivity.location);

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();
        service.refreshWeather("New York");
    }

    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();

        Item item = channel.getItem();
        int resourceId = getResources().getIdentifier("drawable/icon_" + item.getCondition().getCode(), null, getPackageName());

        //@SuppressLint("deprecation")
        //Drawable weatherIconDrawable = getResources().getDrawable(resourceId);
        Drawable weatherIconDrawable = getResources().getDrawable(resourceId);

        weatherPic.setImageDrawable(weatherIconDrawable);

        temp.setText(item.getCondition().getTemp()+"\u00B0"+channel.getUnits().getTemp());
        cond.setText(item.getCondition().getDesc());
        local.setText(service.getLocation());

    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();

    }
}
