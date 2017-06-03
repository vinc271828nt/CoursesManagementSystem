package com.example.android.coursesmanagementsystem;

import java.util.LinkedList;

/**
 * Created by Administrator on 2017/5/28.
 */

public class Course {
    String mCourseName;
    String mTeacher;
    LinkedList<ClassSchedule> mLinkedList;

    public Course() {
    }

    public Course(String s) {
        String[] ss = s.split("   ");
        mCourseName = ss[0];
        mTeacher = ss[1];
        this.mLinkedList = new LinkedList<ClassSchedule>();
        for(int i = 2;i < ss.length;i++)
            mLinkedList.add(new ClassSchedule(ss[i]));
    }

    public Course(String mCourseName, String mTeacher, int weekday, int classtime, String classroom, String range) {
        this.mCourseName = mCourseName;
        this.mTeacher = mTeacher;
        int[] temp = parseRange(range);
        this.mLinkedList = new LinkedList<ClassSchedule>();
        mLinkedList.add(new ClassSchedule(weekday, classtime, classroom, temp[0], temp[1]));
    }

    public Course(String coursename, String teacher, int weekday, int classtime, String classroom, String range, String parity) {
        this.mCourseName = coursename;
        this.mTeacher = teacher;
        int[] temp = parseRange(range);
        this.mLinkedList = new LinkedList<ClassSchedule>();
        mLinkedList.add(new ClassSchedule(weekday, classtime, classroom, temp[0], temp[1], parity));
    }

    public Course(LinkedList<String> l, int day, int time, String type) {
        this.mCourseName = l.get(0);
        this.mTeacher = l.get(1);
        this.mLinkedList = new LinkedList<ClassSchedule>();
        int[] t = parseRange(l.get(l.size() - 1));
        mLinkedList.add(new ClassSchedule(day, time, l.get(l.size() - 2), t[0], t[1], type));
    }

    public Course(LinkedList<String> l, int day, int time) {
        this.mCourseName = l.get(0);
        this.mTeacher = l.get(1);
        this.mLinkedList = new LinkedList<ClassSchedule>();
        int[] t = parseRange(l.get(l.size() - 1));
        mLinkedList.add(new ClassSchedule(day, time, l.get(l.size() - 2), t[0], t[1]));
    }

    private int[] parseRange(String range) {
        int[] temp = {0, 0};
        String[] s = range.split("-");
        temp[0] = Integer.valueOf(s[0]);
        temp[1] = Integer.valueOf(s[1]);
        return temp;
    }

    public String getmCourseName() {
        return mCourseName;
    }

    public void setmCourseName(String mCourseName) {
        this.mCourseName = mCourseName;
    }

    public String getmTeacher() {
        return mTeacher;
    }

    public void setmTeacher(String mTeacher) {
        this.mTeacher = mTeacher;
    }

    public int getListSize() {
        return mLinkedList.size();
    }

    public int getBeginWeek(int index) {
        if (index >= 0 && index < mLinkedList.size())
            return mLinkedList.get(index).getmBeginWeek();
        else
            return -1;
    }

    public int getEndWeek(int index) {
        if (index >= 0 && index < mLinkedList.size())
            return mLinkedList.get(index).getmEndWeek();
        else
            return -1;
    }

    public String getParity(int index) {
        if (index >= 0 && index < mLinkedList.size())
            return mLinkedList.get(index).getmParity();
        else
            return null;
    }

    public int getWeekDay(int index) {
        if (index >= 0 && index < mLinkedList.size())
            return mLinkedList.get(index).getmWeekday();
        else
            return -1;
    }

    public int getClassNumber(int index) {
        if (index >= 0 && index < mLinkedList.size())
            return mLinkedList.get(index).getmClassNumber();
        else
            return -1;
    }

    public void addSchedule(LinkedList<String> l, int day, int time, String type) {
        int[] t = parseRange(l.get(l.size() - 1));
        mLinkedList.add(new ClassSchedule(day, time, l.get(l.size() - 2), t[0], t[1], type));
    }

    public void addSchedule(LinkedList<String> l, int day, int time) {
        int[] t = parseRange(l.get(l.size() - 1));
        mLinkedList.add(new ClassSchedule(day, time, l.get(l.size() - 2), t[0], t[1]));
    }
    public String getClassInfo(int week,int day,int time){
        for(int i=0;i<mLinkedList.size();i++){
            if(day == mLinkedList.get(i).getmWeekday() && time == mLinkedList.get(i).getmClassNumber()){
                if(week <= mLinkedList.get(i).getmEndWeek() && week >= mLinkedList.get(i).getmBeginWeek()){
                    if((mLinkedList.get(i).getmParity() == null)||(mLinkedList.get(i).getmParity().equals("单周")&& (week%2!=0))||(mLinkedList.get(i).getmParity().equals("双周")&& (week%2 ==0))){
                        return mCourseName + " "+mLinkedList.get(i).getmClassRoom()+" "+day+" "+time;
                    }

                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String s = mCourseName + "   " + mTeacher;
        for (int i = 0; i < mLinkedList.size(); i++)
            s += "   " + mLinkedList.get(i).toString();
        return s;
    }
}
