package com.example.rikaolianxi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rikaolianxi.Beans;

import java.util.List;

public class PullAdapter extends BaseAdapter {
    private List<Beans.ResultBean.DataBean> list;
    private Context context;

    private final int ONE_ITEM = 0 ;
    private final int TWO_ITEM = 0 ;
    private final int THREE_ITEM = 0 ;

    public PullAdapter(List<Beans.ResultBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        String thumbnail_pic_s = list.get(position).getThumbnail_pic_s();
        String thumbnail_pic_s02 = list.get(position).getThumbnail_pic_s02();
        String thumbnail_pic_s03 = list.get(position).getThumbnail_pic_s03();

        if (thumbnail_pic_s != null && thumbnail_pic_s02==null && thumbnail_pic_s03==null){
            return ONE_ITEM;
        }else if (thumbnail_pic_s != null && thumbnail_pic_s02!=null && thumbnail_pic_s03==null){
            return TWO_ITEM;
        }else if (thumbnail_pic_s != null && thumbnail_pic_s02!=null && thumbnail_pic_s03!=null){
            return THREE_ITEM;
        }else {
            return ONE_ITEM;
        }

    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == ONE_ITEM){

        }else if (itemViewType == TWO_ITEM){

        }else if (itemViewType == THREE_ITEM){

        }

        return null;
    }
    class One{
        TextView one;
        ImageView one_img;
    }
    class Two{
        TextView two;
        ImageView two_img_one,two_img_two;
    }
    class Three{
        TextView three;
        ImageView three_img_one,three_img_two,three_img_three;
    }
}
