package com.example.android.coursesmanagementsystem;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class CourseInWeekActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private int mWeekNumber;
    private String mSno;
    private ArrayList<ClassInfo> mArrayList;

    CoursesList mCoursesList;
    Person mPerson;
    MyDBHelper mMyDBHelper;
    ClassInfoAdapter mClassInfoAdapter;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_in_week);
        Intent intent = getIntent();
        mWeekNumber = intent.getIntExtra(WeekGridActivity.WEEK,0);
        mSno = intent.getStringExtra(MainActivity.SNO);
        android.support.v7.app.ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            this.setTitle("第"+(mWeekNumber +1)+"周");
        }
        mArrayList = new ArrayList<ClassInfo>();
        mMyDBHelper = new MyDBHelper(this, "roster", null, 1);
        mMyDBHelper.getWritableDatabase();
        SQLiteDatabase db0 = mMyDBHelper.getWritableDatabase();
        //   Cursor cursor = db.rawQuery("select * from roster ",null);
        //   String s = cursor.getString(cursor.getColumnIndex("coursesinfo1602"));
        Cursor cursor = db0.rawQuery("select  coursesinfo1602 from roster where sno = ?",new String[]{mSno});
        cursor.moveToFirst();
        String teststring = cursor.getString(cursor.getColumnIndex("coursesinfo1602"));
        cursor.close();
       // mCoursesList = new CoursesList(teststring);
       // String s = mCoursesList.getClassInfoString(mWeekNumber+1);
        mPerson = new Person("default",mSno,teststring);
        String s = mPerson.getCoursesList().getClassInfoString(mWeekNumber+1);
        if(s !=null){
            String[]ss = s.split("   ");
            for(int i = 0;i <ss.length;i++)
                mArrayList.add(new ClassInfo(ss[i]));
        }
        mClassInfoAdapter = new ClassInfoAdapter(CourseInWeekActivity.this,mArrayList);
        mListView = (ListView)findViewById(R.id.course_In_week_list);
        mListView.setAdapter(mClassInfoAdapter);
        mListView.setOnItemClickListener(this);
        Log.i("CourseActivity","right");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
}
