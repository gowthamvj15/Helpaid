package com.example.helpaid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Query_adapter extends RecyclerView.Adapter<Query_adapter.ViewHolder> {

    private Context context;
    private ArrayList<Addquery> list;

    public Query_adapter(Context context, ArrayList<Addquery> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Query_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.query_layout,parent,false);
        return new Query_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Query_adapter.ViewHolder holder, int position) {

        holder.roll_no.setText(list.get(position).getRoll_no());
        holder.name.setText(list.get(position).getName());
        holder.query.setText(list.get(position).getQuery());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView roll_no,name,query;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            roll_no = itemView.findViewById(R.id.roll_no);
            name = itemView.findViewById(R.id.name);
            query = itemView.findViewById(R.id.query);
        }
    }
}
