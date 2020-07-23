package com.example.helpaid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static int TIME_OUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                if (firebaseUser!=null)
                {
                    Log.d("null","not null");
                    if (firebaseUser.getEmail().equals("admin@gmail.com")) {
                        Log.d("values","called1");
                        startActivity(new Intent(getApplicationContext(), Admin.class));
                        finish();
                    }
                    else if (!firebaseUser.getEmail().equals("admin@gmail.com")) {
                        Log.d("values","called2");
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        finish();
                    }
                }
                if (firebaseUser==null)
                {
                    startActivity(new Intent(getApplicationContext(),login.class));
                    finish();
                }
            }
        },TIME_OUT);
    }
}
