package com.a.quarter.view.activity.compile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a.quarter.R;
import com.seu.cameraandimage.common.view.FilterLayoutUtils;
import com.seu.cameraandimage.common.view.edit.ImageEditFragment;
import com.seu.magicfilter.display.MagicImageDisplay;
import com.seu.magicfilter.filter.helper.MagicFilterType;

public class ImageEditFilterFragment extends ImageEditFragment{

	private NewFilterLayoutUtils mFilterLayoutUtils;

	public ImageEditFilterFragment() {
		super();
	}

	@SuppressLint("ValidFragment")
	public ImageEditFilterFragment(Context context, MagicImageDisplay magicDisplay) {
		super(context, magicDisplay);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_image_edit_filter_new, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		mFilterLayoutUtils = new NewFilterLayoutUtils(getActivity(), mMagicDisplay);
		mFilterLayoutUtils.init(getView());
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		if(!hidden)
			mFilterLayoutUtils.init(getView());
	}

	@Override
	protected boolean isChanged() {
		return mFilterLayoutUtils.getFilterType() != MagicFilterType.NONE;
	}
}
