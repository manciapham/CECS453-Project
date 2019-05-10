package com.example.cecs453finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 *  SIGN UP PAGE
 *  - username field
 *  - password field
 *  - password check field
 *  - email field
 *  - submit button
 *  - cancel button
 */

public class SignupActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EditText confirmPass;
    private EditText email;
    private Button submit;
    private Button cancel;


    //TODO: ADD CODE FOR DATABASE CONNECTIVITY

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = (EditText)findViewById(R.id.et_new_username);
        password = (EditText)findViewById(R.id.et_new_password);
        confirmPass = (EditText)findViewById(R.id.et_new_repassword);
        email = (EditText)findViewById(R.id.et_new_email);
        submit = (Button)findViewById(R.id.b_signup_submit);
        cancel = (Button)findViewById(R.id.b_signup_cancel);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation(username.getText().toString(), password.getText().toString(),
                        confirmPass.getText().toString(), email.getText().toString());
            }

        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void validation(String user, String pass, String rePass,
                            String mail)
    {
            //TODO: INSERT DATABASE CONNECTIVITY CODE HERE
    }
}
