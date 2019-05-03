package com.example.cecs453finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 *  SIGN IN PAGE
 *  - Logo/App name
 *  - username field
 *  - password field
 *  - login button
 */
public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private TextView status;
    private Button login;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.et_username);
        password = (EditText)findViewById(R.id.et_password);
        login = (Button)findViewById(R.id.b_login);
        signup = (Button)findViewById(R.id.b_signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation(username.getText().toString(), password.getText().toString());
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

    }

    private void validation(String user, String pass)
    {
        //TODO: DATABASE CONNECTIVITY CODE

    }
}
