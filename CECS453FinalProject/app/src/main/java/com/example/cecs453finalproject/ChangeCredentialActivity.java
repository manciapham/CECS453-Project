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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class ChangeCredentialActivity extends AppCompatActivity {

    private EditText changeUser;
    private EditText changePass;
    private EditText confirmPass;
    private EditText changeEmail;
    private Button save;
    String un="";
    String up="";
    String up2="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_credential);

        //reading user credentials from the text fields
        changeUser = (EditText)findViewById(R.id.et_change_user);
        changePass = (EditText)findViewById(R.id.et_change_pass);
        confirmPass = (EditText)findViewById(R.id.et_change_repass);

        //buttons link to an action
        save = (Button)findViewById(R.id.btn_change_save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                un=changeUser.getText().toString();
                up=changePass.getText().toString();
                up2=confirmPass.getText().toString();

                if (up.equals(up2)){
                    upJSON("https://www.nisalgamage.com/updatePass");
                }else {
                    Toast.makeText(getApplicationContext(),"Passwords dont match",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void validation(String user, String pass, String rePass,
                            String mail)
    {
        //TODO: INSERT DATABASE CONNECTIVITY CODE HERE
    }

    private void upJSON(final String urlWebService) {

        class upJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {

                String urlString = urlWebService;
                String userName = un;
                String password = up;
                URL url = null;
                InputStream stream = null;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(urlString);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setDoOutput(true);

                    String data = URLEncoder.encode("username", "UTF-8")
                            + "=" + URLEncoder.encode(userName, "UTF-8");

                    data += "&" + URLEncoder.encode("newpassword", "UTF-8") + "="
                            + URLEncoder.encode(password, "UTF-8");

                    urlConnection.connect();

                    OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
                    wr.write(data);
                    wr.flush();

                    stream = urlConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"), 8);
                    String result = reader.readLine();
                    return result;

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.i("Result", "SLEEP ERROR");
                }
                return null;

            }

            protected void onPreExecute() {
                super.onPreExecute();
            }
        }
        upJSON getJSON = new upJSON();
        getJSON.execute();
    }
}