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
 * Created by Administrator on 2017/5/28.
 */

public class WeekGridAdapter extends ArrayAdapter<String> {
    ArrayList<String> mArrayList;
  public  WeekGridAdapter(Activity context,ArrayList<String> arrayList){
      super(context,0,arrayList);
      mArrayList = arrayList;
  }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View gridView = convertView;
        ViewHolderWeek  viewHolderWeek;
        if(gridView == null) {
            gridView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            viewHolderWeek = new ViewHolderWeek();
            viewHolderWeek.mTextView =(TextView)gridView.findViewById(android.R.id.text1);
            gridView.setTag(viewHolderWeek);
        }else{
            viewHolderWeek = (ViewHolderWeek)convertView.getTag();
        }
        String mString = getItem(position);
        viewHolderWeek.mTextView.setText(mString);


        return gridView;
    }
    public class ViewHolderWeek{
        TextView mTextView;
    }
}
