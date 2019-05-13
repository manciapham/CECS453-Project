package com.example.cecs453finalproject.Adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cecs453finalproject.Model.Question;
import com.example.cecs453finalproject.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class QuestionAdapter extends BaseAdapter {

    Activity context;
    ArrayList<Question> questions;
    private static LayoutInflater inflater = null;

    public QuestionAdapter(Activity context, ArrayList<Question> questions){
        this.context = context;
        this.questions = questions;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount(){
        return questions.size();
    }

    @Override
    public Question getItem(int position){
        return questions.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent){
        View itemView = convertView;
        itemView = (itemView == null) ? inflater.inflate(R.layout.list_questions, null): itemView;
        TextView textViewQuestion = (TextView) itemView.findViewById(R.id.textViewQuestion);
        TextView textViewAnswer = (TextView) itemView.findViewById(R.id.textViewAnswer);
        Question selectedQuestion = questions.get(position);
        textViewAnswer.setText(selectedQuestion.getQuestion());
        textViewQuestion.setText(selectedQuestion.getAnswer());
        return itemView;
    }



}
