package com.shg.keyebang.view.activity.courseList.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shg.keyebang.R;
import com.shg.keyebang.model.ViewCourse;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainCourseListAdapter extends RecyclerView.Adapter<MainCourseListAdapter.ListItemHolder> {
    private ArrayList<ViewCourse> courses;

    static class ListItemHolder extends RecyclerView.ViewHolder {
        private final TextView mainCourseName;

        ListItemHolder(View view) {
            super(view);
            mainCourseName = view.findViewById(R.id.mainCourseName);
        }
    }

    public MainCourseListAdapter() {
        courses = new ArrayList<>();
    }

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course_main, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {
        ViewCourse course = courses.get(position);
        holder.mainCourseName.setText(course.getCourseName());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public void setCourseList(ArrayList<ViewCourse> courses) {
        this.courses = courses;
    }

    public String getCourseId(int position){
        return courses.get(position).getCourseId();
    }

    public String getCourseName(int position){
        return courses.get(position).getCourseName();
    }
}