package com.shg.keyebang.view.activity.coursedetail.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shg.keyebang.R;
import com.shg.keyebang.model.ViewBook;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CourseBookListAdapter extends RecyclerView.Adapter<CourseBookListAdapter.ListItemHolder> {
    private ArrayList<ViewBook> books;

    static class ListItemHolder extends RecyclerView.ViewHolder {
        private final TextView bookName;

        ListItemHolder(View view) {
            super(view);
            bookName = view.findViewById(R.id.bookName);
        }
    }

    public CourseBookListAdapter(){ books = new ArrayList<>();}

    @NonNull
    @Override
    public CourseBookListAdapter.ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CourseBookListAdapter.ListItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course_book, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CourseBookListAdapter.ListItemHolder holder, int position) {
        holder.bookName.setText(books.get(position).getBookName());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<ViewBook> books) {
        this.books = books;
    }
}
