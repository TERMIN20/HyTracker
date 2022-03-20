package com.example.hytracker;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static String apiKey = "8ec31ca6-3ece-4ce5-afb1-1bc764ef702a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText username = findViewById(R.id.editTextTextEmailAddress);
        EditText password = findViewById(R.id.editTextTextPassword);



        Button loginbutton = findViewById(R.id.Login);


        loginbutton.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view)
            {
                OpenMenu();
                if(username.getText().toString().equals("Username") && password.getText().toString().equals("Password"))
                {
                    Toast.makeText(MainActivity.this, "LOGGED IN", Toast.LENGTH_SHORT).show();
                    OpenMenu();
                }
                else{
                    Toast.makeText(MainActivity.this, "DOESN'T WORK", Toast.LENGTH_SHORT).show();
                }
            }
        }
        );
    }

    public static String getKey(){
        return apiKey;
    }

    public void OpenMenu(){
        Intent openIntent = new Intent(this, MenuPage.class);
        startActivity(openIntent);
    }
}