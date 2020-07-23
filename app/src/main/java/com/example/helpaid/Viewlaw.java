package com.example.helpaid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class Viewlaw extends AppCompatActivity {

    private TextView title;
    private EditText desc;
    private Button update,delete;
    String Title,Desc,path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewlaw);

        setui();
        getpath();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Laws").child(Title).child(path);
                Addlaw addlaw  = new Addlaw(Title,desc.getText().toString());
                reference.setValue(addlaw).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(Viewlaw.this, "update successfull", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(Viewlaw.this, "update failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Laws").child(Title).child(path);
                reference.setValue(null).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(Viewlaw.this, "Law deleted successfully", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(Viewlaw.this, "Deletion Failure", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Laws.class));
                            finish();
                        }
                    }
                });
            }
        });
    }

    private void setui()
    {
        title = findViewById(R.id.title);
        desc = findViewById(R.id.desc);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);

        Title = getIntent().getStringExtra("law_title");
        Desc = getIntent().getStringExtra("law_desc");

        title.setText(Title);
        desc.setText(Desc);
    }

    private void getpath()
    {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Laws").child(Title);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    path = snapshot.getKey();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
