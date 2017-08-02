package com.a.quarter.view.activity.mycollect;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.model.bean.collect.MyCollectItemBean;
import com.a.quarter.view.adapter.mycollect.MyCollectAdapter;
import com.a.quarter.view.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 类的作用：
 * 实现思路 ：
 * auther:  郭鸽鸽
 * date ： 2017/7/28.
 */

public class MyCollectActivity extends BaseActivity implements View.OnClickListener{

    @Bind(R.id.tv_back)
    TextView mtvBack;
    @Bind(R.id.tv_head)
    TextView mTvHead;
    @Bind(R.id.tv_right)//删除
    TextView mDelete;
    @Bind(R.id.collect_recycler)
    RecyclerView recyclerView;
    @Bind(R.id.collect_recycler_delete)//删除
    TextView mTvDelete;
    @Bind(R.id.collect_recycler_cancel)//取消
    TextView mCancel;
    private LinearLayoutManager linearLayoutManager;
    private MyCollectAdapter adapter;
    List<MyCollectItemBean>list=new ArrayList<>();
    @Override
    protected int getContentViewId() {
        return R.layout.activity_mycollect;
    }

    @Override
    protected void initViews() {
        mtvBack.setOnClickListener(this);
        mDelete.setOnClickListener(this);
        mTvDelete.setOnClickListener(this);
        mCancel.setOnClickListener(this);
        mTvHead.setText("我的收藏");
        mDelete.setText("删除");
    }

    @Override
    protected void initDatas() {
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MyCollectAdapter(this);
        recyclerView.setAdapter(adapter);
        for (int i = 0; i <2 ; i++) {
            MyCollectItemBean myCollectItemBean = new MyCollectItemBean(false,false,false);
            list.add(myCollectItemBean);
        }
         adapter.setData(list);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View view) {
      switch (view.getId()){
          case R.id.tv_back:
              finish();
              break;
           case R.id.tv_right://点击删除 显示编辑框
               for (int i = 0; i <2 ; i++) {
                   list.get(i).setVisibility(true);
               }
               adapter.notifyDataSetChanged();
               mTvDelete.setVisibility(View.VISIBLE);
               mCancel.setVisibility(View.VISIBLE);
          break;
           case R.id.collect_recycler_delete:
               break;
           case R.id.collect_recycler_cancel://取消隐藏删除条
               for (int i = 0; i <2 ; i++) {
                   list.get(i).setVisibility(false);
               }
               adapter.notifyDataSetChanged();
               mTvDelete.setVisibility(View.GONE);
               mCancel.setVisibility(View.GONE);

          break;

      }
    }
}
