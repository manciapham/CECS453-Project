package com.example.cecs453finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 *  Admin Homepage
 *  - Settings bar
 *  - Greeting
 *  - List of questions
 *  - Add question button
 */
public class AdminHomeActivity extends AppCompatActivity {

    private Button addQuestion;
    private Button questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);

        addQuestion = (Button) findViewById(R.id.b_addQuestions);
        questionList = (Button) findViewById(R.id.b_questionlist);


        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeActivity.this, AddQuestionActivity.class);
                startActivity(intent);
            }
        });

        questionList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });



    }
}
