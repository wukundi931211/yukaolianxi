package com.example.tianlong.yukaolianxi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tianlong.yukaolianxi.R;
import com.example.tianlong.yukaolianxi.beans.Beans;
import com.example.tianlong.yukaolianxi.utils.Imageloader;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class PullAdapter extends BaseAdapter {

    private  List<Beans.DataBeanX.DataBean> list2;
    private Context context;


    private final  int ONR_ITEM = 0 ;
    private final  int TWO_ITEM = 1 ;



    public PullAdapter(List<Beans.DataBeanX.DataBean> list2, Context context) {
        this.list2 = list2;
        this.context = context;
    }




    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {

        if (list2.get(position).getPics().size()==0){
            return ONR_ITEM;
        }else if (list2.get(position).getPics().size()==1){
            return TWO_ITEM;
        }else {
            return ONR_ITEM;
        }
    }

    @Override
    public int getCount() {
        return list2.size();
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
        if (itemViewType == ONR_ITEM){
            OneViewHolder oneViewHolder ;
            if (convertView == null){
                oneViewHolder = new OneViewHolder();
                convertView = View.inflate(context,R.layout.listview,null);
                oneViewHolder.title_one = convertView.findViewById(R.id.list_title_one);
                oneViewHolder.catalog_name_one = convertView.findViewById(R.id.catalog_name_one);

                convertView.setTag(oneViewHolder);
            }else {
                oneViewHolder = (OneViewHolder) convertView.getTag();
            }
            oneViewHolder.title_one.setText(list2.get(position).getTitle());
            oneViewHolder.catalog_name_one.setText(list2.get(position).getCatalog_name());

            return convertView;
        }else if (itemViewType == TWO_ITEM){
            TwoViewHolder twoViewHolder;
            if (convertView == null){
                twoViewHolder = new TwoViewHolder();
                convertView = View.inflate(context,R.layout.listview_two,null);
                twoViewHolder.title_two = convertView.findViewById(R.id.list_title_two);
                twoViewHolder.catalog_name_two = convertView.findViewById(R.id.catalog_name_two);
                twoViewHolder.imageView = convertView.findViewById(R.id.imageView);
                convertView.setTag(twoViewHolder);

            }else {
                twoViewHolder = (TwoViewHolder) convertView.getTag();
            }


            twoViewHolder.title_two.setText(list2.get(position).getTitle());
            twoViewHolder.catalog_name_two.setText(list2.get(position).getCatalog_name());
            ImageLoader.getInstance().displayImage("http://img.365jia.cn/uploads/"+list2.get(position).getPics().get(0),twoViewHolder.imageView);
            return convertView;
        }
        return null;
    }

    class OneViewHolder{
        TextView title_one,catalog_name_one;
    }
    class TwoViewHolder{
        TextView title_two,catalog_name_two;
        ImageView imageView;
    }

}
