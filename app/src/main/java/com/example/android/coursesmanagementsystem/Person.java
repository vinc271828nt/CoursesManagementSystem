package com.example.android.coursesmanagementsystem;

/**
 * Created by Administrator on 2017/5/28.
 */

public class Person {
    private String mName;
    private String mSno;
    private CoursesList mCoursesList;
    public Person(String name,String sno){
        mName = name;
        mSno = sno;
        mCoursesList = new CoursesList();
    }
    public Person(String name, String sno,String courses){
        mName = name;
        mSno = sno;
        mCoursesList = new CoursesList(courses);
    }
    public Person(){
        mCoursesList = new CoursesList();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSno() {
        return mSno;
    }

    public void setSno(String sno) {
        mSno = sno;
    }

    public CoursesList getCoursesList() {
        return mCoursesList;
    }
    public  String getCoursesListString(){
        return mCoursesList.toString();
    }
    public void setCoursesList(CoursesList coursesList) {
        mCoursesList = coursesList;
    }
}
