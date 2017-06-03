package com.example.android.coursesmanagementsystem;

/**
 * Created by Administrator on 2017/5/28.
 */

public class ClassSchedule {
    private int mWeekday;
    private int mClassTime;
    private String mClassRoom;
    private int mBeginWeek;
    private int mEndWeek;
    private String mParity;

    public ClassSchedule(int day, int time, String room, int b, int e, String p) {
        this.mWeekday = day;
        this.mClassTime = time;
        this.mClassRoom = room;
        this.mBeginWeek = b;
        this.mEndWeek = e;
        this.mParity = p;
    }

    public ClassSchedule(String s) {
        String[] ss = s.split(" ");
        this.mWeekday = Integer.valueOf(ss[0]);
        this.mClassTime = Integer.valueOf(ss[1]);
        this.mClassRoom = ss[2];
        this.mBeginWeek = Integer.valueOf(ss[3]);
        this.mEndWeek = Integer.valueOf(ss[4]);
        if (ss.length > 5)
            this.mParity = ss[5];

    }

    public ClassSchedule(int day, int time, String room, int b, int e) {
        this.mWeekday = day;
        this.mClassTime = time;
        this.mClassRoom = room;
        this.mBeginWeek = b;
        this.mEndWeek = e;
    }
/*    public  ClassSchedule(String str){
        if(!str.isEmpty()){
            String [] ss = str.split(" ");
            int i =0;
            while (i <ss.length)
            {
                if(i == 0)
                    this.mWeekday = ss[0];
                else if(i == 1)
                    this.mClassNumber = ss[1];
                else if(i == 2)
                    this.mClassRoom = ss[2];
                else if(i == 3)
                    this.mBeginWeek = Integer.parseInt(ss[3]);
                else if(i == 4)
                    this.mEndWeek = Integer.parseInt(ss[4]);
                else if(i == 5)
                    this.mParity = ss[5];
            }
        }
    }*/

    public String getmClassRoom() {
        return mClassRoom;
    }

    public void setmClassRoom(String mClassRoom) {
        this.mClassRoom = mClassRoom;
    }

    public int getmWeekday() {
        return mWeekday;
    }

    public void setmWeekday(int mWeekday) {
        this.mWeekday = mWeekday;
    }

    public int getmClassNumber() {
        return mClassTime;
    }

    public void setmClassNumber(int time) {
        this.mClassTime = time;
    }

    public String getmParity() {
        return mParity;
    }

    public void setmParity(String mParity) {
        this.mParity = mParity;
    }

    public int getmBeginWeek() {
        return mBeginWeek;
    }

    public void setmBeginWeek(int mBeginWeek) {
        this.mBeginWeek = mBeginWeek;
    }

    public int getmEndWeek() {
        return mEndWeek;
    }

    public void setmEndWeek(int mEndWeek) {
        this.mEndWeek = mEndWeek;
    }

    @Override
    public String toString() {
        if (mParity != null)
            return mWeekday + " " + mClassTime + " "+mClassRoom+ " "
                    + mBeginWeek + " " + mEndWeek + " " + mParity;
        else
            return mWeekday + " " + mClassTime + " "+mClassRoom + " "
                    + mBeginWeek + " " + mEndWeek;
    }

}
