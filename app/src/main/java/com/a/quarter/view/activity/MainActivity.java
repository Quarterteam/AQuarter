package com.a.quarter.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.a.quarter.R;
import com.a.quarter.view.base.BaseActivity;
import com.a.quarter.view.fragment.joke.JokeFragment;
import com.a.quarter.view.fragment.recommend.RecommendFragment;
import com.a.quarter.view.fragment.video.VideoFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

//    @Bind(R.id.radioGroupNav)
    private RadioGroup radioGroupNav;

    private RecommendFragment recommendFragment;
    private JokeFragment jokeFragment;
    private VideoFragment videoFragment;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
//        radioGroupNav = (RadioGroup)findViewById(R.id.radioGroupNav);
//        radioGroupNav.setOnCheckedChangeListener(this);
//        ((RadioButton)findViewById(R.id.radioButtonRecommend)).setChecked(true);
    }

    @Override
    protected void initDatas() {

    }

    private FragmentTransaction t;
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        t = getSupportFragmentManager().beginTransaction();
        switch (checkedId) {
            case R.id.radioButtonRecommend:
                showFrag(0);
                hideFrag(1);
                hideFrag(2);
                break;

            case R.id.radioButtonJoke:
                showFrag(1);
                hideFrag(0);
                hideFrag(2);
                break;

            case R.id.radioButtonVideo:
                showFrag(2);
                hideFrag(0);
                hideFrag(1);
                break;

            default:
                break;
        }
        t.commit();
    }

    private void showFrag(int i) {
        switch (i) {
            case 0:
                if (recommendFragment == null) {
                    recommendFragment = new RecommendFragment();
                    t.add(R.id.frag_container, recommendFragment, "recommend");
                }
                t.show(recommendFragment);
                break;
            case 1:
                if (jokeFragment == null) {
                    jokeFragment = new JokeFragment();
                    t.add(R.id.frag_container, jokeFragment, "joke");
                }
                t.show(jokeFragment);
                break;
            case 2:
                if (videoFragment == null) {
                    videoFragment = new VideoFragment();
                    t.add(R.id.frag_container, videoFragment, "video");
                }
                t.show(videoFragment);
                break;
            default:
                break;
        }
    }

    private void hideFrag(int i) {
        switch (i) {
            case 0:
                if (recommendFragment != null) {
                    t.hide(recommendFragment);
                }
                break;
            case 1:
                if (jokeFragment != null) {
                    t.hide(jokeFragment);
                }
                break;
            case 2:
                if (videoFragment != null) {
                    t.hide(videoFragment);
                }
                break;
            default:
                break;
        }
    }

}
