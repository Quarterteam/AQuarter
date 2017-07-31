package com.a.quarter.view.fragment.msginform;

import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

import com.a.quarter.R;
import com.a.quarter.view.base.BaseFragment;

import butterknife.Bind;

/**
 * 类的作用：
 * 实现思路 ：
 * auther:  郭鸽鸽
 * date ： 2017/7/27.
 */

public class PrivateLetterFragment extends BaseFragment {
    @Bind(R.id.iv_anim)
    ImageView mAnim;
    @Override
    protected int getContentViewId() {
        return R.layout.frag_private_letter;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        mAnim.setBackgroundResource(R.drawable.anim_frame_private);
        AnimationDrawable animationDrawable = (AnimationDrawable) mAnim.getBackground();
        animationDrawable.start();

    }
}
