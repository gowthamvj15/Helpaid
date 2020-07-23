package com.example.helpaid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class User_View_law extends AppCompatActivity {

    private TextView title,desc;
    private String Title,Desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__view_law);

        title = findViewById(R.id.title);
        desc = findViewById(R.id.desc);

        Title = getIntent().getStringExtra("title");
        Desc = getIntent().getStringExtra("desc");

        title.setText(Title);
        desc.setText(Desc);
    }
}
