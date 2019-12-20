package com.shg.keyebang.presenter.coursedetail;

import com.shg.keyebang.aatools.StringUtil;
import com.shg.keyebang.model.Book;
import com.shg.keyebang.model.Comment;
import com.shg.keyebang.model.CourseTime;
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
        CourseTime time1 = CourseTime.builder()
                .setWeekday(1)
                .setFirstClass(1)
                .setLastClass(2);
        CourseTime time2 = CourseTime.builder()
                .setWeekday(2)
                .setFirstClass(1)
                .setLastClass(2);
        CourseTime time3 = CourseTime.builder()
                .setWeekday(2)
                .setFirstClass(3)
                .setLastClass(4);
        CourseTime time4 = CourseTime.builder()
                .setWeekday(4)
                .setFirstClass(3)
                .setLastClass(4);

        ArrayList<CourseTime> times1 = new ArrayList<>();
        times1.add(time1);
        times1.add(time2);

        ArrayList<CourseTime> times2 = new ArrayList<>();
        times2.add(time3);
        times2.add(time4);

        ArrayList<ArrayList<CourseTime>> times = new ArrayList<>();
        times.add(times1);
        times.add(times2);

        activity.setTimeData(times);
    }

    public void getBookList(){
        Book book = Book.builder()
                .setBookName("《他改变了中国》");

        ArrayList<Book> books = new ArrayList<>();
        books.add(book);
        books.add(book);

        activity.setBookData(books);
    }

    public void getCommentList(){
        Calendar time = Calendar.getInstance();
        Comment comment = Comment.builder()
                .setCommentUserName("同济学生")
                .setCommentTime(time)
                .setCommentMessage("这门课可以有");

        ArrayList<Comment> comments = new ArrayList<>();
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

        Comment comment = Comment.builder()
                .setCommentUserName(User.getCurrentUser(User.class).getNickname())
                .setCommentTime(Calendar.getInstance())
                .setCommentMessage(text);

        activity.addMyComment(comment);
    }
}
