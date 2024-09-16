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
import com.shg.keyebang.services.datamodel.Likes;
import com.shg.keyebang.services.datamodel.Todo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
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
                        String evaluationId=course.getEvaId();
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
                        query2.addWhereEqualTo("objectId", course.getEvaId());
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
        query.addWhereEqualTo("evaId",evaId1);
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
                                                .setSingleOrDouble(courseTime.getWeekTime())
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
                        Calendar time = comment.getCommentTime();
                        Comment comment1=new Comment();
                        comment1.setYear(time.get(Calendar.YEAR));
                        comment1.setDayOfMonth(time.get(Calendar.DAY_OF_MONTH)+1);
                        comment1.setMonth(time.get(Calendar.MONTH));
                        Calendar time1=comment1.getCommentTime();
                        BmobQuery<User> query2 = new BmobQuery<>();

                        query2.addWhereEqualTo("objectId", comment.getUserId());
                        query2.findObjects(new FindListener<User>() {
                            @Override
                            public void done(List<User> object, BmobException e) {
                                if(e==null){
                                    for(User user:object){
                                        ViewComment viewComment = ViewComment.builder()
                                                .setCommentUserName(user.getNickname())
                                                .setCommentTime(time1)
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


    public static void getIsLike(String evaId,IsLikeListener listener){
        BmobQuery<Likes> query=new BmobQuery<>();
        query.addWhereEqualTo("userId",User.getCurrentUser(User.class).getObjectId());
        query.findObjects(new FindListener<Likes>() {
            @Override
            public void done(List<Likes> object, BmobException e) {
                if (e==null){
                    for (Likes likes :object){
                        BmobQuery<Likes> query1=new BmobQuery<>();
                        query1.addWhereEqualTo("evaId",evaId);
                        query1.findObjects(new FindListener<Likes>() {
                            @Override
                            public void done(List<Likes> object, BmobException e) {
                                if (e==null){
                                    boolean islike;
                                    for (Likes likes1:object){
                                        islike=likes1.isLike();
                                        listener.onSuccess(islike);
                                    }
                                }else{listener.onFailure("查询失败"+e.getMessage()+e.getErrorCode());}
                            }
                        });
                    }
                }else{listener.onFailure("查询失败"+e.getMessage()+e.getErrorCode());}

            }
        });
    }

    public static void sendComment(String evaId, String text,SendCommentListener listener){
        Evaluation evaluation = new Evaluation();
        String evaId1= IdUtil.getCorrectId(evaId);
        evaluation.setObjectId(evaId1);
        final Comment comment=new Comment();
        final User user= new User();
        user.setObjectId(User.getCurrentUser(User.class).getObjectId());
        user.setLimit(true);
        comment.setEvaId(evaId1);
        comment.setUserId(User.getCurrentUser(User.class).getObjectId());
        comment.setCommentUserName(User.getCurrentUser(User.class).getNickname());
        Calendar calendar = Calendar.getInstance();
        comment.setYear(calendar.get(Calendar.YEAR));
        comment.setMonth(calendar.get(Calendar.MONTH)+1);
        comment.setDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH));
        comment.setCommentTime(Calendar.getInstance());
        comment.setCommentMessage(text);
        comment.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    listener.onSuccess("添加数据成功");
                }else{
                    listener.onFailure("创建数据失败：" + e.getMessage());
                }
            }
        });

        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    listener.onSuccess("更新数据成功");
                }else{
                    listener.onFailure("更新数据失败：" + e.getMessage());
                }
            }
        });

    }

    public static void addBook(String bookName, String evaId,AddDataListener listener){
        String evaId1= IdUtil.getCorrectId(evaId);
        Evaluation evaluation = new Evaluation();
        evaluation.setObjectId(evaId1);
        final Book book =new Book();
        book.setEvaId(evaId1);
        book.setBookName(bookName);
        book.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
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
        todo.setCourseId(courseId1);
        todo.setUserId(BmobUser.getCurrentUser(User.class).getObjectId());
        todo.setTodoTitle(null);
        todo.setTodoMessage(null);
        todo.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    listener.onSuccess("添加数据成功");
                }else{
                    listener.onFailure("创建数据失败：" + e.getMessage());
                }
            }
        });

    }

    public static void updateLike(String evaId, int num /* 1 or -1 */,AddDataListener listener){
        BmobQuery<Evaluation> query=new BmobQuery<>();
        Evaluation evaluation = new Evaluation();
        Likes likes =new Likes();
        String evaId1= IdUtil.getCorrectId(evaId);
        query.addWhereEqualTo("objectId",evaId1);
        query.findObjects(new FindListener<Evaluation>() {
            @Override
            public void done(List<Evaluation> object, BmobException e) {
                if (e==null){

                    for (Evaluation evaluation1 :object){
                        evaluation.setObjectId(evaId1);
                        if(num==-1){
                            evaluation.setLikes(evaluation1.getLikes()-1);
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
                            BmobQuery<Likes> query1=new BmobQuery<>();
                            query1.addWhereEqualTo("evaId",evaId);
                            query1.findObjects(new FindListener<Likes>() {
                                @Override
                                public void done(List<Likes> object, BmobException e) {
                                    if (e==null){

                                        for (Likes likes1:object){
                                            likes.setObjectId(likes1.getObjectId());
                                            likes.setLike(false);
                                            likes.update(new UpdateListener() {
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
                                    }else{listener.onFailure("查询失败"+e.getMessage()+e.getErrorCode());}
                                }
                            });
                        }else {
                            evaluation.setLikes(evaluation1.getLikes()+1);
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
                            BmobQuery<Likes> query1=new BmobQuery<>();
                            query1.addWhereEqualTo("evaId",evaId);
                            query1.findObjects(new FindListener<Likes>() {
                                @Override
                                public void done(List<Likes> object, BmobException e) {
                                    if (e==null){
                                        for (Likes likes1:object){
                                            likes.setObjectId(likes1.getObjectId());
                                            likes.setLike(true);
                                            likes.update(new UpdateListener() {
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
                                    }else{listener.onFailure("查询失败"+e.getMessage()+e.getErrorCode());}
                                }
                            });
                        }
                    }
                    listener.onSuccess("添加数据成功");
                }else{ listener.onFailure("创建数据失败：" + e.getMessage());}
            }
        });
    }
}
