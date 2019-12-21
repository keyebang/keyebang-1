package com.shg.keyebang.view.activity.coursedetail.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shg.keyebang.R;
import com.shg.keyebang.aatools.TimeCNUtil;
import com.shg.keyebang.model.ViewCourseTime;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CourseTimeListAdapter extends RecyclerView.Adapter<CourseTimeListAdapter.ListItemHolder> {
    ArrayList<ArrayList<ViewCourseTime>> courseTimes;

    static class ListItemHolder extends RecyclerView.ViewHolder {
        private TextView timeMessage;
        private ImageView addCourse;

        ListItemHolder(View view) {
            super(view);
            timeMessage = view.findViewById(R.id.timeMessage);
            addCourse = view.findViewById(R.id.addCourse);
        }
    }

    public CourseTimeListAdapter(){}

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course_time, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {
        String s = "";
        for(ViewCourseTime time : courseTimes.get(position)){
            s += "周" + TimeCNUtil.weekdayToCN(time.getWeekday() + 1) + time.getFirstClass() + "-" + time.getLastClass() + "  ";
        }
        holder.timeMessage.setText(s);
    }

    @Override
    public int getItemCount() {
        return courseTimes.size();
    }

    public void setCourseTimes(ArrayList<ArrayList<ViewCourseTime>> courseTimes) {
        this.courseTimes = courseTimes;
    }
}
