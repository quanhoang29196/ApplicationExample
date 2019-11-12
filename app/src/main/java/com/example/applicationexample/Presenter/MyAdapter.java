package com.example.applicationexample.Presenter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationexample.Activities.Product_Details;
import com.example.applicationexample.Model.BookModel;
import com.example.applicationexample.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<BookModel> bookModel;

    public MyAdapter(Context context, List<BookModel> bookModels) {
        this.context = context;
        this.bookModel = bookModels;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);

        return new MyAdapter.ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //fill the details
        ((ViewHolder) holder).author.setText(bookModel.get(position).getAuthor());
        ((ViewHolder) holder).title.setText(bookModel.get(position).getTitle());
        Picasso.with(holder.itemView.getContext())
                .load(bookModel.get(position).getImageURL())
                .into(((ViewHolder) holder).image);

        //set on click
        ((MyAdapter.ViewHolder) holder).setItemClickListener(new ItemClick() {
            @Override
            public void onItemClick(View v, int pos) {
                Intent intent = new Intent(context, Product_Details.class);
                intent.putExtra("title", bookModel.get(pos).getTitle());
                intent.putExtra("author", bookModel.get(pos).getAuthor());
                intent.putExtra("URL", bookModel.get(pos).getImageURL());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bookModel.size();
    }


    private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, author;
        ImageView image;
        private ItemClick itemClick;

        private ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            title = view.findViewById(R.id.Title);
            author = view.findViewById(R.id.Author);
            image = view.findViewById(R.id.Image);
        }


        @Override
        public void onClick(View v) {
            this.itemClick.onItemClick(v, getLayoutPosition());
        }

        private void setItemClickListener(ItemClick ic) {
            this.itemClick = ic;
        }
    }
}


