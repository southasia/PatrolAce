package com.zen.patrolace.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.zen.patrolace.R;
import com.zen.patrolace.util.CommonUtil;

public class MainActivity extends SherlockActivity {

	ImageView ivPerson;
	ImageView ivArticle;
	ImageView ivLocation;
	ImageView ivMessage;

	SherlockActivity mActivity;
	SharedPreferences mSharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initialize();
		findViewsById();
		setListeners();

	}

	private void initialize() {
		mActivity = this;
		mSharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(mActivity);
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
				CommonUtil.switchActivity(mActivity, LocationActivity.class, false);
				break;
			case R.id.iv_message:

				break;
			}
		}
	};

	private void findViewsById() {

		ivPerson = (ImageView) findViewById(R.id.iv_person);
		ivArticle = (ImageView) findViewById(R.id.iv_article);
		ivLocation = (ImageView) findViewById(R.id.iv_location);
		ivMessage = (ImageView) findViewById(R.id.iv_message);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
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
