package com.shg.keyebang.services.coursedetail;

import com.shg.keyebang.aatools.IdUtil;
import com.shg.keyebang.model.User;
import com.shg.keyebang.model.ViewBook;
import com.shg.keyebang.model.ViewComment;
import com.shg.keyebang.model.ViewCourse;
import com.shg.keyebang.model.ViewCourseInfo;
import com.shg.keyebang.model.ViewCourseSelect;
import com.shg.keyebang.model.ViewCourseTime;
import com.shg.keyebang.services.datamodel.Book;
import com.shg.keyebang.services.datamodel.Comment;
import com.shg.keyebang.services.datamodel.Course;
import com.shg.keyebang.services.datamodel.CourseTime;
import com.shg.keyebang.services.datamodel.Evaluation;
import com.shg.keyebang.services.datamodel.Todo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class CourseDetailService {
    public static void getEvaId(String courseId,GetEvaIdListener listener){
        BmobQuery<Course> query1 = new BmobQuery<>();
        String courseId1=IdUtil.getCorrectId(courseId);
        query1.addWhereEqualTo("objectId",courseId1);
        query1.setLimit(100);
        query1.findObjects(new FindListener<Course>() {
            @Override
            public void done(List<Course> object, BmobException e) {
                if(e==null){
                    for(Course course:object){
                        String evaluationId=course.getEvaluationId().getObjectId();
                        listener.onSuccess(evaluationId);
                    }
                }else{listener.onFailure("查询失败"+e.getMessage()+e.getErrorCode());}
            }
        });
    }

    public static void getCourseInfo(String courseId /* Or String evaluationId */,GetCourseInfoListener listener){
        BmobQuery<Course> query1 = new BmobQuery<>();
        query1.addWhereEqualTo("objectId",courseId);
        query1.setLimit(100);
        query1.findObjects(new FindListener<Course>() {
            @Override
            public void done(List<Course> object, BmobException e) {
                if(e==null){
                    for(Course course:object){
                        BmobQuery<Evaluation>query2=new BmobQuery<>();
                        query2.addWhereEqualTo("objectId", course.getEvaluationId());
                        query2.findObjects(new FindListener<Evaluation>() {
                            @Override
                            public void done(List<Evaluation> object, BmobException e) {
                                if (e==null){
                                    for (Evaluation evaluation:object){
                                        ViewCourseInfo viewCourseInfo = ViewCourseInfo.builder()
                                                .setType(course.getType())
                                                .setCredit(course.getCredit())
                                                .setInfo(evaluation.getContent());
                                        listener.onSuccess(viewCourseInfo);
                                    }
                                }else{listener.onFailure("查询失败"+e.getMessage()+e.getErrorCode());}

                            }
                        });

                    }
                }else{listener.onFailure("查询失败"+e.getMessage()+e.getErrorCode());}

            }
        });
    }
    public static void getThisCourseList(String evaId ,ThisCourseListListener listener){
        String evaId1= IdUtil.getCorrectId(evaId);
        BmobQuery<Course> query = new BmobQuery<>();
        query.addWhereEqualTo("evaluationId",evaId1);
        query.findObjects(new FindListener<Course>() {
            @Override
            public void done(List<Course> object, BmobException e) {
                if (e==null){
                    ArrayList<ViewCourseSelect> viewCourseSelects = new ArrayList<>();
                    for (Course course:object){
                        BmobQuery<CourseTime> query1 = new BmobQuery<>();
                        query1.addWhereEqualTo("courseId",course.getObjectId());
                        query1.setLimit(100);
                        query1.findObjects(new FindListener<CourseTime>(){
                            @Override
                            public void done(List<CourseTime> object, BmobException e){
                                if(e==null){
                                    ArrayList<ViewCourseTime> times1 = new ArrayList<>();
                                    for(CourseTime courseTime:object){
                                        ViewCourseTime time1 = ViewCourseTime.builder()
                                                .setWeekday(courseTime.getWeekday())
                                                .setFirstClass(courseTime.getFirstClass())
                                                .setLastClass(courseTime.getLastClass())
                                                .setCourseId(course.getObjectId());
                                        times1.add(time1);
                                    }
                                    ViewCourseSelect viewCourseSelect1 = new ViewCourseSelect(course.getObjectId(), course.getTeacher(), course.getClassPlace(), times1);
                                    viewCourseSelects.add(viewCourseSelect1);
                                    listener.onSuccess(viewCourseSelects);
                                }else{listener.onFailure("查询失败"+e.getMessage()+e.getErrorCode());}
                            }
                        });
                    }
                }else{listener.onFailure("查询失败"+e.getMessage()+e.getErrorCode());}
            }
        });
    }

    public static void getBookList(String evaId,BookListListener listener){
        BmobQuery<Book> query1 = new BmobQuery<>();
        String evaId1= IdUtil.getCorrectId(evaId);
        query1.addWhereEqualTo("evaId",evaId1);
        query1.setLimit(100);
        query1.findObjects(new FindListener<Book>() {
            @Override
            public void done(List<Book> object, BmobException e) {
                if(e==null){
                    ArrayList<ViewBook> books = new ArrayList<>();
                    for(Book book:object){
                        ViewBook viewBook = ViewBook.builder()
                                .setBookName(book.getBookName());
                        books.add(viewBook);
                    }
                    listener.onSuccess(books);
                }
                else{listener.onFailure("查询失败"+e.getMessage()+e.getErrorCode());}

            }
        });
    }

    public static void getCommentList(String evaId,CommentListListener listener){
        String evaId1= IdUtil.getCorrectId(evaId);
        BmobQuery<Comment> query1 = new BmobQuery<>();
        query1.addWhereEqualTo("evaId",evaId1);
        query1.setLimit(100);
        query1.findObjects(new FindListener<Comment>() {
            @Override
            public void done(List<Comment> object, BmobException e) {
                if(e==null){
                    ArrayList<ViewComment> comments = new ArrayList<>();
                    for(Comment comment:object){
                        Calendar time = Calendar.getInstance();
                        BmobQuery<User> query2 = new BmobQuery<>();

                        query2.addWhereEqualTo("objectId", comment.getUserId());
                        query2.findObjects(new FindListener<User>() {
                            @Override
                            public void done(List<User> object, BmobException e) {
                                if(e==null){
                                    for(User user:object){
                                        ViewComment viewComment = ViewComment.builder()
                                                .setCommentUserName(user.getNickname())
                                                .setCommentTime(time)
                                                .setCommentMessage(comment.getCommentMessage());
                                        comments.add(viewComment);
                                    }
                                    listener.onSuccess(comments);
                                }else{listener.onFailure("查询失败"+e.getMessage()+e.getErrorCode());}
                            }
                        });

                    }

                }
                else{listener.onFailure("查询失败"+e.getMessage()+e.getErrorCode());}

            }
        });
    }
    public static void getLikeNum(String evaId,LikeNumListener listener){
        BmobQuery<Evaluation> query1=new BmobQuery<>();
        String evaId1= IdUtil.getCorrectId(evaId);
        query1.addWhereEqualTo("objectId",evaId1);
        query1.findObjects(new FindListener<Evaluation>() {
            @Override
            public void done(List<Evaluation> object, BmobException e) {
                if(e==null){
                    int likenum;
                    for(Evaluation evaluation :object){
                        likenum = evaluation.getLikes();
                        listener.onSuccess(likenum);
                    }

                }else{listener.onFailure("查询失败"+e.getMessage()+e.getErrorCode());}
            }
        });
    }


    public void getIsLike(String evaId){

    }

    public static void sendComment(String evaId, String text,SendCommentListener listener){
        Evaluation evaluation = new Evaluation();
        String evaId1= IdUtil.getCorrectId(evaId);
        evaluation.setObjectId(evaId1);
        final Comment comment=new Comment();
        comment.setEvaId(evaluation);
        comment.setUserId(User.getCurrentUser(User.class));
        comment.setCommentUserName(User.getCurrentUser(User.class).getNickname());
        comment.setCommentTime(Calendar.getInstance());
        comment.setCommentMessage(text);
        comment.update(new UpdateListener(){
            @Override
            public void done(BmobException e) {
                if(e==null){
                    listener.onSuccess("添加数据成功");
                }else{
                    listener.onFailure("创建数据失败：" + e.getMessage());
                }
            }
        });

    }

    public static void addBook(String bookName, String evaId,AddDataListener listener){
        String evaId1= IdUtil.getCorrectId(evaId);
        Evaluation evaluation = new Evaluation();
        evaluation.setObjectId(evaId1);
        final Book book =new Book();
        book.setEvaId(evaluation);
        book.setBookName(bookName);
        book.update(new UpdateListener(){
            @Override
            public void done(BmobException e) {
                if(e==null){
                    listener.onSuccess("添加数据成功");
                }else{
                    listener.onFailure("创建数据失败：" + e.getMessage());
                }
            }
        });
    }

    public static void addCourseToTable(String courseId,AddDataListener listener) {
        Course course=new Course();
        String courseId1= IdUtil.getCorrectId(courseId);
        course.setObjectId(courseId1);
        final Todo todo =new Todo();
        todo.setCourseId(course);
        todo.setTodoTitle(null);
        todo.setTodoMessage(null);
        todo.update(new UpdateListener(){
            @Override
            public void done(BmobException e) {
                if(e==null){
                    listener.onSuccess("添加数据成功");
                }else{
                    listener.onFailure("创建数据失败：" + e.getMessage());
                }
            }
        });
    }

    public static void updateLike(String evaId, int num /* 1 or -1 */,AddDataListener listener){
        Evaluation evaluation = new Evaluation();
        String evaId1= IdUtil.getCorrectId(evaId);
        evaluation.setObjectId(evaId1);
        if(num==1){
            evaluation.setLikes(evaluation.getLikes()+1);
        }else evaluation.setLikes(evaluation.getLikes()-1);
        evaluation.update(new UpdateListener(){
            @Override
            public void done(BmobException e) {
                if(e==null){
                    listener.onSuccess("添加数据成功");
                }else{
                    listener.onFailure("创建数据失败：" + e.getMessage());
                }
            }
        });

    }
}
