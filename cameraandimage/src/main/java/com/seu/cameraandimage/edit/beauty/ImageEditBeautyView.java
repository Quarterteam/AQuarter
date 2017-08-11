package com.seu.cameraandimage.edit.beauty;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;

import com.seu.cameraandimage.R;
import com.seu.cameraandimage.edit.ImageEditFragment;
import com.seu.cameraandimage.widget.TwoLineSeekBar;

public class ImageEditBeautyView extends ImageEditFragment {

	private RadioGroup mRadioGroup;
	private RelativeLayout mSkinSmoothView;
	private RelativeLayout mSkinColorView;
	private TwoLineSeekBar mSmoothBubbleSeekBar;
	private TwoLineSeekBar mWhiteBubbleSeekBar;
	private boolean mIsSmoothed = false;
	private boolean mIsWhiten = false;

	public ImageEditBeautyView() {
		super();
	}

	public ImageEditBeautyView(Context context) {
		super(context);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_image_edit_beauty, container, false);  
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		mSkinSmoothView = (RelativeLayout) getView().findViewById(R.id.fragment_beauty_skin);
		mSkinColorView = (RelativeLayout) getView().findViewById(R.id.fragment_beauty_color);
		mRadioGroup = (RadioGroup)getView().findViewById(R.id.fragment_beauty_radiogroup);
		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if (checkedId == R.id.fragment_beauty_btn_skinsmooth) {
					mSkinSmoothView.setVisibility(View.VISIBLE);
					mSkinColorView.setVisibility(View.GONE);

				} else if (checkedId == R.id.fragment_beauty_btn_skincolor) {
					mSkinColorView.setVisibility(View.VISIBLE);
					mSkinSmoothView.setVisibility(View.GONE);

				} else {
				}
			}
		});
		mSmoothBubbleSeekBar = (TwoLineSeekBar) view.findViewById(R.id.fragment_beauty_skin_seekbar);
		mWhiteBubbleSeekBar = (TwoLineSeekBar) view.findViewById(R.id.fragment_beauty_white_seekbar);
		init();
		super.onViewCreated(view, savedInstanceState);
	}
	
	private void init(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {

			}
		}).start();
	}
	
	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		if(!hidden){			

			init();
		}else{

		}
	}

	@Override
	protected boolean isChanged() {
		return mIsWhiten || mIsSmoothed;
	}

	@Override
	protected void onDialogButtonClick(DialogInterface dialog) {
		super.onDialogButtonClick(dialog);
	}
}