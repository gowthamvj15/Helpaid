package com.example.helpaid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {

    private Button logout,view_laws,add_query;
    private EditText query;
    private TextView username;
    String Roll_no,Name="",Query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setUI();
        getvalues();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),login.class));
                finish();
            }
        });
        add_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query = query.getText().toString();
                if (!Query.isEmpty())
                {
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("student queries");
                    Addquery obj = new Addquery(Roll_no,Name,Query);
                    reference.push().setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(Home.this, "Your Query have been Recorded.. ", Toast.LENGTH_SHORT).show();
                                query.setText("");
                            }
                            else
                            {
                                Toast.makeText(Home.this, "Cannot add query", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(Home.this, "Please enter an query..", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view_laws.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Userlaw.class));
            }
        });
    }


    private void setUI()
    {
        logout = findViewById(R.id.logout);
        view_laws = findViewById(R.id.view_laws);
        add_query = findViewById(R.id.query);
        query = findViewById(R.id.add_query);
        username = findViewById(R.id.username);
    }

    private void getvalues()
    {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for (DataSnapshot snapshot : dataSnapshot.getChildren())
               {
                   Name = snapshot.child("name").getValue().toString();
                   username.setText("Welcome "+snapshot.child("name").getValue().toString());
                   Roll_no = snapshot.child("roll_no").getValue().toString();
                   Log.d("values",Name);
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
