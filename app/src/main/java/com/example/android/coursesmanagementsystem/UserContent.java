package com.example.android.coursesmanagementsystem;

/**
 * Created by Administrator on 2017/5/27.
 */

public class UserContent {
    private String mName;
    private String mSno;
    //private final static IMAGEISRC = "R.draw."
    public UserContent(String name,String sno){
        this.mName = name;
        this.mSno = sno;
    }

    public String getName() {
        return mName;
    }

    public String getSno() {
        return mSno;
    }
}
