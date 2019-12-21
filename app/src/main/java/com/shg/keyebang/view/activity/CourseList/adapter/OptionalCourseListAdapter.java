package com.shg.keyebang.view.activity.CourseList.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shg.keyebang.R;
import com.shg.keyebang.aatools.TimeCNUtil;
import com.shg.keyebang.model.ViewCourse;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OptionalCourseListAdapter extends RecyclerView.Adapter<OptionalCourseListAdapter.ListItemHolder> {
    private ArrayList<ViewCourse> courses;

    public static class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView optionalCourseName;
        private TextView optionalCourseMessage;
        private String optionalCourseId;

        public ListItemHolder(View view) {
            super(view);
            optionalCourseName = view.findViewById(R.id.optionalCourseName);
            optionalCourseMessage = view.findViewById(R.id.optionalCourseMessage);
        }

        @Override
        public void onClick(View view) {

        }
    }

    public OptionalCourseListAdapter() {
        courses = new ArrayList<>();
    }

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course_optional, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {
        ViewCourse course = courses.get(position);
        holder.optionalCourseName.setText(course.getCourseName());
        holder.optionalCourseMessage.setText(course.getCampus() +  "·周" + TimeCNUtil.weekdayToCN(course.getOneOfWeekday() + 1) + course.getOneOfFirstClass() + "-" + course.getOneOfLastClass() + " · " + course.getCredit());
        holder.optionalCourseId = courses.get(position).getCourseId();
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

    public String getCourseTeacher(int position) {
        return courses.get(position).getTeacher();
    }
}