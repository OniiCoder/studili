package com.example.perez.studili;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by Perez on 8/5/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    String[] task_titles, task_texts;

    public RecyclerAdapter(String[] task_titles, String[] task_texts){
        this.task_titles = task_titles;
        this.task_texts = task_texts;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_task_card,parent,false);

        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        String title = task_titles[position];
        int titleLength = title.length();

        String text = task_texts[position];
        int textLength = text.length();

            holder.task_title.setText(title);
            holder.task_text.setText(text);





    }

    @Override
    public int getItemCount() {
        return task_titles.length;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView task_title, task_text;


        public RecyclerViewHolder(View view){
            super(view);

            view.setOnClickListener(this);


            task_title = (TextView)view.findViewById(R.id.task_title);
            task_text = (TextView)view.findViewById(R.id.task_text);
        }

        @Override
        public void onClick(View v) {

        }

    }
}
