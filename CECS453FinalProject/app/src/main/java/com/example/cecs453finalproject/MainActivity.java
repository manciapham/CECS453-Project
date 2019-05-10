package com.example.cecs453finalproject;

import android.content.Intent;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.NetworkOnMainThreadException;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
<<<<<<< HEAD

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
=======
>>>>>>> f3b6a7b6d263b6d50a3ef2ede5f9f5a5ed6a43fd

/**
 *  SIGN IN PAGE
 *  - Logo/App name
 *  - username field
 *  - password field
 *  - login button
 */

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private TextView status;
    private Button login;
    private Button signup;
<<<<<<< HEAD
    boolean check=false;
    int index;
    private static final String TAG = "MainActivity";
    ArrayList<String> uN = new ArrayList<>();
    ArrayList<String> uP = new ArrayList<>();

=======
    private int count = 3;
>>>>>>> f3b6a7b6d263b6d50a3ef2ede5f9f5a5ed6a43fd

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD

=======
        //reading user credentials from the text fields
>>>>>>> f3b6a7b6d263b6d50a3ef2ede5f9f5a5ed6a43fd
        username = (EditText)findViewById(R.id.et_username);
        password = (EditText)findViewById(R.id.et_password);

        //buttons link to an action
        login = (Button)findViewById(R.id.b_login);
        signup = (Button)findViewById(R.id.b_signup);

        downloadJSON("https://nisalgamage.com/userNpass");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validation(username.getText().toString(), password.getText().toString());
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

<<<<<<< HEAD
    private void downloadJSON(final String urlWebService){
        class DownloadJSON extends AsyncTask<Void,Void,String>{
            @Override
            protected String doInBackground(Void... voids) {

                HttpHandler sh = new HttpHandler();
                String jsonStr=sh.makeServiceCall(urlWebService);
                Log.e(TAG,"response"+jsonStr);
                if (jsonStr!=null){
                    try {
                        JSONObject jsonObject=new JSONObject(jsonStr);
                        JSONArray users=jsonObject.getJSONArray("dataUNP");

                        for (int i=0;i<users.length();i++){
                            JSONObject un=users.getJSONObject(i);
                            String usern=un.getString("username");
                            String pass=un.getString("password");

                            uN.add(usern);
                            uP.add(pass);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return null;

            }

            protected void onPreExecute(){
                super.onPreExecute();
            }
        }

        DownloadJSON getJSON=new DownloadJSON();
        getJSON.execute();
    }


    private void validation(final String user, final String pass)
    {
        //TODO: DATABASE CONNECTIVITY CODE
boolean check=false;
for (int i=0;i<uN.size();i++){
    System.out.println("username"+uN.get(i));
    System.out.println("password"+uP.get(i));
    if ((uN.get(i).equals(user)) && (uP.get(i).equals(pass))){
        check=true;
    }
}
if (check==true){
    Intent intent=new Intent(MainActivity.this,UserHomeActivity.class);
    startActivity(intent);
}else{
    //Toast.makeText(getString(R.string.incorrect));
}
=======
    //validates the user credentials in the database
    private void validation(String user, String pass)
    {
        if((user.equals("admin")) && (pass.equals("admin"))) {
            Toast.makeText(MainActivity.this,
                    "Login is Successful!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, UserHomeActivity.class);
            startActivity(intent);
        }

        else{
            count--;
            Toast.makeText(MainActivity.this,
                    "Username or Password is Incorrect", Toast.LENGTH_SHORT).show();
            status.setText("Incorrect Attempts: " + String.valueOf(count));

            if(count == 0)
            {
                login.setEnabled(false);
                System.exit(0);
            }
        }
>>>>>>> f3b6a7b6d263b6d50a3ef2ede5f9f5a5ed6a43fd
    }

}
