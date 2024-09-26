package com.shg.keyebang.view.activity.courseList.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shg.keyebang.R;
import com.shg.keyebang.aatools.TimeCNUtil;
import com.shg.keyebang.model.ViewCourse;
import com.shg.keyebang.model.ViewCourseTime;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemCourseListAdapter extends RecyclerView.Adapter<ItemCourseListAdapter.ListItemHolder> {
    private ArrayList<ViewCourse> courses;

    static class ListItemHolder extends RecyclerView.ViewHolder {
        private final TextView itemCourseName;
        private final TextView itemCourseMessage;

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
        ViewCourse course = courses.get(position);
        holder.itemCourseName.setText(course.getCourseName());
        StringBuilder message  = new StringBuilder();
        message.append(course.getTeacher()).append(" · ");
        for(ViewCourseTime time : course.getCourseTimes()){
            String t = " " + getSingleOrDoubleCN(time.getSingleOrDouble());
            message.append("周").append(TimeCNUtil.weekdayToCN(time.getWeekday() + 1)).append(time.getFirstClass()).append("-").append(time.getLastClass()).append(t).append("  ");
        }
        holder.itemCourseMessage.setText(message.toString());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public void setCourseList(ArrayList<ViewCourse> courses) {
        this.courses = courses;
    }

    public String getCourseName(int position) {
        return courses.get(position).getCourseName();
    }

    public String getCourseId(int position) {
        return courses.get(position).getCourseId();
    }

    private String getSingleOrDoubleCN(int i){
        switch (i){
            case 1: return "单";
            case 2: return "双";
            default: return "";
        }
    }
}