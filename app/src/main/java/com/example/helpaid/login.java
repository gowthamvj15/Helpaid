package com.example.helpaid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class login extends AppCompatActivity {

    private EditText email,pass;
    private Button login;
    private TextView signin;
    private String Email,Pass;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        setUI();
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),signup.class));
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                getValues();
                if(Email.equals("admin@gmail.com") && Pass.equals("123456"))
                {
                    startActivity(new Intent(getApplicationContext(),Admin.class));
                    finish();
                }
                else {
                    if (validate()) {
                        FirebaseAuth.getInstance().signInWithEmailAndPassword(Email, Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    dialog.dismiss();
                                    startActivity(new Intent(getApplicationContext(), Home.class));
                                    finish();
                                } else {
                                    dialog.dismiss();
                                    Toast.makeText(login.this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    private Boolean checkadmin()
    {
        if (Email.equals("admin@gmail.com"))
        {
            startActivity(new Intent(getApplicationContext(),Admin.class));
            finish();
            return false;
        }
        else
        {
            return true;
        }
    }
    private void setUI()
    {
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        login = findViewById(R.id.login);
        signin = findViewById(R.id.signin);

        dialog = new ProgressDialog(this);
        dialog.setMessage("PLease wait while you are authenticated..");
    }

    private void getValues()
    {
        Email = email.getText().toString();
        Pass = pass.getText().toString();
    }

    private Boolean validate()
    {
        if(Email.isEmpty() || Pass.isEmpty())
        {
            Toast.makeText(this, "All Fields have to be filled", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            return true;
        }

    }
}
