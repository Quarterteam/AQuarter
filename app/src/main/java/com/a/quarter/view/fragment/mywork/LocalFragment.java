package com.a.quarter.view.fragment.mywork;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.a.quarter.R;
import com.a.quarter.view.adapter.mywork.LocationAdapter;
import com.a.quarter.view.base.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * desc:
 * author:吴晓芳
 * date:
 */

public class LocalFragment extends BaseFragment {
    @Bind(R.id.location_gridview2)
    RecyclerView recyclerView;

    @Override
    protected int getContentViewId() {
        return R.layout.frag_location;
    }

    @Override
    protected void initViews() {

        recyclerView.setLayoutManager(
                new GridLayoutManager(mActivity,3));

        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.mipmap.anim3);
        list.add(R.mipmap.anim2);
        list.add(R.mipmap.anim3);
        list.add(R.mipmap.anim3);
        list.add(R.mipmap.anim2);
        list.add(R.mipmap.anim1);

        LocationAdapter adapter = new LocationAdapter(mActivity);
        recyclerView.setAdapter(adapter);

        adapter.setData(list);
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void initDatas() {

    }

}
