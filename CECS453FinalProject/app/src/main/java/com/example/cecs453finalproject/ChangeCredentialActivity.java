package com.example.cecs453finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChangeCredentialActivity extends AppCompatActivity {

    private EditText changeUser;
    private EditText changePass;
    private EditText confirmPass;
    private EditText changeEmail;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_credential);

        //reading user credentials from the text fields
        changeUser = (EditText)findViewById(R.id.et_change_user);
        changePass = (EditText)findViewById(R.id.et_change_pass);
        confirmPass = (EditText)findViewById(R.id.et_change_repass);
        changeEmail = (EditText)findViewById(R.id.et_change_email);

        //buttons link to an action
        save = (Button)findViewById(R.id.btn_change_save);
    }

    private void validation(String user, String pass, String rePass,
                            String mail)
    {
        //TODO: INSERT DATABASE CONNECTIVITY CODE HERE
    }
}