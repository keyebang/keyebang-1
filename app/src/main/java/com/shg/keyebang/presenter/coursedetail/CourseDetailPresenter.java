package com.shg.keyebang.presenter.coursedetail;

import com.shg.keyebang.aatools.StringUtil;
import com.shg.keyebang.model.ViewBook;
import com.shg.keyebang.model.ViewComment;
import com.shg.keyebang.model.ViewCourseInfo;
import com.shg.keyebang.model.ViewCourseSelect;
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

    public void getEvaId(String courseId) {
        String evaId = "SDfa";
        activity.setEvaId(evaId);
    }

    public void getCourseInfo(String courseId /* Or String evaluationId */){

        ViewCourseInfo viewCourseInfo = ViewCourseInfo.builder()
                .setType("必修课")
                .setCredit(3f)
                .setInfo("课程的主要内容");

        activity.setCourseInfoData(viewCourseInfo);
    }

    public void getThisCourseList(String courseId /* Or String evaluationId */){
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

        ViewCourseSelect viewCourseSelect1 = new ViewCourseSelect("1", "李华", "安楼101", times1);
        ViewCourseSelect viewCourseSelect2 = new ViewCourseSelect("2", "李明", "安楼101", times2);

        ArrayList<ViewCourseSelect> viewCourseSelects = new ArrayList<>();
        viewCourseSelects.add(viewCourseSelect1);
        viewCourseSelects.add(viewCourseSelect2);

        activity.setTimeData(viewCourseSelects);
    }

    public void getBookList(String evaId){
        ViewBook book = ViewBook.builder()
                .setBookName("《他改变了中国》");

        ArrayList<ViewBook> books = new ArrayList<>();
        books.add(book);
        books.add(book);

        activity.setBookData(books);
    }

    public void getCommentList(String evaId){
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

    public void getLikeNum(String evaId){
        int likenum = 123;
        activity.setLikeNum(likenum);
    }

    public void getIsLike(String evaId){
        boolean isLike = false;
        activity.setIsLike(isLike);
    }

    public void getLimit() {
        activity.setLimit(true);
    }

    public void sendComment(String evaId, String text) {
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

    public void addBook(String bookName, String evaId) {
    }

    public void addCourseToTable(String courseId) {
    }

    public void updateLike(String evaId, int num /* 1 or -1 */) {
        activity.updateLikeSuccess(num);
    }
}
