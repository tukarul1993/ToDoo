package com.example.todoo_ver1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewTasks extends AppCompatActivity {
    private  DatabaseHelper databaseHelper;
    RecyclerView recyclerView;
    ArrayList<String> title,description;    // To accept list of values from DB table
    ArrayList<String> IsTaskCompleted,taskCompleteDate;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tasks);

        databaseHelper = new DatabaseHelper(ViewTasks.this);

        // initialize Array list
        title=new ArrayList<>();
        description=new ArrayList<>();
        IsTaskCompleted=new ArrayList<>();
        taskCompleteDate=new ArrayList<>();

        recyclerView=findViewById(R.id.recyclerview);
        myAdapter=new MyAdapter(this,title,description,IsTaskCompleted,taskCompleteDate);

        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //--- Retrive data from table
        viewTasks();
        //--- Retrive data from table
    }

    //--- Retrive data from table
    private void viewTasks() {
        Cursor cursor=databaseHelper.viewData();
        if(cursor.getCount()==0)
            Toast.makeText(this,"No tasks to display",Toast.LENGTH_SHORT).show();
        else{
            while(cursor.moveToNext()){
                Log.d("varc",cursor.getString(1));

                //add cursor values to Array lists
                title.add(cursor.getString(1));
                description.add(cursor.getString(2));
                taskCompleteDate.add(cursor.getString(3));
                IsTaskCompleted.add(cursor.getString(5));
                //taskCompleteDate.add(cursor.getString(3));


                // Added by Ganesh

            }

        }
    }
}