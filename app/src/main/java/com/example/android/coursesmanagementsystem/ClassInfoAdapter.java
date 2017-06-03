package com.example.android.coursesmanagementsystem;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/29.
 */

public class ClassInfoAdapter extends ArrayAdapter<ClassInfo> {
    ArrayList<ClassInfo> mArrayList;

    public ClassInfoAdapter(Activity context, ArrayList<ClassInfo> arrayList){
        super(context,0,arrayList);
        this.mArrayList =arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View mListView = convertView;
        ViewHolderCourse mViewHolderCourse;
        if (mListView == null) {
            mListView = LayoutInflater.from(getContext()).inflate(R.layout.course_list_item, parent, false);
            mViewHolderCourse = new ViewHolderCourse();

            mViewHolderCourse.mTextName = (TextView)mListView.findViewById(R.id.course_list_text_3);
            mViewHolderCourse.mTextRoom = (TextView)mListView.findViewById(R.id.course_list_text_4);
            mViewHolderCourse.mDay = (TextView)mListView.findViewById(R.id.course_list_text_1);
            mViewHolderCourse.mTime = (TextView)mListView.findViewById(R.id.course_list_text_2);
            mListView.setTag(mViewHolderCourse);



        } else {
            mViewHolderCourse = (ViewHolderCourse)convertView.getTag();
        }
        ClassInfo classInfo = getItem(position);
        mViewHolderCourse.mTextName.setText(classInfo.getCourseName());
        mViewHolderCourse.mTextRoom.setText(classInfo.getClassroom());
       // mViewHolderCourse.mDay.setText(String.valueOf(classInfo.getDay()));
       // mViewHolderCourse.mTime.setText(String.valueOf(classInfo.getTime()));
         mViewHolderCourse.mDay.setText(classInfo.getDayString());
         mViewHolderCourse.mTime.setText(classInfo.getTimeString());
        return mListView;

    }
    public class ViewHolderCourse{
        TextView mTextName;
        TextView mTextRoom;
        TextView mDay;
        TextView mTime;
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Nullable
    @Override
    public ClassInfo getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
