package com.example.android.coursesmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class WeekGridActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    public static final String WEEK = "week";
    ArrayList<String> mArrayList ;
    private String mSno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_grid);
        android.support.v7.app.ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            this.setTitle("课程管理");
        }
        mArrayList = new ArrayList<String>();
        fillArrayList(mArrayList);
       // ArrayAdapter<ArrayList<String>> arrayAdapter = new ArrayAdapter<ArrayList<String>>(WeekGridActivity.this, android.R.layout.simple_list_item_1, mArrayList);

        WeekGridAdapter weekGridAdapter= new WeekGridAdapter(WeekGridActivity.this,mArrayList);
        GridView gridView = (GridView) findViewById(R.id.week_grid_view);
        gridView.setAdapter(weekGridAdapter);
        gridView.setOnItemClickListener(this);
        Intent intent = getIntent();
        mSno = intent.getStringExtra(MainActivity.SNO);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(WeekGridActivity.this,CourseInWeekActivity.class);
        intent.putExtra(WEEK,position);
        intent.putExtra(MainActivity.SNO,mSno);
        startActivity(intent);

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
    private void fillArrayList(ArrayList<String> arrayList){
         String[] week = {"第1周", "第2周", "第3周", "第4周", "第5周", "第6周", "第7周", "第8周", "第9周", "第10周",
                "第11周", "第12周", "第13周", "第14周", "第15周", "第16周", "第17周", "第18周", "第19周", "第20周"};
        for(int i = 0;i < week.length;i++)
            arrayList.add(week[i]);
    }
}
