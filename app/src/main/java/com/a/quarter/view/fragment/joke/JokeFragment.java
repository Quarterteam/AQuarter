package com.a.quarter.view.fragment.joke;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.a.quarter.R;
import com.a.quarter.model.bean.joke.JokeBean;
import com.a.quarter.presenter.joke.JokePresenter;
import com.a.quarter.view.adapter.joke.JokeAdapter;
import com.a.quarter.view.base.BaseFragment;
import com.exa.framelib_rrm.base.model.http.tag.BaseTag;
import com.exa.framelib_rrm.rx.RxCallback;
import com.exa.framelib_rrm.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 段子
 */
public class JokeFragment extends BaseFragment <JokePresenter,JokeFragment.JokeCallBack>{
    @Bind(R.id.joke_recycler)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.joke_progress)
    ProgressBar progressBar;
    private JokeAdapter adapter;
    private List<String> listNick=new ArrayList<>();
    private List<String> listBad=new ArrayList<>();
    private List<String> listForward=new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private int currentpag=1;
    private boolean flag=true;
    private static final byte TAG_DATA=1;
    private static final byte TAG_NICE=2;
    private static final byte TAG_FORWARD=3;
    private static final byte TAG_BAD=4;

    private MyHandler  myHandler = new MyHandler(JokeFragment.this);

    @Override
    protected int getContentViewId() {
        return R.layout.frag_joke;
    }

    @Override
    protected void initViews() {


    }

    @Override
    protected void initDatas() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new JokeAdapter(getActivity(),myHandler);
        mRecyclerView.setAdapter(adapter);
        bindPresenter(new JokePresenter(),new JokeCallBack(JokeFragment.this,getActivity()));
        mPresenter.getDataFrom(new BaseTag(TAG_DATA),currentpag);
        //下拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        mPresenter.getDataFrom(new BaseTag(TAG_DATA),currentpag);
                    }
                },3000);
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastitemPosition = linearLayoutManager.findLastVisibleItemPosition(); //获得最后一个条目数
                int itemCount = linearLayoutManager.getItemCount();//获得总条目数
                if (lastitemPosition+1==itemCount){
                    currentpag++;
                    progressBar.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);
                         mPresenter.getDataFrom(new BaseTag(TAG_DATA),currentpag);

                        }
                    },3000);
                }
            }
        });
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

    }

    static  class JokeCallBack extends RxCallback<Object,JokeFragment,BaseTag> {


        public JokeCallBack(JokeFragment host, Context mContext) {
            super(host, mContext);
        }

        @Override
        public String onCheckParamsLegality(BaseTag tag, Object... params) {
            return null;
        }

        @Override
        public void onRequestStart(BaseTag tag) {
        }

        @Override
        public void onRequestEnd(BaseTag tag) {
        }

        @Override
        protected void onDealNextResponse(Object response, BaseTag tag) {
            if (response!=null) {
            if (tag.tag== TAG_DATA){
                JokeBean jokeBean= (JokeBean) response;
                if (jokeBean.getCode().equals("200")){
                    LogUtils.i("数据请求成功！");
                 for (int i = 0; i <jokeBean.getCharacter().size() ; i++) {

                     jokeBean.getCharacter().get(i).setRigth(false);
                     jokeBean.getCharacter().get(i).setIscomment(false);
                     jokeBean.getCharacter().get(i).setNice(false);
                     jokeBean.getCharacter().get(i).setBad(false);
                     jokeBean.getCharacter().get(i).setIsforward(false);
                     getHost().listNick.add(jokeBean.getCharacter().get(i).getRedis_character_nice_key());
                     getHost().listBad.add(jokeBean.getCharacter().get(i).getRedis_character_bad_key());
                     getHost().listForward.add(jokeBean.getCharacter().get(i).getRedis_character_forward_key());
                 }
                   getHost().adapter.setData(jokeBean.getCharacter());
                    getHost().adapter.notifyDataSetChanged();
             }
            }else if (tag.tag== TAG_NICE){
                   Toast.makeText(mAppContext, "+1", Toast.LENGTH_SHORT).show();
                   String nick= (String) response;
                   try {
                       JSONObject jsonObject = new JSONObject(nick);
                       String nice_num = jsonObject.getString("nice_num");
                       getHost().adapter.getNickNum(nice_num);
                       getHost().adapter.notifyDataSetChanged();
                   } catch (JSONException e) {
                       e.printStackTrace();
                   }
                   getHost().adapter.notifyDataSetChanged();

            }else if (tag.tag== TAG_FORWARD){//转发
                String nick= (String) response;
                try {
                    JSONObject jsonObject = new JSONObject(nick);
                    String forward_num = jsonObject.getString("character_forward_num");
                    getHost().adapter.getForwardNum(forward_num);
                    getHost().adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                getHost().adapter.notifyDataSetChanged();
                }else if (tag.tag== TAG_BAD){//踩

            }
        }
        }

    }
    public class MyHandler extends Handler{
        WeakReference<JokeFragment> weakReference;
        public MyHandler(JokeFragment jokeFragment){
            weakReference =new WeakReference<JokeFragment>(jokeFragment);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:   //点赞
                {
                    int position = (int) msg.obj;
                    mPresenter.getDataFromNice(new BaseTag(TAG_NICE), listNick.get(position));
                }
                    break;
                case 1:   //转发
                {
                    int position = (int) msg.obj;
                    mPresenter.getDataFormForward(new BaseTag(TAG_FORWARD), listForward.get(position));
                }
                    break;
            }
        }
    }
}
