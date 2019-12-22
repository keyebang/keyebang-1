package com.shg.keyebang.view.activity.coursedetail.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shg.keyebang.R;
import com.shg.keyebang.model.ViewBook;
import com.shg.keyebang.model.ViewSecondHandBook;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SecondHandBookListAdapter extends RecyclerView.Adapter<SecondHandBookListAdapter.ListItemHolder> {
    ArrayList<ViewSecondHandBook> secondHandBooks;

    static class ListItemHolder extends RecyclerView.ViewHolder {
        private TextView secondHandBookMessage;
        private TextView contactMessage;

        ListItemHolder(View view) {
            super(view);
            secondHandBookMessage = view.findViewById(R.id.secondHandBookMessage);
            contactMessage = view.findViewById(R.id.contactMessage);
        }
    }

    public SecondHandBookListAdapter(){ secondHandBooks = new ArrayList<>();}

    @NonNull
    @Override
    public SecondHandBookListAdapter.ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SecondHandBookListAdapter.ListItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_second_hand, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SecondHandBookListAdapter.ListItemHolder holder, int position) {
        holder.secondHandBookMessage.setText(secondHandBooks.get(position).getBookMessage());
        holder.contactMessage.setText(secondHandBooks.get(position).getContactMessage());
    }

    @Override
    public int getItemCount() {
        return secondHandBooks.size();
    }

    public void setBooks(ArrayList<ViewSecondHandBook> books) {
        this.secondHandBooks = books;
    }

    public void addSecondHandBook(ViewSecondHandBook book){
        secondHandBooks.add(0, book);
    }
}
