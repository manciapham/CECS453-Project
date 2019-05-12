package com.example.cecs453finalproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.io.InputStreamReader;
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
    private static final String TAG = "signup";
    String UN = "";
    String UP = "";

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
                UN = username.getText().toString();
                UP = password.getText().toString();
                System.out.println(UN+UP);
                downloadJSON("https://nisalgamage.com/registerUser");
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

    private void downloadJSON(final String urlWebService) {

        class DownloadJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {

                String urlString = urlWebService;
                String userName = UN;
                String password = UP;
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

                    data += "&" + URLEncoder.encode("password", "UTF-8") + "="
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

        DownloadJSON getJSON = new DownloadJSON();
        getJSON.execute();
    }


}
