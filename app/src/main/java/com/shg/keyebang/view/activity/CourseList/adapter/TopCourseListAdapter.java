package com.shg.keyebang.view.activity.courseList.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shehuan.niv.NiceImageView;
import com.shg.keyebang.R;
import com.shg.keyebang.model.TopCourse;
import com.shg.keyebang.view.activity.courseList.OptionalCourseFragment;
import com.shg.keyebang.view.activity.coursedetail.CourseDetailActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TopCourseListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_TYPE_HEADER = 0;
    private static final int ITEM_TYPE_CONTENT = 1;
    private static final int NUM_HEADER = 1;

    private final OptionalCourseFragment fragment;
    private ArrayList<TopCourse> topCourses;

    static class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final OptionalCourseFragment fragment;
        private final NiceImageView topCourseImg;
        private final TextView topCourseName;
        private String courseName;
        private String courseId;

        ListItemHolder(View view, OptionalCourseFragment fragment){
            super(view);
            this.fragment = fragment;
            topCourseImg = view.findViewById(R.id.topCourseImg);
            topCourseName = view.findViewById(R.id.topCourseName);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(fragment.getActivity(), CourseDetailActivity.class);
            intent.putExtra("courseId",  courseId);
            intent.putExtra("courseName", courseName);
            fragment.startActivity(intent);
        }
    }

    static class HeaderHolder extends RecyclerView.ViewHolder{
        HeaderHolder(View view){
            super(view);
        }
    }

    public TopCourseListAdapter(OptionalCourseFragment fragment){
        this.fragment = fragment;
        topCourses = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == ITEM_TYPE_HEADER) {
            return new HeaderHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.header_course_top, parent,false));
        }
        return new ListItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course_top, parent,false), fragment);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        position -= NUM_HEADER;
        if(holder instanceof ListItemHolder) {
            ((ListItemHolder)holder).topCourseName.setText(topCourses.get(position).getCourseName());
            ((ListItemHolder)holder).topCourseImg.setImageDrawable(topCourses.get(position).getImg());
            ((ListItemHolder)holder).courseId = topCourses.get(position).getCourseId();
            ((ListItemHolder)holder).courseName = topCourses.get(position).getCourseName();
        }
    }

    @Override
    public int getItemCount() {
        return topCourses.size() + NUM_HEADER;
    }

    @Override
    public int getItemViewType(int position) {
        if(position < NUM_HEADER) return ITEM_TYPE_HEADER;
        else return ITEM_TYPE_CONTENT;
    }

    public void setTopCourses(ArrayList<TopCourse> topCourses) {
        this.topCourses = topCourses;
    }
}
