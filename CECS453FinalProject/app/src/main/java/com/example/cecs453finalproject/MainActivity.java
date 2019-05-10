package com.example.cecs453finalproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


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

    private Button testButton;

    boolean check = false;
    int index;
    private static final String TAG = "MainActivity";
    ArrayList<String> uN = new ArrayList<>();
    ArrayList<String> uP = new ArrayList<>();

    private int count = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //reading user credentials from the text fields

        username = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.et_password);

        //buttons link to an action
        login = (Button) findViewById(R.id.b_login);
        signup = (Button) findViewById(R.id.b_signup);

        testButton = (Button) findViewById(R.id.b_test);

        downloadJSON("https://nisalgamage.com/userNpass");

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddQuestionActivity.class);
                startActivity(intent);
            }
        });

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

    private void downloadJSON(final String urlWebService) {
        class DownloadJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {

                HttpHandler sh = new HttpHandler();
                String jsonStr = sh.makeServiceCall(urlWebService);
                Log.e(TAG, "response" + jsonStr);
                if (jsonStr != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(jsonStr);
                        JSONArray users = jsonObject.getJSONArray("dataUNP");

                        for (int i = 0; i < users.length(); i++) {
                            JSONObject un = users.getJSONObject(i);
                            String usern = un.getString("username");
                            String pass = un.getString("password");

                            uN.add(usern);
                            uP.add(pass);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }

            protected void onPreExecute() {
                super.onPreExecute();
            }
        }

        DownloadJSON getJSON = new DownloadJSON();
        getJSON.execute();
    }

    private void validation(final String user, final String pass) {
        boolean check = false;
        for (int i = 0; i < uN.size(); i++) {
            System.out.println("Username" + uN.get(i));
            System.out.println("Password" + uP.get(i));
            if ((uN.get(i).equals(user)) && (uP.get(i).equals(pass))) {
                check = true;
            }
        }

        if (check == true) {
            Intent intent = new Intent(MainActivity.this, UserHomeActivity.class);
            startActivity(intent);
            Toast.makeText(MainActivity.this,
                    "WELCOME QUIZTAKER!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this,
                    "INCORRECT USERNAME OR PASSWORD", Toast.LENGTH_SHORT).show();
        }
    }
}