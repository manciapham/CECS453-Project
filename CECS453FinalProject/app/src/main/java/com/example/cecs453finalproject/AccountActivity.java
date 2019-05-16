package com.example.cecs453finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

public class AccountActivity extends AppCompatActivity{

    private TextView username;
    private TextView password;
    private Button edit;
    private Button back;

    String userValue;
    String passValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        username = (TextView)findViewById(R.id.userText);
        password = (TextView)findViewById(R.id.userPass);
        edit = (Button)findViewById(R.id.b_edit_account);
        back = (Button)findViewById(R.id.b_back_to_home);

        //TODO: FIX THIS PART OF THE CODE TO DISPLAY CREDENTIALS CORRECTLY
        userValue = getIntent().getExtras().getString("Username");
        passValue = getIntent().getExtras().getString("Password");

        username.setText("USERNAME: " + userValue);
        password.setText("EMAIL: " + passValue);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, UserHomeActivity.class);
                startActivity(intent);
            }
        });
    }
}