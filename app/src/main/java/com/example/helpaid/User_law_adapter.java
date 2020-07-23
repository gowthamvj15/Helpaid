package com.example.helpaid;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class User_law_adapter extends RecyclerView.Adapter<User_law_adapter.ViewHolder> {

    private Context context;
    private ArrayList<Addlaw> list;

    public User_law_adapter(Context context, ArrayList<Addlaw> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public User_law_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.law_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull User_law_adapter.ViewHolder holder, final int position) {
        holder.title.setText(list.get(position).getLaw_title());
        holder.desc.setText(list.get(position).getLaw_desc());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,User_View_law.class);
                intent.putExtra("title",list.get(position).getLaw_title());
                intent.putExtra("desc",list.get(position).getLaw_desc());
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
        public TextView title,desc;
        public LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.linear);
            title = itemView.findViewById(R.id.title);
            desc =  itemView.findViewById(R.id.law_desc);
        }
    }

    public void updatelist(ArrayList<Addlaw> list1)
    {
        list = new ArrayList<>();
        list.addAll(list1);
        notifyDataSetChanged();
    }
}
