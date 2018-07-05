package com.example.tianlong.yukaolianxi;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;
import com.example.tianlong.yukaolianxi.dao.SqliteDao;
import com.example.tianlong.yukaolianxi.fragment.FragmentOne;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private Button button;
    private HorizontalScrollView hs;
    private LinearLayout linearLayout;
    private List<TextView> list ;
    private ArrayList<ChannelBean> channelBeans;
    private SqliteDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获得组件
        initview();

    }

    private void initview() {
        viewPager = findViewById(R.id.main_viewpager);
        button = findViewById(R.id.main_button);
        linearLayout = findViewById(R.id.main_liner);
        hs = findViewById(R.id.hs);
        dao = new SqliteDao(MainActivity.this);
        //获得导航栏
        initData();
    }

    private void initData() {
        list = new ArrayList<>();
        channelBeans = new ArrayList<>();

        channelBeans.add(new ChannelBean("头条",true));
        channelBeans.add(new ChannelBean("订阅",true));
        channelBeans.add(new ChannelBean("热评",true));
        channelBeans.add(new ChannelBean("图集",true));
        channelBeans.add(new ChannelBean("视屏",true));
        channelBeans.add(new ChannelBean("国际",true));
        channelBeans.add(new ChannelBean("购物",true));
        channelBeans.add(new ChannelBean("社会",true));
        channelBeans.add(new ChannelBean("娱乐",true));
        channelBeans.add(new ChannelBean("购物",true));
        channelBeans.add(new ChannelBean("亲自1",false));
        channelBeans.add(new ChannelBean("亲自2",false));
        channelBeans.add(new ChannelBean("亲自3",false));
        channelBeans.add(new ChannelBean("亲自4",false));
        channelBeans.add(new ChannelBean("亲自5",false));
        channelBeans.add(new ChannelBean("亲自6",false));

        for (int i = 0; i <channelBeans.size() ; i++) {
            TextView textView = new TextView(this);
            textView.setText(channelBeans.get(i).getName());
            textView.setTextSize(20);
            textView.setId(i+1000);
            String name = channelBeans.get(i).getName();
            dao.add(name);
            Log.i("TAG",dao.toString());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = v.getId();
                    viewPager.setCurrentItem(id-1000);
                }
            });

            //判断点击颜色
            if (i == 0){
                textView.setTextColor(Color.RED);
            }else {
                textView.setTextColor(Color.BLACK);
            }

            if (channelBeans.get(i).isSelect()){
                textView.setVisibility(View.VISIBLE);
            }else {
                textView.setVisibility(View.GONE);
            }
            //添加条目
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(30,10,30,10);
            linearLayout.addView(textView,layoutParams);
            list.add(textView);
        }
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                for (int i = 0; i <list.size() ; i++) {
                    if (position == i){
                        list.get(i).setTextColor(Color.RED);
                    }else {
                        list.get(i).setTextColor(Color.BLACK);
                    }
                    TextView textView = list.get(position);
                    //移动的距离
                    int width = textView.getWidth()+10;

                    hs.scrollTo(width*position,0);
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChannelActivity.startChannelActivity(MainActivity.this,channelBeans);
            }
        });

    }

     class MyAdapter extends FragmentPagerAdapter {

         public MyAdapter(FragmentManager fm) {
             super(fm);
         }

         @Override
         public Fragment getItem(int position) {
             return FragmentOne.getInStance(list.get(position).getText().toString());
         }

         @Override
         public int getCount() {
             return list.size();
         }
     }
}
