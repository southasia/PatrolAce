package com.zen.patrolace.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.SherlockMapFragment;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.zen.patrolace.R;
import com.zen.patrolace.util.CommonUtil;

public class MapFragment extends SherlockMapFragment {
	
	private GoogleMap mMap;
	private SherlockFragmentActivity mActivity;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//		View root = super.onCreateView(inflater, container, savedInstanceState);
		
		View root = inflater.inflate(R.id.map, container, false);
		
		initialize();
		
		if (CommonUtil.isGPSEnabled(mActivity))
			CommonUtil.promptEnableGPS(mActivity);
		else
			moveHere();
		
		return root;
	}
	
	private void initialize() {
		mActivity = getSherlockActivity();
		mMap = getMap();
	}

	private void moveHere()
	{
		double[] coordinate = CommonUtil.getLocationCoordinate(mActivity);
		
		CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(
				coordinate[0], coordinate[1]));
		CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);

		mMap.moveCamera(center);
		mMap.animateCamera(zoom);
	}
	
	
	
}