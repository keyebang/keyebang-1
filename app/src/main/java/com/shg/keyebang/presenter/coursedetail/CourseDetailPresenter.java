package com.shg.keyebang.presenter.coursedetail;

import com.shg.keyebang.aatools.StringUtil;
import com.shg.keyebang.model.ViewBook;
import com.shg.keyebang.model.ViewComment;
import com.shg.keyebang.model.ViewCourseTime;
import com.shg.keyebang.model.User;
import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.view.activity.coursedetail.CourseDetailActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class CourseDetailPresenter extends BasePresenter {
    private CourseDetailActivity activity;

    public CourseDetailPresenter(CourseDetailActivity courseDetailActivity){
        this.activity = courseDetailActivity;
    }

    public void getCourseTimeList(){
        ViewCourseTime time1 = ViewCourseTime.builder()
                .setWeekday(1)
                .setFirstClass(1)
                .setLastClass(2);
        ViewCourseTime time2 = ViewCourseTime.builder()
                .setWeekday(2)
                .setFirstClass(1)
                .setLastClass(2);
        ViewCourseTime time3 = ViewCourseTime.builder()
                .setWeekday(2)
                .setFirstClass(3)
                .setLastClass(4);
        ViewCourseTime time4 = ViewCourseTime.builder()
                .setWeekday(4)
                .setFirstClass(3)
                .setLastClass(4);

        ArrayList<ViewCourseTime> times1 = new ArrayList<>();
        times1.add(time1);
        times1.add(time2);

        ArrayList<ViewCourseTime> times2 = new ArrayList<>();
        times2.add(time3);
        times2.add(time4);

        ArrayList<ArrayList<ViewCourseTime>> times = new ArrayList<>();
        times.add(times1);
        times.add(times2);

        activity.setTimeData(times);
    }

    public void getBookList(){
        ViewBook book = ViewBook.builder()
                .setBookName("《他改变了中国》");

        ArrayList<ViewBook> books = new ArrayList<>();
        books.add(book);
        books.add(book);

        activity.setBookData(books);
    }

    public void getCommentList(){
        Calendar time = Calendar.getInstance();
        ViewComment comment = ViewComment.builder()
                .setCommentUserName("同济学生")
                .setCommentTime(time)
                .setCommentMessage("这门课可以有");

        ArrayList<ViewComment> comments = new ArrayList<>();
        comments.add(comment);
        comments.add(comment);
        comments.add(comment);
        comments.add(comment);
        comments.add(comment);
        comments.add(comment);

        activity.setCommentData(comments);
    }

    public void sendComment(String text) {
        if(StringUtil.isAllNullOrEmpty(text)){
            activity.toastAndLog("评论内容为空");
            return;
        }

        ViewComment comment = ViewComment.builder()
                .setCommentUserName(User.getCurrentUser(User.class).getNickname())
                .setCommentTime(Calendar.getInstance())
                .setCommentMessage(text);

        activity.addMyComment(comment);
    }

    public void getLimit() {
        activity.setLimit(true);
    }
}
