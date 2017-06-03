package com.example.android.coursesmanagementsystem;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/27.
 */

public class UserContentAdapter extends ArrayAdapter<UserContent> implements View.OnClickListener {
/*    public UserContentAdapter(Activity context, ArrayList<UserContent> arrayList) {

        super(context, 0, arrayList);
    }*/
    public UserContentAdapter(Activity context,ArrayList<UserContent> arrayList,CallBack callBack){
        super(context,0,arrayList);
        mCallBack = callBack;
        mUserContentArrayList = arrayList;
    }
    CallBack mCallBack;
    ArrayList<UserContent> mUserContentArrayList;
    public interface CallBack{
        public void click(View view);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listview = convertView;
        ViewHolder viewHolder;
        if (listview == null) {
            listview = LayoutInflater.from(getContext()).inflate(R.layout.name_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mTextViewName = (TextView) listview.findViewById(R.id.usr_list_text_1);
            viewHolder.mTextViewSno = (TextView) listview.findViewById(R.id.usr_list_text_2);
            viewHolder.mImageViewDelete = (ImageView) listview.findViewById(R.id.user_list_image_view_2);
            listview.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        UserContent userContent = getItem(position);
        viewHolder.mTextViewName.setText(userContent.getName());
        viewHolder.mTextViewSno.setText(userContent.getSno());
        viewHolder.mImageViewDelete.setTag(position);
        viewHolder.mImageViewDelete.setOnClickListener(this);


        return listview;
    }

    public class ViewHolder {
        TextView mTextViewName;
        TextView mTextViewSno;
        ImageView mImageViewDelete;
    }

    @Override
    public int getCount() {
        return mUserContentArrayList.size();
    }

    @Nullable
    @Override
    public UserContent getItem(int position) {
        return mUserContentArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onClick(View v) {
         mCallBack.click(v);
    }
}
