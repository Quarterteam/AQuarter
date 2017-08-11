package com.a.quarter.view.activity.main;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.app.App;
import com.a.quarter.model.bean.main.FindUserResponse;
import com.a.quarter.model.bean.main.MultiTypeItemBean;
import com.a.quarter.model.bean.main.SearchFriendBean;
import com.a.quarter.presenter.main.FollowPresenter;
import com.a.quarter.utils.SearchFriendHistoryUtils;
import com.a.quarter.view.adapter.main.SearchFriendListAdapter;
import com.a.quarter.view.base.BaseActivity;
import com.exa.framelib_rrm.base.model.http.tag.BaseTag;
import com.exa.framelib_rrm.base.model.http.tag.PositionTag;
import com.exa.framelib_rrm.base.model.http.tag.SinglePositionTag;
import com.exa.framelib_rrm.rx.RxCallback;
import com.exa.framelib_rrm.utils.T;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.OnClick;

//搜索好友页面
//http://192.168.1.100/quarter/user/findUserBy
//6.查询用户信息接口（根据条件查询用户查询用户）
//如果关键字是中文的话，提交给服务器时需要进行转码，不然会出现HTTP 500 Internal Server Error，怎么转码参考FollowPresenter的findUserByKeyword方法
public class SearchFriendActivity extends BaseActivity<FollowPresenter, SearchFriendActivity.FindUserByCallback> implements View.OnClickListener, SearchFriendListAdapter.OnItemClickListener {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_head)
    TextView tvHead;
    //@Bind(R.id.tv_cancel)
    //TextView tvCancel;
    @Bind(R.id.et_search)
    EditText etSearch;
    @Bind(R.id.iv_search)
    ImageView ivSearch;
    @Bind(R.id.rv)
    RecyclerView rv;
    private ArrayList<MultiTypeItemBean> list;
    private SearchFriendListAdapter adapter;
    private static final byte TAG_FIND_USER_BY = 1;
    private static final byte TAG_ADD_CONCERN = 2;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_search_friend;
    }

    @Override
    protected void initViews() {
        tvHead.setText("搜索好友");
        //初始化列表控件
        rv.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<MultiTypeItemBean>();
        //查询搜索历史记录
        Map<String, ?> historys = SearchFriendHistoryUtils.getAllHistorys();
        SearchFriendBean bean;
        if(historys!=null && !historys.isEmpty()){//如果有搜索的历史记录的话，显示出来
            list.add(new MultiTypeItemBean(SearchFriendListAdapter.TYPE_HISTORY_TITLE));
            Set<String> keywords = historys.keySet();
            for (String keyword: keywords) {
                bean = new SearchFriendBean();
                bean.itemType = SearchFriendListAdapter.TYPE_HISTORY;
                bean.userName = keyword;
                bean.userHead = (String) historys.get(keyword);
                list.add(bean);
            }
        }
        //感兴趣的用户，假数据
        list.add(new MultiTypeItemBean(SearchFriendListAdapter.TYPE_INTEREST_TITLE));
        for (int i = 0; i < 5; i++) {
            bean = new SearchFriendBean();
            bean.itemType = SearchFriendListAdapter.TYPE_INTEREST;
            bean.userName = "感兴趣的用户"+i;
            //bean.userHead = null;
            bean.userPhone = "我爱美食";
            list.add(bean);
        }
        adapter = new SearchFriendListAdapter(this, list);
        adapter.setOnItemClickListener(this);
        rv.setAdapter(adapter);
    }

    @Override
    protected void initDatas() {
        bindPresenter(new FollowPresenter(), new FindUserByCallback(this, getApplicationContext()));
        //mPresenter.findUserByKeyword("");

        //初始化数据
//        list.add(new MultiTypeItemBean(SearchFriendListAdapter.TYPE_HISTORY_TITLE));
//        for (int i = 0; i < 5; i++) {
//            list.add(new SearchFriendHistory("历史记录"+i));
//        }
//        list.add(new MultiTypeItemBean(SearchFriendListAdapter.TYPE_INTEREST_TITLE));
//        for (int i = 0; i < 5; i++) {
//            list.add(new SearchFriendInterest("感兴趣的用户"+i, "我爱美食"));
//        }
//        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.iv_back, R.id.iv_search})//, R.id.tv_cancel
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_back:
                finish();
                break;

            case R.id.tv_cancel:
                T.showShort(getApplicationContext(), "取消");
                break;

            case R.id.iv_search:
                String keyword = etSearch.getText().toString().trim();
                if(!TextUtils.isEmpty(keyword)){
                    T.showShort(getApplicationContext(), "开始搜索");
                    //保存该搜索记录到SharedPrefrences
                    saveHistory(keyword);
                    //TODO 清空历史记录，点击历史记录搜索，关注
                    //根据关键字，请求网络数据，搜索其他用户
                    mPresenter.findUserByKeyword(new BaseTag(TAG_FIND_USER_BY), keyword);
                }else{
                    T.showShort(getApplicationContext(), "还没输入关键字");
                }
                break;

            default:
                break;
        }
    }

    //保存一条搜索记录到SharedPrefrences
    private void saveHistory(String keyword) {
        if(SearchFriendHistoryUtils.saveHistory(keyword, null)){
            //如果正在显示历史记录
            if(list.get(0).itemType==SearchFriendListAdapter.TYPE_HISTORY_TITLE){
                MultiTypeItemBean bean = null;
                //boolean alreadyInHistory = false;//原来该关键字是否已经在历史记录中
                int alreadyInHistory = -1;//原来该关键字是否已经在历史记录中
                for (int i = 1; i < list.size(); i++) {
                    bean = list.get(i);
                    if(bean.itemType == SearchFriendListAdapter.TYPE_HISTORY){
                        if(((SearchFriendBean)bean).userName.equals(keyword)){
                            //alreadyInHistory = true;
                            alreadyInHistory = i;
                            break;
                        }
                    }else{
                        break;
                    }
                }
                //if(!alreadyInHistory) {//如果该关键字原来不在历史记录中，向集合中添加一条搜索记录
                if(alreadyInHistory==-1) {//如果该关键字原来不在历史记录中，向集合中添加一条搜索记录
                    SearchFriendBean historyBean = new SearchFriendBean();
                    historyBean.itemType = SearchFriendListAdapter.TYPE_HISTORY;
                    historyBean.userName = keyword;
                    list.add(1, historyBean);
                    adapter.notifyItemInserted(1);
                }else if(alreadyInHistory!=1){//如果已经存在，并且原来不是第一个历史记录的话
                    //把该历史记录在集合中的顺序调整到所有历史记录的第一个
                    bean = list.get(alreadyInHistory);
                    list.remove(alreadyInHistory);
                    adapter.notifyItemRemoved(alreadyInHistory);
                    list.add(1, bean);
                    adapter.notifyItemInserted(1);
                }
            }

        }
    }

    @Override
    public void onItemClick(int itemType, View v, int position) {
        if(itemType == SearchFriendListAdapter.TYPE_HISTORY){
            //点击了一条历史记录，根据该历史记录进行搜索
            mPresenter.findUserByKeyword(new BaseTag(TAG_FIND_USER_BY),((SearchFriendBean)list.get(position)).userName);
            //把该条历史记录对应的关键字显示EditText上
            etSearch.setText(((SearchFriendBean)list.get(position)).userName);
            //把该历史记录在集合中的顺序调整到所有历史记录的第一个，如果本来不是第一个的话
            if(position!=1){
                MultiTypeItemBean bean = list.get(position);
                list.remove(position);
                adapter.notifyItemRemoved(position);
                list.add(1, bean);
                adapter.notifyItemInserted(1);
            }
        }else if(itemType == SearchFriendListAdapter.TYPE_INTEREST){//点击了感兴趣的用户条目或者搜索结果条目
            if(v.getId()==R.id.tv_follow){//代表点击的是条目上的关注按钮
                //判断是否已登录，请求网络，添加关注
                if(App.isLogin()){
                    //5.用户添加关注接口（根据当前登陆用户id以及被关注用户id进行关注）
                    //http://192.168.1.100/quarter/user/addConcern
                    SinglePositionTag tag = new SinglePositionTag(TAG_ADD_CONCERN, position);
                    mPresenter.addConcern(tag, App.getUser().userId, ((SearchFriendBean)list.get(position)).userId);
                }else{
                    T.showShort(getApplicationContext(), "未登录");
                }
            }
        }
    }

    static class FindUserByCallback extends RxCallback<Object, SearchFriendActivity, BaseTag>{

        public FindUserByCallback(SearchFriendActivity host, Context mContext) {
            super(host, mContext);
        }

        @Override
        protected void onDealNextResponse(Object obj, BaseTag tag) {
            if(tag!=null){
                if(tag.tag == getHost().TAG_FIND_USER_BY){
                    FindUserResponse response = (FindUserResponse) obj;
                    if("200".equals(response.code)){
                        ArrayList<SearchFriendBean> users = response.user;
                        if(users!=null && !users.isEmpty()){
                            getHost().list.clear();
                            //getHost().list.add(new MultiTypeItemBean(SearchFriendListAdapter.TYPE_INTEREST_TITLE));
                            SearchFriendBean user;
                            for (int i = 0; i < users.size(); i++) {
                                user = users.get(i);
                                user.itemType = SearchFriendListAdapter.TYPE_INTEREST;
                                getHost().list.add(user);
                            }
                            getHost().adapter.notifyDataSetChanged();
                        }else{
                            T.showShort(mAppContext, "没有符合条件的");
                        }
                    }else{
                        T.showShort(mAppContext, "搜索失败，"+response.code);
                    }
                    //HTTP 500 Internal Server Error
                    //是因为传入的keyword是中文，所以乱码了吗？
                }else if(tag.tag == getHost().TAG_ADD_CONCERN){
                    String json = (String) obj;
                    if(json!=null && json.contains("\"code\":\"200\"")){
                        //关注成功，改变对应的条目上的关注按钮文字为已关注
                        SinglePositionTag poTag = (SinglePositionTag) tag;
                        ((SearchFriendBean)getHost().list.get(poTag.position)).isAlreadyFollowed = true;
                        getHost().adapter.notifyItemChanged(poTag.position);
                    }else{
                        T.showShort(mAppContext, "关注失败，json="+json);
                    }
                }
            }

        }

        @Override
        public String onCheckParamsLegality(BaseTag tag, Object... params) {
            if(tag!=null){
                if(tag.tag == getHost().TAG_ADD_CONCERN){
                    //检查两个id是否都合法
                    if(((int)params[0])==-1){
                        return "userId无效";
                    }
                    if(((int)params[1])==-1){
                        return "被关注的userId无效";
                    }
                }
            }
            return null;
        }

        @Override
        public void onRequestStart(BaseTag tag) {

        }

        @Override
        public void onRequestEnd(BaseTag tag) {

        }
    }
}
