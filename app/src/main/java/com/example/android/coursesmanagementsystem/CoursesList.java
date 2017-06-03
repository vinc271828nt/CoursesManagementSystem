package com.example.android.coursesmanagementsystem;

import java.util.LinkedList;

/**
 * Created by Administrator on 2017/5/28.
 */

public class CoursesList {
    private LinkedList<Course> mlinkedList;

    public CoursesList() {
        mlinkedList = new LinkedList<Course>();
    }

    public CoursesList( String s) {
        String[] ss = s.split("     ");
        mlinkedList = new LinkedList<Course>();
        for (int i = 0; i < ss.length; i++)
            mlinkedList.add(new Course(ss[i]));
    }

    public void setCourses(LinkedList<String> linkedList,String classnum) {
        for (int i = 1; i < 7; i++) {
            if (!linkedList.get(i).isEmpty()) {
                String[] s = linkedList.get(i).split(" ");
                fillList(s,classnum);
            }
        }
    }

    private void fillList(String[] s,String classn) {
        int day = 0;
        int index = 1;
        String classtime = s[0];
        int ct = getClassTime(classtime);
        for (int i = 1; i < s.length; i++) {
/*            if (s[i].equals(" ")) {
                day++;
                index++;
            }*/
            if(s[i].equals("*")){
                day++;
                index = i+1;
            }
            if (s[i].contains("-")) {
                //if(s[i-2].contains("150"+classn)) {
                if(isSnoExit(s,index,i-1,"150"+classn)){
                    if (!s[i].contains(",")) {
                        if (s[i + 1].equals("单周") || s[i + 1].equals("双周")) {
                            setCourse(copyToArray(s, index, i + 1), day, ct, s[i + 1]);
                            index = i + 2;
                        } else {
                            setCourse(copyToArray(s, index, i + 1), day, ct);
                            index = i + 1;
                        }
                    } else {
                        String[] temp = s[i].split(",");
                        if (s[i + 1].equals("单周") || s[i + 1].equals("双周")) {
                            s[i] = temp[0];
                            setCourse(copyToArray(s, index, i + 1), day, ct, s[i + 1]);
                            s[i] = temp[1];
                            setCourse(copyToArray(s, index, i + 1), day, ct, s[i + 1]);
                            index = i + 2;
                        } else {
                            s[i] = temp[0];
                            setCourse(copyToArray(s, index, i + 1), day, ct);
                            s[i] = temp[1];
                            setCourse(copyToArray(s, index, i + 1), day, ct);
                            index = i + 1;
                        }
                    }
                   // day++;
                }
                else{
                    if (s[i + 1].equals("单周") || s[i + 1].equals("双周")) {
                        index = i + 2;
                    } else {
                        index = i + 1;
                    }

                }
            }
        }
    }

    private int getClassTime(String s) {
        String[] ss = {"第1节", "第2节", "第3节", "第4节", "第5节", "第6节"};
        int i;
        for (i = 0; i < 6; i++)
            if (ss[i].equals(s))
                return i;
        return -1;
    }

    private LinkedList<String> copyToArray(String[] s, int h, int t) {
        LinkedList<String> rl = new LinkedList<String>();
        for (int i = h; i < t; i++)
            rl.add(s[i]);
        return rl;
    }

    private void setCourse(LinkedList<String> l, int day, int time, String type) {
        int index = findCourseInList(l.get(0));
        if (index != -1) {
            mlinkedList.get(index).addSchedule(l, day, time, type);
        } else {
            mlinkedList.add(new Course(l, day, time, type));
        }
    }

    private void setCourse(LinkedList<String> l, int day, int time) {
        int index = findCourseInList(l.get(0));
        if (index != -1) {
            mlinkedList.get(index).addSchedule(l, day, time);
        } else {
            mlinkedList.add(new Course(l, day, time));
        }
    }

    private int findCourseInList(String s) {
        for (int i = 0; i < mlinkedList.size(); i++) {
            if (mlinkedList.get(i).getmCourseName().equals(s))
                return i;
        }
        return -1;
    }

    private void addScheduleToCourse(LinkedList<String> l, String time, int day, String type) {

    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < mlinkedList.size() - 1; i++)
            s += mlinkedList.get(i).toString() + "     ";
        s += mlinkedList.get(mlinkedList.size() - 1).toString();
        return s;
    }
    private boolean isSnoExit(String[]ss,int b,int e,String s){
        for(int i = b;i<e;i++)
            if(ss[i].contains(s))
                return true;
        return false;
    }

    public String getClassInfoString(int week) {
        String m = null;
        for (int i = 0; i <7;i++)
            for(int j = 0;j <6;j++)
            {
                for(int k=0;k < mlinkedList.size();k++)
                {
                    if(mlinkedList.get(k).getClassInfo(week,i,j) !=null) {
                        if (m == null)
                            m = mlinkedList.get(k).getClassInfo(week, i, j) + "   ";
                        else
                            m += mlinkedList.get(k).getClassInfo(week, i, j) + "   ";
                       // m += mlinkedList.get(k).getClassInfo(week, i, j) + "   ";
                    }
                }
            }
        return m;
    }
}
