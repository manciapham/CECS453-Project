package com.example.cecs453finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.cecs453finalproject.Adapter.QuestionAdapter;
import com.example.cecs453finalproject.Model.Question;

import java.util.ArrayList;

public class ListQuestion extends AppCompatActivity {
    private ListView listViewQuestions;
    private QuestionAdapter adapter;
    private ArrayList<Question> questions = new ArrayList<Question>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_question);



    }

    public void populateListView(){
        //TODO: add questions from database here

        listViewQuestions = (ListView) findViewById(R.id.listViewQuestions);

        adapter = new QuestionAdapter(this, questions);
        listViewQuestions.setAdapter(adapter);
    }
}
