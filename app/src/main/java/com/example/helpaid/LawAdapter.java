package com.example.helpaid;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class LawAdapter extends RecyclerView.Adapter<LawAdapter.ViewHolder> {
    private ArrayList<Addlaw> list;
    private Context context;

    public LawAdapter(Context context,@NonNull ArrayList<Addlaw> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public LawAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.law_layout,parent,false);
        return new LawAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LawAdapter.ViewHolder holder, final int position) {
        holder.title.setText(list.get(position).getLaw_title());
        holder.desc.setText(list.get(position).getLaw_desc());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Viewlaw.class);
                intent.putExtra("law_title",list.get(position).getLaw_title());
                intent.putExtra("law_desc",list.get(position).getLaw_desc());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView desc;
        public TextView title;
        public LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            desc =  (TextView) itemView.findViewById(R.id.law_desc);
            layout = (LinearLayout) itemView.findViewById(R.id.linear);
        }
    }
}
