package com.example.applicationexample.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.applicationexample.R;
import com.squareup.picasso.Picasso;

public class Product_Details extends AppCompatActivity {

    private ImageView imageView;
    private TextView authorDetail, titleDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__details);

        imageView = findViewById(R.id.imageDetail);
        authorDetail = findViewById(R.id.authorDetail);
        titleDetail = findViewById(R.id.titleDetail);

        //set text
        String author = getIntent().getStringExtra("author");
        authorDetail.setText(author);
        String  title = getIntent().getStringExtra("title");
        titleDetail.setText(title);

        //set image
        String url = getIntent().getStringExtra(("URL"));
        Picasso.with(this)
                .load(url)
                .into(imageView);
    }
}
