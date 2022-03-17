package com.example.hytracker;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText username = findViewById(R.id.editTextTextEmailAddress);

        Button loginbutton = findViewById(R.id.Login);


        loginbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if(username.getText().toString().equals("Fart"))
                {
                    Toast.makeText(MainActivity.this, "LOGGED", Toast.LENGTH_SHORT).show();
                    openBrowse();
                }
                else{
                    Toast.makeText(MainActivity.this, "UNLOGGED", Toast.LENGTH_SHORT).show();
                }
            }
        }
        );
    }

    public void openBrowse(){
        Intent openIntent = new Intent(this, BrowsePage.class);
        startActivity(openIntent);
    }
}