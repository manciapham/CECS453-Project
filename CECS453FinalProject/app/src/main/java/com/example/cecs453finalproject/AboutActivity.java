package com.example.cecs453finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AboutActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        AboutFragment about = new AboutFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, about).commit();

    }
}
