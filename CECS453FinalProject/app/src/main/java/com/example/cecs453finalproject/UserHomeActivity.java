package com.example.cecs453finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 *  User Homepage
 *  - Settings bar
 *  - Greeting
 *  - Quiz tab w/ play button
 *  - History tab
 */

public class UserHomeActivity extends AppCompatActivity {

    private Button navDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        navDrawer = (Button)findViewById(R.id.btn_nav_drawer);

        navDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHomeActivity.this, NavigationDrawerActivity.class);
                startActivity(intent);
            }
        });
    }
}
