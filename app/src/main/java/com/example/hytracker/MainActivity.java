package com.example.hytracker;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText username = findViewById(R.id.editTextAPI);
        EditText password = findViewById(R.id.editTextTextPassword);
        //EditText APIKey = findViewById(R.id.editTextAPIKey);



        Button loginbutton = findViewById(R.id.Login);


        loginbutton.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view)
            {
                OpenMenu();
                //apiKey = APIKey.getText().toString();
                if(username.getText().toString().equals("Username") && password.getText().toString().equals("Password"))
                {
                    Toast.makeText(MainActivity.this, "LOGGED IN", Toast.LENGTH_SHORT).show();
                    OpenMenu();
                }
                else{
                    Toast.makeText(MainActivity.this, "Please Retry", Toast.LENGTH_SHORT).show();
                }
            }
        }
        );
    }


    public void OpenMenu(){
        Intent openIntent = new Intent(this, MenuPage.class);
        startActivity(openIntent);
    }
}