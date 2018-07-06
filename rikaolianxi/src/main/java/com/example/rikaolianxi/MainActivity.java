package com.example.rikaolianxi;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private PullToRefreshListView pull;
    private String path = "http://v.juhe.cn/toutiao/index?type=top&key=2f092bd9ce76c0257052d6d3c93c11b4";
    private Handler handler = new Handler();
    private List<Beans.ResultBean.DataBean> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取方法
        setInitView();
    }

    private void setInitView() {
        pull = findViewById(R.id.pull_listview);

        pull.setMode(PullToRefreshListView.Mode.BOTH);

        final ILoadingLayout start = pull.getLoadingLayoutProxy(true, false);
        start.setPullLabel("下拉刷新");
        start.setReleaseLabel("正在刷新");
        start.setRefreshingLabel("刷新完成");
        final ILoadingLayout end = pull.getLoadingLayoutProxy(false, true);
        end.setPullLabel("上拉加载");
        end.setReleaseLabel("正在加载");
        end.setRefreshingLabel("加载完成");

        pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(final PullToRefreshBase<ListView> pullToRefreshBase) {
                getData();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pull.onRefreshComplete();
                    }
                },2000);
                //可以设置刷新的时间....
                //last最近的,最后一次update修改/更新
                start.setLastUpdatedLabel("上次更新的时间"+new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis())));
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                getData();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pull.onRefreshComplete();
                    }
                },2000);
            }
        });


    }

    private void getData() {
        HttpUtils.getdata(path, MainActivity.this, new JsonBack() {
            @Override
            public void getjsondata(String json) {
                Gson gson = new Gson();
                Beans beans = gson.fromJson(json, Beans.class);
                List<Beans.ResultBean.DataBean> data = beans.getResult().getData();

                list.addAll(data);


            }
        });
    }
}
