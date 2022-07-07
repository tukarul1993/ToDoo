package com.example.todoo_ver1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    private Context context;

    //--- Declare Arraylists
    private ArrayList title,description,IsTaskCompleted ,taskCompleteDate;

    public MyAdapter(Context context, ArrayList title,ArrayList description,ArrayList IsTaskCompleted,
                     ArrayList taskCompleteDate) {
        this.context = context;
        this.title = title;
        this.description=description;
        this.IsTaskCompleted=IsTaskCompleted;
        this.taskCompleteDate=taskCompleteDate;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.tasksentry,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //--- Set value to elements on VardView
        holder.tv_title.setText("Title: "+String.valueOf(title.get(position)));
        holder.tv_description.setText("Description: "+String.valueOf(description.get(position)));
        holder.chk_IsCompleted.setChecked(Boolean.parseBoolean(String.valueOf(IsTaskCompleted.get(position))));
        holder.tv_taskCompleteDate.setText("Target date: "+String.valueOf(taskCompleteDate.get(position)));
    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // Initialize CardView elements
        TextView tv_title,tv_description,tv_taskCompleteDate;
        CheckBox chk_IsCompleted;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title=itemView.findViewById(R.id.tv_title);
            tv_description=itemView.findViewById(R.id.tv_description);
            chk_IsCompleted=itemView.findViewById(R.id.chk_IsCompleted);
            tv_taskCompleteDate=itemView.findViewById(R.id.tv_taskCompleteDate);
        }


    }
}
