package com.example.cecs453finalproject.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cecs453finalproject.Model.Question;
import com.example.cecs453finalproject.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<Question> questionList;
    Context context;


    public MyAdapter(Context context) {
        this.questionList = new ArrayList<>();
        this.context = context;
    }

    public void addAll(List<Question> newQuestions){
        int initSize = questionList.size();
        notifyItemRangeChanged(initSize,newQuestions.size());
    }

    public String getLastQuestion(){
        return questionList.get(questionList.size()-1).getQuestion();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.question_layout_item,viewGroup,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.txtAnswer.setText(questionList.get(i).getAnswer());
        myViewHolder.txtQuestion.setText(questionList.get(i).getQuestion());

    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtQuestion, txtAnswer;
        public MyViewHolder(View itemView){
            super(itemView);

            txtAnswer = (TextView) itemView.findViewById(R.id.answer);
            txtQuestion = (TextView) itemView.findViewById(R.id.question);


        }

    }
}
