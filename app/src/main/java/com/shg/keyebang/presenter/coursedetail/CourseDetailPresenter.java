package com.shg.keyebang.presenter.coursedetail;

import com.shg.keyebang.aatools.StringUtil;
import com.shg.keyebang.model.ViewBook;
import com.shg.keyebang.model.ViewComment;
import com.shg.keyebang.model.ViewCourseInfo;
import com.shg.keyebang.model.ViewCourseSelect;
import com.shg.keyebang.model.User;
import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.services.account.AccountInfoService;
import com.shg.keyebang.services.account.SignUpLogInListener;
import com.shg.keyebang.services.coursedetail.AddDataListener;
import com.shg.keyebang.services.coursedetail.BookListListener;
import com.shg.keyebang.services.coursedetail.CommentListListener;
import com.shg.keyebang.services.coursedetail.CourseDetailService;
import com.shg.keyebang.services.coursedetail.GetCourseInfoListener;
import com.shg.keyebang.services.coursedetail.GetEvaIdListener;
import com.shg.keyebang.services.coursedetail.LikeNumListener;
import com.shg.keyebang.services.coursedetail.SendCommentListener;
import com.shg.keyebang.services.coursedetail.ThisCourseListListener;
import com.shg.keyebang.view.activity.coursedetail.CourseDetailActivity;

import java.util.ArrayList;

public class CourseDetailPresenter extends BasePresenter {
    private final CourseDetailActivity activity;

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

    public void getCourseInfo(String courseId){

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

    public void getThisCourseList(String evaId){
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
    }

    public void getBookList(String evaId){
        CourseDetailService.getBookList(evaId, new BookListListener() {
            @Override
            public void onSuccess(ArrayList<ViewBook> books) {
                activity.setBookData(books);
            }

            @Override
            public void onFailure(String errMassage) {
                activity.showErrorMessage(errMassage);
            }
        });
    }

    public void getCommentList(String evaId){
        CourseDetailService.getCommentList(evaId, new CommentListListener() {
            @Override
            public void onSuccess(ArrayList<ViewComment> viewComments) {
                activity.setCommentData(viewComments);
            }

            @Override
            public void onFailure(String errMassage) {
                activity.showErrorMessage(errMassage);
            }
        });
    }

    public void getLikeNum(String evaId){
        CourseDetailService.getLikeNum(evaId, new LikeNumListener() {
            @Override
            public void onSuccess(int likeNum) {
                activity.setLikeNum(likeNum);
            }

            @Override
            public void onFailure(String errMassage) {
                activity.showErrorMessage(errMassage);
            }
        });
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
                AccountInfoService.fetchUserInfo(new SignUpLogInListener() {
                    @Override
                    public void onSuccess(String message) {
                        if(User.getCurrentUser(User.class).getLimit())
                        getCommentList(evaId);
                        activity.setLimit(true);
                    }
                    @Override
                    public void onFailure(String errMassage) {
                        activity.showErrorMessage(errMassage);
                    }
                });
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

    public void updateLike(String evaId, int num) {
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


