package com.example.android.coursesmanagementsystem;

/**
 * Created by Administrator on 2017/5/29.
 */

public class ClassInfo {
    private static String[] cWeek = {"", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日", "越界"};
    private static String[] cTime = {"第一节", "第二节", "第三节", "第四节", "第五节", "第六节", "越界"};
    private int mDay;
    private int mTime;
    private String mCourseName;
    private String mClassroom;

    public ClassInfo(int day, int time, String name, String room) {
        this.mDay = day;
        this.mTime = time;
        this.mCourseName = name;
        this.mClassroom = room;
    }

    public ClassInfo(String s) {
        String[] ss = s.split(" ");
        if (ss.length == 4) {
            this.mCourseName = ss[0];
            this.mClassroom = ss[1];
            this.mDay = Integer.valueOf(ss[2]);
            this.mTime = Integer.valueOf(ss[3]);
        }
    }

    public String getCourseName() {
        return mCourseName;
    }

    public String getClassroom() {
        return mClassroom;
    }

    public int getDay() {
        return mDay;
    }

    public String getDayString() {
        if (mDay < 8 && mDay > 0)
            return cWeek[mDay];
        else
            return cWeek[8];
    }

    public int getTime() {
        return mTime;
    }

    public String getTimeString() {
        if(mTime<6&& mTime>=0)
        return cTime[mTime];
        else
            return cTime[6];
    }
}
