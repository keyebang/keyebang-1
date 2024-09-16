package com.shg.keyebang.view.activity.coursedetail.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shg.keyebang.R;
import com.shg.keyebang.aatools.DisplayUtil;
import com.shg.keyebang.aatools.TimeCNUtil;
import com.shg.keyebang.model.ViewCourseSelect;
import com.shg.keyebang.model.ViewCourseTime;
import com.shg.keyebang.view.activity.coursedetail.CourseDetailActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.ContentValues.TAG;

public class CourseSelectListAdapter extends RecyclerView.Adapter<CourseSelectListAdapter.ListItemHolder> {
    private ArrayList<ViewCourseSelect> courseSelects;
    private final CourseDetailActivity activity;

    public static class ListItemHolder extends RecyclerView.ViewHolder {
        private String courseId;
        private final TextView courseSelectMessage;
        private final TextView timeMessage;

        ListItemHolder(View view, CourseDetailActivity activity) {
            super(view);
            courseSelectMessage = view.findViewById(R.id.courseSelectMessage);
            timeMessage = view.findViewById(R.id.timeMessage);
            ImageView addCourse = view.findViewById(R.id.addCourse);

            addCourse.setOnClickListener(v->{
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setMessage("确定要添加此课程到你的课程表吗？");
                builder.setPositiveButton("是的", (v1, i1)->{
                    activity.addCourseToTable(courseId);
                });
                builder.setNegativeButton("取消", (v2, i2)->{});
                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.getWindow().setLayout(DisplayUtil.dpToPx(360), LinearLayout.LayoutParams.WRAP_CONTENT);
            });
        }
    }

    public CourseSelectListAdapter(CourseDetailActivity activity){
        this.activity = activity;
        courseSelects = new ArrayList<>();
    }

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course_select, parent, false), activity);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {
        ViewCourseSelect courseSelect = courseSelects.get(position);
        holder.courseId = courseSelect.getCourseId();
        holder.courseSelectMessage.setText(courseSelect.getTeacher() + "  " + courseSelect.getCoursePlace());
        StringBuilder s = new StringBuilder();
        for(ViewCourseTime time : courseSelect.getCourseTimes()){
            String t = getSingleOrDoubleCN(time.getSingleOrDouble());
            Log.d(TAG, "onBindViewHolder: " + time.getSingleOrDouble());
            s.append("周").append(TimeCNUtil.weekdayToCN(time.getWeekday() + 1)).append(time.getFirstClass()).append("-").append(time.getLastClass()).append(" ").append(t).append("  ");
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

    private String getSingleOrDoubleCN(int i){
        switch (i){
            case 1: return "单";
            case 2: return "双";
            default: return "";
        }
    }
}
