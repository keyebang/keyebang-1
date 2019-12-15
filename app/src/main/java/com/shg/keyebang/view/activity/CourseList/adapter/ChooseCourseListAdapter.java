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

public class ChooseCourseListAdapter extends RecyclerView.Adapter<ChooseCourseListAdapter.ListItemHolder> {
    private ArrayList<Course> courses;

    public static class ListItemHolder extends RecyclerView.ViewHolder {
        private TextView chooseCourseName;
        private TextView chooseCourseMessage;

        public ListItemHolder(View view) {
            super(view);
            chooseCourseName = view.findViewById(R.id.chooseCourseName);
            chooseCourseMessage = view.findViewById(R.id.chooseCourseMessage);
        }
    }

    public ChooseCourseListAdapter() {
        courses = new ArrayList<>();
    }

    @NonNull
    @Override
    public ChooseCourseListAdapter.ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChooseCourseListAdapter.ListItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course_choose, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseCourseListAdapter.ListItemHolder holder, int position) {
        Course course = courses.get(position);
        holder.chooseCourseName.setText(course.getClassName());
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
}