package com.example.hytracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class APIEnterPage extends AppCompatActivity {

    static String apiKey = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.api_layout);
        super.onCreate(savedInstanceState);

        EditText apiKeyText = findViewById(R.id.editTextAPI);
        MaterialButton apiOkButton = findViewById(R.id.apiOkButton);

        apiOkButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                apiKey = apiKeyText.getText().toString();
                Log.d("words", "API Key: " + apiKey);
                OpenMenu();

            }
        });

    }

    public static String getApiKey() {
        return apiKey;
    }

    public void OpenMenu(){
        Intent openIntent = new Intent(this, MenuPage.class);
        startActivity(openIntent);
    }

}
