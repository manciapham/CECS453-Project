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

import java.util.ArrayList;
import java.util.Collections;

/**
 * Display quiz
 */

public class QuizActivity extends AppCompatActivity {

    private TextView question0;
    private EditText answer0;
    private TextView question1;
    private EditText answer1;
    private TextView question2;
    private EditText answer2;
    private TextView question3;
    private EditText answer3;
    private TextView question4;
    private EditText answer4;
    private TextView question5;
    private EditText answer5;
    private TextView question6;
    private EditText answer6;
    private TextView question7;
    private EditText answer7;
    private TextView question8;
    private EditText answer8;
    private TextView question9;
    private EditText answer9;
    private Button quizSubmit;

    private static final String TAG = "QuizActivity";
    ArrayList<String> uQ = new ArrayList<>(); // array of questions
    ArrayList<String> uA = new ArrayList<>(); // array of answers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //submit quiz button
        quizSubmit = (Button) findViewById(R.id.quizButton);

        downloadJSON("https://nisalgamage.com/qNa");

        ArrayList<Integer> order = new ArrayList<>(); //order of questions
        for(int i = 0; i < uQ.size(); i++ ) {
            order.add(i);
        }
        Collections.shuffle(order);

        question0.setText("1. " + uQ.get(order.get(0)));
        question1.setText("2. " + uQ.get(order.get(1)));
        question2.setText("3. " + uQ.get(order.get(2)));
        question3.setText("4. " + uQ.get(order.get(3)));
        question4.setText("5. " + uQ.get(order.get(4)));
        question5.setText("6. " + uQ.get(order.get(5)));
        question6.setText("7. " + uQ.get(order.get(6)));
        question7.setText("8. " + uQ.get(order.get(7)));
        question8.setText("9. " + uQ.get(order.get(8)));
        question9.setText("10. " + uQ.get(order.get(9)));

        quizSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: submit quiz & grade
            }
        });
    }

    ////
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
                            String quest = un.getString("question");
                            String ans = un.getString("answer");

                            uQ.add(quest);
                            uA.add(ans);
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

    private void gradeQuestion(final String userAnswer, final String correctAnswer) {

    }
}
