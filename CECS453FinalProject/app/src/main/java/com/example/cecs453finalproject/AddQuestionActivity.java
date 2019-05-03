package com.example.cecs453finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 *  Activity for adding a question
 *  - settings
 *  - greeting
 *  - title
 *  - question textbox
 *  - answer choice boxes
 *  - checkbox for correct answer
 *  - submit button
 */
public class AddQuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
    }
}
