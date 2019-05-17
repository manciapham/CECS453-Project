package com.example.cecs453finalproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cecs453finalproject.Model.Question;
//import com.example.quizzical.SQL.DatabaseHelper2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Display quiz
 */

public class QuizActivity extends AppCompatActivity {

    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTION_LIST = "keyQuestionList";
    ArrayList<String> questionID = new ArrayList<>();
    ArrayList<String> questionz = new ArrayList<>();
    ArrayList<String> answers = new ArrayList<>();
    private static final String TAG = "quizact";

    private TextView textViewQuestion;
    private TextView textViewQuestionCount;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonNext;

    private ArrayList<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;
    public static int score;

    private Boolean answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        downloadJSON("https://nisalgamage.com/qNa");
        score = 0;

        textViewQuestion = findViewById(R.id.text_view_question);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        rb4 = findViewById(R.id.radio_button4);
        buttonNext = findViewById(R.id.button_next);

        if (savedInstanceState == null) {
           // DatabaseHelper2 databaseHelper2 = new DatabaseHelper2(this);
           // questionList = databaseHelper2.getAllQuestion();
           // questionCountTotal = questionList.size();
           // questionCountTotal = questionz.size();
           // questionList = questionz;
           // Collections.shuffle(questionList);

            showNextQuestion();
        } else {
            questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            if (questionList == null) {
                showScore();
            }
            questionCountTotal = questionList.size();
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion = questionList.get(questionCounter - 1);
            score = savedInstanceState.getInt(KEY_SCORE);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);
        }

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (questionCounter < questionCountTotal) {
                        buttonNext.setText("Next");
                    } else {
                        buttonNext.setText("Finish");
                    }
                    showNextQuestion();
                }
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

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }
        }

        DownloadJSON getJSON = new DownloadJSON();
        getJSON.execute();
    }

    private void showNextQuestion() {
        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getAnswer1());
            rb2.setText(currentQuestion.getAnswer2());
            rb3.setText(currentQuestion.getAnswer3());
            rb4.setText(currentQuestion.getAnswer4());

            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;

        } else {
            showScore();
        }
    }

    private void checkAnswer() {
        answered = true;

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answer = rbGroup.indexOfChild(rbSelected) + 1;

        if ( answer == Integer.parseInt(currentQuestion.getCorrect())) {
            score++;
        }
    }

    public static int getScore(){
        return score;
    }

    private void showScore() {
        Intent intent = new Intent(QuizActivity.this, GradeQuizActivity.class);
        startActivity(intent);
        finishQuiz();
    }

    private void finishQuiz() {
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE, score);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, questionList);
    }
}
