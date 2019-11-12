package com.example.applicationexample.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationexample.Model.BookModel;
import com.example.applicationexample.Presenter.MyAdapter;
import com.example.applicationexample.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Products extends AppCompatActivity {

    RecyclerView mRecyclerView;
    BookModel bookModel;
    List<BookModel> bookModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recyclerview
        mRecyclerView = findViewById(R.id.Recyclerview);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(Products.this, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        //get book from json to POJO
        getData();

        //to recyclerview
        MyAdapter myAdapter = new MyAdapter(Products.this, bookModelList);
        mRecyclerView.setAdapter(myAdapter);


    }

    public void getData() {
        String json = null;
        try {
            InputStream is = getAssets().open("data/data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        bookModel = new Gson().fromJson(json, BookModel.class);
        bookModelList = new ArrayList<>();
    }
}
