package com.shg.keyebang.view.activity.coursedetail.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shg.keyebang.R;
import com.shg.keyebang.aatools.TimeCNUtil;
import com.shg.keyebang.model.ViewCourseSelect;
import com.shg.keyebang.model.ViewCourseTime;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CourseSelectListAdapter extends RecyclerView.Adapter<CourseSelectListAdapter.ListItemHolder> {
    ArrayList<ViewCourseSelect> courseSelects;

    static class ListItemHolder extends RecyclerView.ViewHolder {
        private String courseId;
        private TextView courseSelectMessage;
        private TextView timeMessage;
        private ImageView addCourse;

        ListItemHolder(View view) {
            super(view);
            courseSelectMessage = view.findViewById(R.id.courseSelectMessage);
            timeMessage = view.findViewById(R.id.timeMessage);
            addCourse = view.findViewById(R.id.addCourse);
        }
    }

    public CourseSelectListAdapter(){}

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course_select, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {
        ViewCourseSelect courseSelect = courseSelects.get(position);
        holder.courseId = courseSelect.getCourseId();
        holder.courseSelectMessage.setText(courseSelect.getTeacher() + "  " + courseSelect.getCoursePlace());
        StringBuilder s = new StringBuilder();
        for(ViewCourseTime time : courseSelect.getCourseTimes()){
            s.append("周").append(TimeCNUtil.weekdayToCN(time.getWeekday() + 1)).append(time.getFirstClass()).append("-").append(time.getLastClass()).append("  ");
        }
        holder.timeMessage.setText(s);
    }

    @Override
    public int getItemCount() {
        return courseSelects.size();
    }

    public void setCourseSelects(ArrayList<ViewCourseSelect> courseSelects) {
        this.courseSelects = courseSelects;
    }
}
