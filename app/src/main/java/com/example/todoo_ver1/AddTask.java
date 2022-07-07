package com.example.todoo_ver1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTask extends AppCompatActivity {
    private EditText et_title,et_description;
    private Button btn_addTask;
    private  DatabaseHelper databaseHelper;
    final Calendar myCalendar= Calendar.getInstance();
    private TextView tv_taskdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        databaseHelper = new DatabaseHelper(AddTask.this);

        et_title=findViewById(R.id.et_title);
        et_description=findViewById(R.id.et_description);
        btn_addTask=findViewById(R.id.btn_addTask);
        tv_taskdate=findViewById(R.id.tv_taskdate);

        btn_addTask.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String strTitle=et_title.getText().toString();
                String strDescription=et_description.getText().toString();
                String strTaskDate=tv_taskdate.getText().toString();

                if(strTitle.isEmpty()){
                    Toast.makeText(AddTask.this, "Please enter task details.", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    databaseHelper.addNewTask(strTitle,strDescription,strTaskDate);
                    Toast.makeText(AddTask.this, "Task has been added.", Toast.LENGTH_SHORT).show();
                    //  viewData();
                }

            }
        });

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();

            }
        };
        tv_taskdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddTask.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }
    private void updateLabel() {
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        tv_taskdate.setText(dateFormat.format(myCalendar.getTime()));
        tv_taskdate=findViewById(R.id.tv_taskdate);
    }
}