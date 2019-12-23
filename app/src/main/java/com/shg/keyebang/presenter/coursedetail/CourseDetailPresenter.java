package com.shg.keyebang.presenter.coursedetail;

import com.shg.keyebang.aatools.StringUtil;
import com.shg.keyebang.model.ViewBook;
import com.shg.keyebang.model.ViewComment;
import com.shg.keyebang.model.ViewCourseInfo;
import com.shg.keyebang.model.ViewCourseSelect;
import com.shg.keyebang.model.ViewCourseTime;
import com.shg.keyebang.model.User;
import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.services.coursedetail.AddDataListener;
import com.shg.keyebang.services.coursedetail.BookListListener;
import com.shg.keyebang.services.coursedetail.CommentListListener;
import com.shg.keyebang.services.coursedetail.CourseDetailService;
import com.shg.keyebang.services.coursedetail.GetCourseInfoListener;
import com.shg.keyebang.services.coursedetail.GetEvaIdListener;
import com.shg.keyebang.services.coursedetail.LikeNumListener;
import com.shg.keyebang.services.coursedetail.SendCommentListener;
import com.shg.keyebang.services.coursedetail.ThisCourseListListener;
import com.shg.keyebang.services.sqlite.SQLiteListener;
import com.shg.keyebang.services.sqlite.SetSQLCourseTable;
import com.shg.keyebang.view.activity.coursedetail.CourseDetailActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class CourseDetailPresenter extends BasePresenter {
    private CourseDetailActivity activity;

    public CourseDetailPresenter(CourseDetailActivity courseDetailActivity){
        this.activity = courseDetailActivity;
    }

    public void getEvaId(String courseId) {
        CourseDetailService.getEvaId(courseId, new GetEvaIdListener() {
            @Override
            public void onSuccess(String evaId) {
                activity.setEvaId(evaId);
            }

            @Override
            public void onFailure(String errMassage) {
                activity.showErrorMessage(errMassage);
            }
        });

    }

    public void getCourseInfo(String courseId /* Or String evaluationId */){

        CourseDetailService.getCourseInfo(courseId, new GetCourseInfoListener() {
            @Override
            public void onSuccess(ViewCourseInfo viewCourseInfo) {
                activity.setCourseInfoData(viewCourseInfo);
            }

            @Override
            public void onFailure(String errMassage) {
                activity.showErrorMessage(errMassage);
            }
        });
    }

    public void getThisCourseList(String evaId /* Or String evaluationId */){
        CourseDetailService.getThisCourseList(evaId, new ThisCourseListListener() {
            @Override
            public void onSuccess(ArrayList<ViewCourseSelect> viewCourseSelects) {
                activity.setTimeData(viewCourseSelects);
            }

            @Override
            public void onFailure(String errMassage) {
                activity.showErrorMessage(errMassage);
            }
        });
        /*ViewCourseTime time1 = ViewCourseTime.builder()
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
*/

    }

    public void getBookList(String evaId){
        CourseDetailService.getBookList(evaId, new BookListListener() {
            @Override
            public void onSuccess(ArrayList<ViewBook> books) {
                activity.setBookData(books);
            }

            @Override
            public void onFailure(String errMassage) {

            }
        });
        /*ViewBook book = ViewBook.builder()
                .setBookName("《他改变了中国》");

        ArrayList<ViewBook> books = new ArrayList<>();
        books.add(book);
        books.add(book);

        activity.setBookData(books);*/
    }

    public void getCommentList(String evaId){
        CourseDetailService.getCommentList(evaId, new CommentListListener() {
            @Override
            public void onSuccess(ArrayList<ViewComment> viewComments) {
                activity.setCommentData(viewComments);
            }

            @Override
            public void onFailure(String errMassage) {

            }
        });
        /*Calendar time = Calendar.getInstance();
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

        activity.setCommentData(comments);*/
    }

    public void getLikeNum(String evaId){
        CourseDetailService.getLikeNum(evaId, new LikeNumListener() {
            @Override
            public void onSuccess(int likeNum) {
                activity.setLikeNum(likeNum);
            }

            @Override
            public void onFailure(String errMassage) {

            }
        });
        /*int likenum = 123;
        activity.setLikeNum(likenum);*/
    }

    public void getIsLike(String evaId){
        boolean isLike = false;
        activity.setIsLike(isLike);
    }

    public void getLimit() {
        activity.setLimit(User.getCurrentUser(User.class).getLimit());
    }

    public void sendComment(String evaId, String text) {
        if(StringUtil.isAllNullOrEmpty(text)){
            activity.toastAndLog("评论内容为空");
            return;
        }
        CourseDetailService.sendComment(evaId, text, new SendCommentListener() {
            @Override
            public void onSuccess(String message) {
                getCommentList(evaId);
            }

            @Override
            public void onFailure(String errMassage) {
                activity.showErrorMessage(errMassage);
            }
        });

    }

    public void addBook(String bookName, String evaId) {
        CourseDetailService.addBook(bookName, evaId, new AddDataListener() {
            @Override
            public void onSuccess(String message) {
                getBookList(evaId);
            }

            @Override
            public void onFailure(String errMassage) {
                activity.showErrorMessage(errMassage);
            }
        });
    }

    public void addCourseToTable(String courseId) {
        activity.toastAndLog(courseId);
        CourseDetailService.addCourseToTable(courseId, new AddDataListener() {
            @Override
            public void onSuccess(String message) {
                activity.toastAndLog("添加成功");
            }

            @Override
            public void onFailure(String errMassage) {
                activity.showErrorMessage(errMassage);
            }
        });
    }

    public void updateLike(String evaId, int num /* 1 or -1 */) {
        CourseDetailService.updateLike(evaId, num, new AddDataListener() {
            @Override
            public void onSuccess(String message) {
                activity.updateLikeSuccess(num);
            }

            @Override
            public void onFailure(String errMassage) {
                activity.showErrorMessage(errMassage);
            }
        });
    }
}


