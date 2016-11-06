package com.township.com.township;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by nupadhay on 11/6/2016.
 */
public class TownShipAdapter extends BaseAdapter{

    private Context mContext;
    private String[] mStr;
    private LayoutInflater mInflator;
    private Integer[] mImageArray;
     ViewHolder  view;
    public TownShipAdapter(Context context,String [] str,Integer[] imageArray){
        mContext = context;
        mStr = str;
        mImageArray = imageArray;
        mInflator = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return mStr.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    int pos;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

         if(convertView==null){
             view = new ViewHolder();
             convertView = mInflator.inflate(R.layout.townadapter,null);
             view.mServicesName = (TextView) convertView.findViewById(R.id.townTextID);
             view.imageView = (ImageView) convertView.findViewById(R.id.imageID);
             view.mLinear = (LinearLayout) convertView.findViewById(R.id.linearID);
             convertView.setTag(view);
         }else{
             view = (ViewHolder) convertView.getTag();
         }
        view.mServicesName.setText(mStr[position]);
        view.imageView.setBackgroundResource(mImageArray[position]);
        return convertView;
    }

    public class ViewHolder{
        private TextView mServicesName;
        private ImageView imageView;
        private LinearLayout mLinear;
    }
}
