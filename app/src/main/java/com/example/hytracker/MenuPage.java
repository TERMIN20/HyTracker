package com.example.hytracker;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MenuPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        Button browsebutton = findViewById(R.id.browseButton);
        //Button trackersbutton = findViewById(R.id.trackersButton);

        browsebutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                OpenBrowsePage();
            }
        }
        );
    }

    public void OpenBrowsePage()
    {
        Intent openIntent = new Intent(this, BrowsePage.class);
        startActivity(openIntent);
    }



}
