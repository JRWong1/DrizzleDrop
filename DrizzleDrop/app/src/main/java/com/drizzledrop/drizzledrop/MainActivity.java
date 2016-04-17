package com.drizzledrop.drizzledrop;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static int vehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void onClickTrain(View view){
        vehicle = 0;
        Intent intent = new Intent(this, DateAndLocSelect.class);
        startActivity(intent);
    }

    public void onClickBus(View view) {
        vehicle = 1;
        Intent intent = new Intent(this, DateAndLocSelect.class);
        startActivity(intent);
    }
}
