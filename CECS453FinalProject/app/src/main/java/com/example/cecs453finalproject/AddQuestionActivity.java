package com.example.cecs453finalproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.cecs453finalproject.Adapter.MyAdapter;
import com.example.cecs453finalproject.Model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

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

    RecyclerView recyclerView;

    final int ITEM_LOAD_COUNT = 21;
    int total_item = 0,last_visible_item;
    MyAdapter adapter;
    boolean isLoading=false,isMaxData=false;

    String last_node = "", last_key ="";

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.admin_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.item_refresh){
            isMaxData = false;
            last_node = adapter.getLastQuestion();
            getLastKeyFromFirebase();
            getQuestions();
        }
        return true;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Questions");

        getLastKeyFromFirebase();

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter = new MyAdapter(this);
        recyclerView.setAdapter(adapter);

        getQuestions();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                total_item = layoutManager.getItemCount();
                last_visible_item = layoutManager.findLastVisibleItemPosition();

                if(!isLoading && total_item <- ((last_visible_item + ITEM_LOAD_COUNT))){
                    getQuestions();
                    isLoading = true;
                }
            }
        });

    }

    private void getQuestions(){
        if(!isMaxData){
            Query query;
            if(TextUtils.isEmpty(last_node)) {
                query = FirebaseDatabase.getInstance().getReference()
                        .child("Questions")
                        .orderByKey()
                        .limitToFirst(ITEM_LOAD_COUNT);
            }else{
                query = FirebaseDatabase.getInstance().getReference()
                        .child("Questions")
                        .orderByKey()
                        .startAt(last_node)
                        .limitToFirst(ITEM_LOAD_COUNT);
            }
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.hasChildren()){
                        List<Question> newQuestions = new ArrayList<>();
                        for(DataSnapshot questionSnapShot:dataSnapshot.getChildren()){
                            newQuestions.add(questionSnapShot.getValue(Question.class));
                        }
                        last_node = newQuestions.get(newQuestions.size() - 1).getQuestion();

                        if(!last_node.equals(last_key)){
                            newQuestions.remove(newQuestions.size() - 1);
                        }else{
                            last_node = "end";
                        }
                        adapter.addAll(newQuestions);
                        isLoading = false;
                    }
                    else{
                        isLoading = false;
                        isMaxData = false;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    isLoading = false;
                }
            });
        }
    }

    private void getLastKeyFromFirebase(){
        Query getLastKey = FirebaseDatabase.getInstance().getReference()
                .child("Questions")
                .orderByKey()
                .limitToLast(1);

        getLastKey.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot lastKey : dataSnapshot.getChildren()){
                    last_key = lastKey.getKey();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AddQuestionActivity.this, "Cannot get Last key", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
