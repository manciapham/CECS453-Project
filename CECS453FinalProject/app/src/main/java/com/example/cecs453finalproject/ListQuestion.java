package com.example.cecs453finalproject;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.cecs453finalproject.Adapter.QuestionAdapter;
import com.example.cecs453finalproject.Model.Question;

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

public class ListQuestion extends AppCompatActivity {
    private ListView listViewQuestions;
    private QuestionAdapter adapter;
    private ArrayList<Question> questions = new ArrayList<Question>();
    ArrayList<String> questionID = new ArrayList<>();
    ArrayList<String> questionz = new ArrayList<>();
    ArrayList<String> answers = new ArrayList<>();
    private static final String TAG = "Listquestions";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        downloadJSON("https://nisalgamage.com/qNa");
        setContentView(R.layout.activity_list_question);
        populateListView();

    }

    public void populateListView(){

        listViewQuestions = (ListView) findViewById(R.id.listViewQuestions);

        //TODO: add questions from database here. replace strings with string data from the database
        questions.add(new Question("What is your name?","Matt"));
        questions.add(new Question("What is your name?","Matt"));
        questions.add(new Question("What is your name?","Matt"));
        questions.add(new Question("What is your name?","Matt"));

        adapter = new QuestionAdapter(this, questions);
        listViewQuestions.setAdapter(adapter);
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
                            String quest = un.getString("question");
                            String ans = un.getString("answer");

                            questionID.add(questID);
                            questionz.add(quest);
                            answers.add(ans);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                for (int i=0; i<questionID.size();i++){
                    System.out.println(questionID.get(i));
                    System.out.println(questionz.get(i));
                    System.out.println(answers.get(i));
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
