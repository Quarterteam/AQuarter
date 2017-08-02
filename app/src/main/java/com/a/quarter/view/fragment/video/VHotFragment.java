package com.a.quarter.view.fragment.video;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.a.quarter.R;
import com.a.quarter.model.bean.video.VHotBean;
import com.a.quarter.presenter.video.VHotPresenter;
import com.a.quarter.view.adapter.video.VHotFragmentAdapter;
import com.a.quarter.view.base.BaseFragment;
import com.exa.framelib_rrm.base.model.http.tag.BaseTag;
import com.exa.framelib_rrm.rx.RxCallback;

import java.util.ArrayList;

import butterknife.Bind;
import media.AndroidMediaController;

/**
 * 王 ：王万鹏
 * & 作用  ：
 * & 思路  ：
 */

public class VHotFragment extends BaseFragment<VHotPresenter,VHotFragment.VHotCallback>{
    @Bind(R.id.vhot_RecyclerView)
    RecyclerView mRecyclerView;
    private ArrayList<Integer> list=new ArrayList<>();
    private AndroidMediaController mMediaController;

    @Override
    protected int getContentViewId() {
        return R.layout.vhotfrag_video;
    }

    @Override
    protected void initViews() {
        // RecycleView 使用的什么样的布局方式
        GridLayoutManager manager=new GridLayoutManager(getActivity(),2);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

        // TODO: 添加集合
        for (int i = 0; i < 10; i++) {
           //int ic_launcher = R.mipmap.ic_launcher;
            list.add(R.mipmap.ic_launcher);
        }

        // TODO: 设置适配器
        VHotFragmentAdapter adapter = new VHotFragmentAdapter(getActivity(),list);
        mRecyclerView.setAdapter(adapter);// No adapter attached; skipping layout
         adapter.notifyDataSetChanged();



    }

    @Override
    protected void initDatas() {

        VHotCallback hotCallback = new VHotCallback(this, getContext().getApplicationContext());
        bindPresenter(new VHotPresenter(),hotCallback);

        mPresenter.Vhot();

    }

    static class VHotCallback extends RxCallback<VHotBean, VHotFragment, BaseTag> {

        public VHotCallback(VHotFragment host, Context mContext) {
            super(host, mContext);
        }



        @Override
        public String onCheckParamsLegality(BaseTag tag, Object... params) {
            return null;
        }

        @Override
        public void onRequestStart(BaseTag tag) {
//            Log.i("        onRequestStart","======================" +
//                    "============================" +
//                    "==================================");
        }

        @Override
        public void onRequestEnd(BaseTag tag) {
//            Log.i("        onRequestEnd","======================" +
//                    "============================" +
//                    "==================================");
        }

        @Override
        protected void onDealNextResponse(VHotBean response, BaseTag tag) {
            String string = response.toString();
//            Log.i("        ============",string);
//            Log.i("        ============","======================" +
//                    "============================" +
//                    "==================================");
        }
    }
}
