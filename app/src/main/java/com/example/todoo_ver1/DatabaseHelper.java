package com.example.todoo_ver1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatabaseHelper   extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "DB_ToDoo", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //--- Create table in database if not present
        String query =
                "create table Tbl_ToDo_Tasks(" +
                        "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "Title TEXT, " +
                        "TaskDescription TEXT," +
                        "TaskCompletionDate TEXT," +
                        "TaskCreatedDate TEXT," + "IsTaskCompleted TEXT  )";
        // method to execute above sql query
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //---Drop table if it exists
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "Tbl_ToDo_Tasks");
        onCreate(sqLiteDatabase);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addNewTask(String title, String description, String taskDate){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        Log.d("ADebugTag", "Value: " + taskDate);

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Title",title);
        values.put("TaskDescription",description);
        values.put("TaskCreatedDate",dtf.format(now));
        values.put("TaskCompletionDate", taskDate);
        values.put("IsTaskCompleted","false");  // Initially inserted as 0:incomplete task

        db.insert("Tbl_ToDo_Tasks", null, values);
        db.close();
    }

    //--- Retrive data from table
    public Cursor viewData(){
        SQLiteDatabase db=this.getReadableDatabase();

        String query="select Id,Title,TaskDescription,TaskCompletionDate,TaskCreatedDate,ifnull(IsTaskCompleted,0)as IsTaskCompleted From Tbl_ToDo_Tasks";
        Cursor cursor=db.rawQuery(query,null);
        return cursor;
    }
}
