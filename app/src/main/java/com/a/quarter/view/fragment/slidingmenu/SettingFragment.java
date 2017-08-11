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
import com.a.quarter.app.App;
import com.a.quarter.view.base.BaseFragment;
import com.exa.framelib_rrm.base.view.view.CircleImageView;
import com.exa.framelib_rrm.utils.T;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * desc:
 * author:吴晓芳
 * date:
 */

public class SettingFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.setting_button_exit)
    Button buttonExit;

    @Override
    protected int getContentViewId() {
        return R.layout.frag_setting;
    }

    @Override
    protected void initViews() {
        buttonExit.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {


//        recycler.setAdapter();

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.setting_button_exit:
                //退出登录
                if(App.isLogin()){
                    //清除SharedPrefrences中保存的用户信息
                    if(App.getInstance().clearUserInfo()) {
                        T.showShort(getActivity(), "已退出登录");
                    }else{
                        T.showShort(getActivity(), "退出登录失败");
                    }
                }else{
                    T.showShort(getActivity(), "未登录");
                }
                break;
            default:
                break;
        }
    }
}

