package com.shg.keyebang.view.activity.CourseList.adapter;

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

    public static class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView optionalCourseName;
        private String optionalCourseId;

        public ListItemHolder(View view) {
            super(view);
            optionalCourseName = view.findViewById(R.id.mainCourseName);
        }

        @Override
        public void onClick(View view) {

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
        holder.optionalCourseName.setText(course.getCourseName());
        holder.optionalCourseId = course.getCourseId();
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