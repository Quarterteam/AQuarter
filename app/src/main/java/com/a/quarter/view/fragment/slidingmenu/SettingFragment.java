package com.a.quarter.view.fragment.slidingmenu;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.view.base.BaseFragment;
import com.exa.framelib_rrm.base.view.view.CircleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * desc:
 * author:吴晓芳
 * date:
 */

public class SettingFragment extends BaseFragment {

    @Bind(R.id.setting_button_exit)
    Button buttonExit;

    @Override
    protected int getContentViewId() {
        return R.layout.frag_setting;
    }

    @Override
    protected void initViews() {




    }

    @Override
    protected void initDatas() {


//        recycler.setAdapter();

    }


}

