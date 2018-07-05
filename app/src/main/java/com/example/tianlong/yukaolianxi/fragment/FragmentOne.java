package com.example.tianlong.yukaolianxi.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.tianlong.yukaolianxi.R;
import com.example.tianlong.yukaolianxi.adapter.PullAdapter;
import com.example.tianlong.yukaolianxi.beans.Beans;
import com.example.tianlong.yukaolianxi.utils.HttpUtils;
import com.example.tianlong.yukaolianxi.utils.JsonBack;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class FragmentOne extends Fragment {
    private String url = "http://www.wanandroid.com/tools/mockapi/6523/hotnews_";
    private int page = 1;
    private  List<Beans.DataBeanX.DataBean> list2 = new ArrayList<>();
    private PullToRefreshListView pullToRefreshListView ;
    private Handler handler = new Handler();
    private PullAdapter pullAdapter ;

    public static  Fragment getInStance(String title){
        FragmentOne fragmentOne = new FragmentOne();

        Bundle bundle = new Bundle();

        bundle.putString("title",title);

        fragmentOne.setArguments(bundle);

        return fragmentOne;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentone,container,false);
        //获得组件
        initview(view);
        return view;
    }

    private void initview(View view) {
        pullToRefreshListView = view.findViewById(R.id.aaa);
        pullToRefreshListView.setMode(PullToRefreshListView.Mode.BOTH);
        ILoadingLayout start = pullToRefreshListView.getLoadingLayoutProxy(true, false);
        start.setPullLabel("下拉刷新");
        start.setRefreshingLabel("正在刷新");
        start.setReleaseLabel("刷新完成");
        ILoadingLayout end = pullToRefreshListView.getLoadingLayoutProxy(false, true);
        end.setPullLabel("上拉加载");
        end.setRefreshingLabel("正在加载");
        end.setReleaseLabel("加载完成");

        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                page = 1 ;
                getData();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefreshListView.onRefreshComplete();
                    }
                },2000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                page ++ ;
                getData();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefreshListView.onRefreshComplete();
                    }
                },2000);
            }
        });

        pullAdapter = new PullAdapter(list2,getActivity());
        pullToRefreshListView.setAdapter(pullAdapter);

    }
    //定位展示
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments.get("title").equals("热评")){
            getData();
        }
    }
    //获得数据
    public void getData() {
            final String path = url+page;
            HttpUtils.getdata(path, getActivity(), new JsonBack() {
                @Override
                public void getjsondata(String json) {
                    Gson gson = new Gson();
                    Beans beans = gson.fromJson(json, Beans.class);

                    List<Beans.DataBeanX.DataBean> data = beans.getData().getData();
//                    if (page == 0){
//                       data.clear();
//                    }
                    list2.addAll(data);

                    pullAdapter.notifyDataSetChanged();
                }
            });
    }
}
