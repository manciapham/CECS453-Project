package com.example.cecs453finalproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import java.util.ArrayList;

public class AddQuestionActivity extends AppCompatActivity {

    private Button addQuestionButton;
    private EditText addQuestion;
    private EditText addAnswer;

    ArrayList<String> questionID = new ArrayList<>();
    private static final String TAG = "addq";
    //private static final String TAG = "addq";
   // String qid = "";
    String q = "";
    String a = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        addQuestion= (EditText)findViewById(R.id.et_addQuestion);
        addAnswer= (EditText)findViewById(R.id.et_addAnswer);

        addQuestionButton = (Button) findViewById(R.id.btn_add);
        addQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO::Add Question and Answer to Database
                q = addQuestion.getText().toString();
                a = addAnswer.getText().toString();
                downloadJSON("https://nisalgamage.com/qNa");
                Toast.makeText(getApplicationContext(),"Added",Toast.LENGTH_SHORT).show();


            }
        });

    //downloadJSON("https://nisalgamage.com/qNa");

    }

    private void uplJSON(final String urlWebService) {

        class uplJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {

                String urlString = urlWebService;
                int x=questionID.size()+1;
                System.out.println("size"+x);
                String questionID = Integer.toString(x);
                String question = q;
                String answer = a;
                URL url = null;
                InputStream stream = null;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(urlString);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setDoOutput(true);

                    String data = URLEncoder.encode("questionID", "UTF-8")
                            + "=" + URLEncoder.encode(questionID, "UTF-8");

                    data += "&" + URLEncoder.encode("question", "UTF-8") + "="
                            + URLEncoder.encode(question, "UTF-8");

                    data += "&" + URLEncoder.encode("answer", "UTF-8") + "="
                            + URLEncoder.encode(answer, "UTF-8");

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

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                questionID.clear();
}
        }

        uplJSON getJSON = new uplJSON();
        getJSON.execute();
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
                        JSONArray users = jsonObject.getJSONArray("dataQNA");

                        for (int i = 0; i < users.length(); i++) {
                            JSONObject un = users.getJSONObject(i);
                            String questID = un.getString("questionID");


                            questionID.add(questID);


                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                for (int i=0; i<questionID.size();i++){
                    System.out.println(questionID.get(i));

                }
                return null;
            }

            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                uplJSON("https://nisalgamage.com/pqNa");


            }
        }

        DownloadJSON getJSON = new DownloadJSON();
        getJSON.execute();
    }
}
