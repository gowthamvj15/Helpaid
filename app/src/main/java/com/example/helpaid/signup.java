package com.example.helpaid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText roll_no,name,pass,cpass,email;
    private Spinner dept;
    private Button signup;
    private  String Roll_no,Name,Pass,Cpass,Email,Dept;
    private DatabaseReference reference;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setUI();
        dept.setOnItemSelectedListener(this);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getValues();
                dialog.show();
                if (validate())
                {
                    if (FirebaseAuth.getInstance().signInWithEmailAndPassword(Email,Pass).isSuccessful())
                    {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Account aldready exists", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),login.class));
                        finish();
                    }
                    else
                    {
                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {
                                    Adduser obj = new Adduser(Roll_no,Name,Pass,Email,Dept);
                                    reference.child(FirebaseAuth.getInstance().getUid()).push().setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful())
                                            {
                                                dialog.dismiss();
                                                Toast.makeText(signup.this, "Sign in Success", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(),Home.class));
                                                finish();
                                            }
                                        }
                                    });
                                }
                                else
                                {
                                    Toast.makeText(signup.this, "Signup failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            }
        });
    }


    private void setUI()
    {
        roll_no = findViewById(R.id.roll_no);
        pass = findViewById(R.id.pass);
        cpass = findViewById(R.id.cpass);
        email = findViewById(R.id.email);
        name = findViewById(R.id.name);

        signup = findViewById(R.id.signin);

        dept = findViewById(R.id.dept);

        reference = FirebaseDatabase.getInstance().getReference("Users");

        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait while we authenticate you...");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Dept = parent.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private Boolean validate()
    {
        if (Email.isEmpty() || Roll_no.isEmpty() || Name.isEmpty() || Pass.isEmpty() || Cpass.isEmpty() || Dept.isEmpty())
        {
            Toast.makeText(this, "All Fields have to filled", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!Pass.equals(Cpass))
        {
            Toast.makeText(this, "Passwords does'nt match", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            return true;
        }
    }

    private void getValues()
    {
        Email = email.getText().toString();
        Pass = pass.getText().toString();
        Cpass = cpass.getText().toString();
        Name = name.getText().toString();
        Roll_no = roll_no.getText().toString();
    }
}
