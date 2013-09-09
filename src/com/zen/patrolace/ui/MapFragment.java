package com.zen.patrolace.ui;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.SherlockMapFragment;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.zen.patrolace.util.CommonUtil;

public class MapFragment extends SherlockMapFragment {
	
	

	private GoogleMap mMap;
	private SherlockFragmentActivity mActivity;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initialize();
	}
	
	// don't override OnCreateView
	
	private void initialize() {
		mActivity = getSherlockActivity();
		mMap = getMap();
		
		if (!CommonUtil.isGPSEnabled(mActivity))
			CommonUtil.promptEnableGPS(mActivity);
		else
			moveHere();
		
//		CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(
//				0, 130));
//		mMap.moveCamera(center);
		
//		LocationManager locationManager = (LocationManager) mActivity.getSystemService(Context.LOCATION_SERVICE);
//		
//		String locationProvider = LocationManager.NETWORK_PROVIDER;
		// Or use LocationManager.GPS_PROVIDER

//		Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
//		
//		double longitude = lastKnownLocation.getLongitude();
//		double latitude = lastKnownLocation.getLatitude();
//		
//		CameraUpdate here = CameraUpdateFactory.newLatLng(new LatLng(
//				latitude, longitude));
//		mMap.moveCamera(here);
//		mMap.animateCamera(here);
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
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		moveHere();
	}
}