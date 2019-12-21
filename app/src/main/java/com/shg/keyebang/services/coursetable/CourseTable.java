package com.shg.keyebang.services.coursetable;

public class CourseTable {


    /*public static void setClass(final ViewCourse course , CourseTableListener listener){

        User user= BmobUser.getCurrentUser(User.class);
        course.setStudent(user);//关联到user类

        course.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {

                if (e == null) { listener.onSuccess("添加数据成功" +s);}
                else{listener.onFailure("添加数据失败：" + e.getErrorCode()+ "-" + e.getMessage() + "\n");}
            }

        });

    }

    public static void getClass(GetClassListener listener){
        BmobQuery<ViewCourse> query =new BmobQuery<>();
        query.addWhereEqualTo("student",BmobUser.getCurrentUser(User.class));
        query.include("student");
        query.setLimit(20);
        query.findObjects(new FindListener<ViewCourse>() {
            @Override
            public void done(List<ViewCourse> object, BmobException e) {
                if(e==null){

                    Map<ViewCourse, ViewTodo> classTable = new HashMap<>();
                    for (ViewCourse course : object) {
                        ViewCourse course1 = new ViewCourse(course.getCourseName(), course.getClassPlace(),course.getTeacher(),course.getOneOfWeekday(),course.getOneOfFirstClass(),course.getOneOfLastClass());
                        if(course.getTodoTitle()==null){classTable.put(course1, null);}
                        else{
                            Calendar calendar = new GregorianCalendar(course.getYear(),course.getMonth(), course.getDayOfMonth());
                            ViewTodo todo = new ViewTodo(course.getTodoTitle(),course.getTodoMessage(),calendar,ViewTodo.COLOR_RED);
                            classTable.put(course1,todo);
                        }

                    }
                    listener.onSuccess(classTable);
                }else{
                    listener.onFailure("查询失败"+e.getMessage()+e.getErrorCode());
                }
            }
        });
    }*/
}
