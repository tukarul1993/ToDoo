package com.example.todoo_ver1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn_addData,btn_viewData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_addData=findViewById(R.id.btn_addData);
        btn_viewData=findViewById(R.id.btn_viewData);

        //--- Add data button action
        btn_addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityAddTask = new Intent(getApplicationContext(), AddTask.class);
                startActivity(activityAddTask);
            }
        });

        //--- View Data button action
        btn_viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityAddTask = new Intent(getApplicationContext(), ViewTasks.class);
                startActivity(activityAddTask);
            }
        });

    }
}