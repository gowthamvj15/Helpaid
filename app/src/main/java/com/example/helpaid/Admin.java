package com.example.helpaid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Admin extends AppCompatActivity {

    private EditText title,desc;
    private Button add_law,view_query,view_laws,logout;
    private String Law_title,Law_desc;
    private DatabaseReference reference;
    private ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        setUI();
        get_data();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),login.class));
                finish();
            }
        });
        view_laws.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Laws.class));
            }
        });
        add_law.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getvalues();
                if (validate() && validate2())
                {
                    Addlaw obj = new Addlaw(Law_title,Law_desc);
                    reference.child(Law_title).push().setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(Admin.this, "law added Successfully", Toast.LENGTH_SHORT).show();
                                title.setText("");
                                desc.setText("");
                            }
                            else
                            {
                                Toast.makeText(Admin.this, "Failed..", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        view_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),View_queries.class));
            }
        });
    }

    private void getvalues()
    {
        Law_title = title.getText().toString();
        Law_desc = desc.getText().toString();
    }
    private void setUI()
    {
        title = findViewById(R.id.lawtitle);
        desc = findViewById(R.id.law_desc);
        logout = findViewById(R.id.logout);

        add_law = findViewById(R.id.add);
        view_laws = findViewById(R.id.view_laws);
        view_query = findViewById(R.id.view_queries);

        reference = FirebaseDatabase.getInstance().getReference("Laws");

        list = new ArrayList<>();
    }

    private Boolean validate()
    {
        if(Law_title.isEmpty() || Law_desc.isEmpty())
        {
            Toast.makeText(this, "All fields have to be filled", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            return true;
        }
    }

    private Boolean validate2()
    {
        Boolean bool = true;
        for (String i : list)
        {
            if (i.equals(Law_title))
            {
                Toast.makeText(this, "Title aldready exists", Toast.LENGTH_SHORT).show();
                bool = false;
            }
        }

        return bool;
    }
    private void get_data()
    {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Laws");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    list.add(snapshot.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
