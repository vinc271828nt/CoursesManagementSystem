package com.example.android.coursesmanagementsystem;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, UserContentAdapter.CallBack {
    public static final String SNO = "SNO";
    // ArrayList<UserContent> arrayList;
    UserContentAdapter userContentAdapter;
    private EditText mEditTextName;
    private EditText mEditTextSno;
    private int mSelClass;

    private ProgressDialog mProgressDialog;
    Map<String, String> mHashMap0;
    Map<String, String> mHashMap1;

    public static final String LOGIN_BODY_NAME_VIEWSTATE = "__VIEWSTATE";
    public static final String LOGIN_TAIL_NAME_EVENTVALIDATION = "__EVENTVALIDATION";

    LinkedList<String> mLinkedList;
    CoursesList mCoursesList;
    Person mPerson;
    MyDBHelper mMyDBHelper;
    private ArrayList<UserContent> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        android.support.v7.app.ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            //actionBar.setDisplayHomeAsUpEnabled(true);
            this.setTitle("用户管理");
        }
        mCoursesList = new CoursesList();
        mMyDBHelper = new MyDBHelper(this, "roster", null, 1);
        mMyDBHelper.getWritableDatabase();
        mPerson = new Person();
        mArrayList = new ArrayList<UserContent>();
        addContentToList();

        Button button = (Button) findViewById(R.id.button_insert);
        mEditTextName = (EditText) findViewById(R.id.edit_text_name);
        mEditTextSno = (EditText) findViewById(R.id.edit_text_sno);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelClass = calculateField(mEditTextSno.getText().toString());
                if (mEditTextName.getText().toString().isEmpty() || mSelClass == -1 ||!(mEditTextSno.getText().toString().matches("-?\\d+")) || mEditTextSno.getText().toString().length() <=10)
                    Toast.makeText(MainActivity.this, "输入有误", Toast.LENGTH_LONG).show();
                else {
                   mPerson.setName(mEditTextName.getText().toString());
                    mPerson.setSno(mEditTextSno.getText().toString());
                    mArrayList.add(new UserContent(mPerson.getName(), mPerson.getSno()));
                    userContentAdapter.notifyDataSetChanged();
                    String[]ss = {String.valueOf(mSelClass),caculateClass(mEditTextSno.getText().toString())};
                   // new asyncTaskGet().execute(String.valueOf(mSelClass));
                    new asyncTaskGet().execute(ss);
                   // insertToDB(mEditTextName.getText().toString(), mEditTextSno.getText().toString());


                }
            }
        });
        mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setTitle("提示信息");
        mProgressDialog.setMessage("请稍候");
        mProgressDialog.setCancelable(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);


        mCoursesList = new CoursesList();
        mMyDBHelper = new MyDBHelper(this, "roster", null, 1);
        mMyDBHelper.getWritableDatabase();
        mPerson = new Person();

        userContentAdapter = new UserContentAdapter(this, mArrayList, this);
        ListView listView = (ListView) findViewById(R.id.user_list_view);
        listView.setAdapter(userContentAdapter);
        listView.setOnItemClickListener(this);
    }

    public class asyncTaskGet extends AsyncTask<String,Integer,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressDialog.show();

        }

        @Override
        protected String doInBackground(String... params) {
            String responseData = "";
            Response response;
            String teststring = "";
            try {
                publishProgress(0);
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                        .add("__EVENTTARGET", "selDepart")
                        .add("selYear", "2018")
                        .add("selTerm", "8")
                        .add("selKblb", "1")
                        .add("selDepart", "31")
                        .add("selClass", "539")
                        .build();
                Request request = new Request.Builder()
                        // 指定访问的服务器地址是电脑本机
                        .url("http://210.31.209.15/kb.aspx")
                        .get()
                        //.post(requestBody)
                        .build();
                response = client.newCall(request).execute();
                responseData =   response.body().string();
                mHashMap0 = getViewStateValue(responseData);

                publishProgress(25);

                OkHttpClient client1 = new OkHttpClient();
                RequestBody requestBody1 = new FormBody.Builder()
                        .add("__EVENTTARGET", "selDepart")
                        .add("__VIEWSTATE", mHashMap0.get(LOGIN_BODY_NAME_VIEWSTATE))
                        .add("selYear", "2016")
                        .add("selTerm", "2")
                        .add("selKblb", "1")
                        .add("selDepart", "19")
                        .add("selClass", params[0])
                        .add("__EVENTVALIDATION", mHashMap0.get(LOGIN_TAIL_NAME_EVENTVALIDATION)).build();
                Request request1 = new Request.Builder()
                        .url("http://210.31.209.15/kb.aspx")
                        .post(requestBody1)
                        .build();
                response = client1.newCall(request1).execute();
                responseData = response.body().string();
                mHashMap1 = getViewStateValue(responseData);

                publishProgress(50);

                OkHttpClient client2 = new OkHttpClient();
                RequestBody requestBody2 = new FormBody.Builder()
                        .add("__EVENTTARGET", "btQuery")
                        .add("__VIEWSTATE", mHashMap1.get(LOGIN_BODY_NAME_VIEWSTATE))
                        .add("selYear", "2016")
                        .add("selTerm", "2")
                        .add("selKblb", "1")
                        .add("selDepart", "19")
                        .add("selClass", params[0])
                        .add("__EVENTVALIDATION", mHashMap1.get(LOGIN_TAIL_NAME_EVENTVALIDATION)).build();

                Request request2 = new Request.Builder()
                        .url("http://210.31.209.15/kb.aspx")
                        .post(requestBody2)
                        .build();
                response = client2.newCall(request2).execute();
                responseData = response.body().string();
                publishProgress(75);

                mLinkedList = getTableContent(responseData);
                //String []s = mLinkedList.get(1).split(" ");
                //  mCoursesList.setCourses(mLinkedList);
                mPerson.getCoursesList().setCourses(mLinkedList,params[1]);
                teststring = mPerson.getCoursesListString();

                SQLiteDatabase db = mMyDBHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(MyDBHelper.NAME,mPerson.getName());
                // values.put(MyDBHelper.SNO,"201302050113");
                values.put(MyDBHelper.SNO,mPerson.getSno());
                values.put(MyDBHelper.COURSESINFO1602,mPerson.getCoursesListString());
                db.insert(MyDBHelper.ROSTER,null,values);
                publishProgress(100);


            } catch (Exception e) {
                e.printStackTrace();
            }

            return  teststring;

        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mProgressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mProgressDialog.setProgress(values[0]);
        }
    }

    private Map<String, String> getViewStateValue(String html) {
        Map<String, String> viewStateValue = new LinkedHashMap<>();
        if (null != html) {
            Document document = Jsoup.parse(html);
            Element viewStateElement =
                    document.select("input[name=\"__VIEWSTATE\"]").first();
            Element eventValidation =
                    document.select("input[name=\"__EVENTVALIDATION\"]").first();
            if (null != viewStateElement) {
                viewStateValue.put(LOGIN_BODY_NAME_VIEWSTATE,
                        viewStateElement.attr("value"));
            }
            if (null != eventValidation) {
                viewStateValue.put(LOGIN_TAIL_NAME_EVENTVALIDATION,
                        eventValidation.attr("value"));
            }
        }
        return viewStateValue;
    }
    private LinkedList<String> getTableContent(String html) {
        String mString = "";
        LinkedList<String> linkedList = new LinkedList<String>();
        if (null != html) {
            Document document = Jsoup.parse(html);
            Elements trs = document.select("table").select("tr");
            for (int i = 0; i < trs.size(); i++) {
                Elements tds = trs.get(i).select("td");
                for (int j = 0; j < tds.size(); j++) {
                    mString += tds.get(j).text()+" "+"*";
                    mString += " ";
                }
                linkedList.add(mString);
                mString = "";
            }
        }
        return linkedList;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.i("MainActivity","haha");
               // this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("MainActivity",mArrayList.get(position).getSno());
        Log.i("MainActivity", "haha");
        Intent intent = new Intent(MainActivity.this,WeekGridActivity.class);
        intent.putExtra(SNO,mArrayList.get(position).getSno());
        startActivity(intent);


        //db.execSQL("delete from roster where ");
    }

    @Override
    public void click(View view) {
        int num = (Integer) view.getTag();

        SQLiteDatabase db = mMyDBHelper.getWritableDatabase();
        db.delete("roster", "sno = ?", new String[]{mArrayList.get(num).getSno()});
        mArrayList.remove(num);
        userContentAdapter.notifyDataSetChanged();

        Log.i("MainActivity", "wawa");
    }

    private int calculateField(String string) {
            int value = Integer.valueOf(string.substring(7, 8));
            if (value == 01 ) {
                if (Integer.valueOf(string.substring(9, 10)) > 4)
                    return 2202;
                else
                    return 2207;
            } else if (value == 02)
                return 2209;
            else if (value == 03)
                return 2212;
            else if (value == 04)
                return 2200;
        return -1;
    }
    private  String caculateClass(String string){
             return string.substring(9,10);

    }

    private void addContentToList() {
        SQLiteDatabase db0 = mMyDBHelper.getWritableDatabase();
        //   Cursor cursor = db.rawQuery("select * from roster ",null);
        //   String s = cursor.getString(cursor.getColumnIndex("coursesinfo1602"));
        Cursor cursor0 = db0.rawQuery("select name,sno  from roster ", null);
        cursor0.moveToFirst();
        int rows_num0 = cursor0.getCount();    //取得資料表列數

        if (rows_num0 != 0) {
            cursor0.moveToFirst();            //將指標移至第一筆資料
            for (int i = 0; i < rows_num0; i++) {
                mArrayList.add(new UserContent(cursor0.getString(0), cursor0.getString(1)));

                cursor0.moveToNext();        //將指標移至下一筆資料
            }
        }
        cursor0.close();
    }

    private void insertToDB(String name, String sno) {
        SQLiteDatabase db = mMyDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyDBHelper.NAME, name);
        // values.put(MyDBHelper.SNO,"201302050113");
        values.put(MyDBHelper.SNO, sno);
        //values.put(MyDBHelper.COURSESINFO1602,teststring);
        db.insert(MyDBHelper.ROSTER, null, values);
    }
}
