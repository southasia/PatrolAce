package com.zen.patrolace.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.zen.patrolace.R;
import com.zen.patrolace.util.CommonUtil;

public class HomeFragment extends SherlockFragment {
	
	SherlockFragmentActivity mActivity;
	View mView;
	
	ImageView ivPerson;
	ImageView ivArticle;
	ImageView ivLocation;
	ImageView ivMessage;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		mView = inflater.inflate(R.layout.activity_main, container, false); 
		initialize();
		findViewsById();
		setListeners();
		
		return mView;
	}
	
	private void initialize() {
		mActivity = getSherlockActivity();
	}

	private void setListeners() {

		ivPerson.setOnClickListener(onClickListener);
		ivArticle.setOnClickListener(onClickListener);
		ivLocation.setOnClickListener(onClickListener);
		ivMessage.setOnClickListener(onClickListener);
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			case R.id.iv_person:

				break;
			case R.id.iv_article:

				break;
			case R.id.iv_location:
//				CommonUtil.switchActivity(mActivity, LocationActivity.class, false);
				
				CommonUtil.switchFragment(mActivity, new LocationFragment());
				break;
			case R.id.iv_message:

				break;
			}
		}
	};

	private void findViewsById() {

		ivPerson = (ImageView) mView.findViewById(R.id.iv_person);
		ivArticle = (ImageView) mView.findViewById(R.id.iv_article);
		ivLocation = (ImageView) mView.findViewById(R.id.iv_location);
		ivMessage = (ImageView) mView.findViewById(R.id.iv_message);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		inflater.inflate(R.menu.main, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.mi_logout:
			CommonUtil.recordLogout(mActivity);
			CommonUtil.switchActivity(mActivity, LoginActivity.class, true);
			break;
		}

		return super.onOptionsItemSelected(item);
	}

}
