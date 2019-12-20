package com.shg.keyebang.view.activity.CourseList.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shg.keyebang.R;
import com.shg.keyebang.model.Course;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemCourseListAdapter extends RecyclerView.Adapter<ItemCourseListAdapter.ListItemHolder> {
    private ArrayList<Course> courses;

    static class ListItemHolder extends RecyclerView.ViewHolder {
        private TextView itemCourseName;
        private TextView itemCourseMessage;

        ListItemHolder(View view) {
            super(view);
            itemCourseName = view.findViewById(R.id.itemCourseName);
            itemCourseMessage = view.findViewById(R.id.itemCourseMessage);
        }
    }

    public ItemCourseListAdapter() {
        courses = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemCourseListAdapter.ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemCourseListAdapter.ListItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCourseListAdapter.ListItemHolder holder, int position) {
        Course course = courses.get(position);
        holder.itemCourseName.setText(course.getClassName());
        holder.itemCourseMessage.setText(course.getTeacher());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public void setCourseList(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public String getCourseId(int position) {
        return courses.get(position).getObjectId();
    }

    public String getCourseName(int position) {
        return courses.get(position).getClassName();
    }

    public String getCourseTeacher(int position) {
        return courses.get(position).getTeacher();
    }
}