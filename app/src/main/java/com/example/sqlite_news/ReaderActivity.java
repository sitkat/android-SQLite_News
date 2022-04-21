package com.example.sqlite_news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class ReaderActivity extends AppCompatActivity {
    DBHelper databaseHelper;
    RecyclerView newsRecycler;
    ArrayList<NewsModel> list = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        databaseHelper = new DBHelper(this);

        newsRecycler = findViewById(R.id.recycler_news);
        newsRecycler.setHasFixedSize(true);



        layoutManager = new LinearLayoutManager(this);
        newsRecycler.setLayoutManager(layoutManager);


        Cursor res = databaseHelper.getDataNews();
        if(res.getCount() == 0){
            Toast.makeText(this,"Нет данных", Toast.LENGTH_SHORT).show();
            return;
        }

        while (res.moveToNext()){
            String name = res.getString(0);
            String description = res.getString(1);
            String date = res.getString(2);
            list.add(new NewsModel(name,description,date));
            Log.i("12312311", "onCreate: 123123");
        }

        newsRecycler.setAdapter(new NewsAdapter(getApplicationContext(), list));
    }
}