package com.shg.keyebang.view.activity.coursedetail.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shg.keyebang.R;
import com.shg.keyebang.model.ViewComment;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.ListItemHolder> {
    ArrayList<ViewComment> comments;

    static class ListItemHolder extends RecyclerView.ViewHolder {
        private TextView commentUserName;
        private TextView commentTime;
        private TextView commentMessage;

        ListItemHolder(View view) {
            super(view);
            commentUserName = view.findViewById(R.id.commentUserName);
            commentTime = view.findViewById(R.id.commentTime);
            commentMessage = view.findViewById(R.id.commentMessage);
        }
    }

    public CommentListAdapter() { comments = new ArrayList<>();}

    @NonNull
    @Override
    public CommentListAdapter.ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommentListAdapter.ListItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentListAdapter.ListItemHolder holder, int position) {
        ViewComment comment = comments.get(position);
        Calendar time = comment.getCommentTime();
        holder.commentUserName.setText(comment.getCommentUserName());
        holder.commentTime.setText(time.get(Calendar.MONTH) + "." + time.get(Calendar.DAY_OF_MONTH) + "." + time.get(Calendar.YEAR));
        holder.commentMessage.setText(comment.getCommentMessage());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public void setComments(ArrayList<ViewComment> comments) {
        this.comments = comments;
    }

    public void addComment(ViewComment comment){
        comments.add(0, comment);
    }
}