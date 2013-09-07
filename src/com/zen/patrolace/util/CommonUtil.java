package com.zen.patrolace.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.SherlockMapFragment;
import com.zen.patrolace.R;

public class CommonUtil {

	private static String KEY_IS_LOGGED_IN = "isLoggedIn";

	public static void showMessage(Context context, String message) {
		Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	public static void showMessage(Context context, int messageResId) {
		Toast toast = Toast.makeText(context, messageResId, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	/**
	 * Switch activity
	 * 
	 * @param thisActivity
	 * @param nextActivityClass
	 * @param finishThis
	 */
	public static void switchActivity(Activity thisActivity,
			Class<?> nextActivityClass, boolean finishThis) {
		Intent intent = new Intent(thisActivity, nextActivityClass);
		thisActivity.startActivity(intent);
		if (finishThis)
			thisActivity.finish();
	}

	/**
	 * Switch activity with animation
	 * 
	 * @param thisActivity
	 * @param nextActivityClass
	 * @param finishThis
	 * @param animIn
	 * @param animOut
	 */
	public static void switchActivity(Activity thisActivity,
			Class<?> nextActivityClass, boolean finishThis, int animIn,
			int animOut) {
		Intent intent = new Intent(thisActivity, nextActivityClass);
		thisActivity.startActivity(intent);
		thisActivity.overridePendingTransition(animIn, animOut);
		if (finishThis)
			thisActivity.finish();
	}

	public static void switchFragment(SherlockFragmentActivity activity,
			SherlockFragment fragment) {
		FragmentManager fragmentManager = activity.getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.container, fragment)
				.commit();
	}

	public static void switchFragment(SherlockFragmentActivity activity,
			SherlockMapFragment fragment) {
		FragmentManager fragmentManager = activity.getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.container, fragment)
				.commit();
	}

	/**
	 * Hide keyboard
	 * 
	 * @param activity
	 */
	public static void hideKeyboard(Activity activity) {

		InputMethodManager inputMethodManager = (InputMethodManager) activity
				.getSystemService(Activity.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus()
				.getWindowToken(), 0);
	}

	/**
	 * Determine whether there is user logged in
	 * 
	 * @param context
	 * @return login state
	 */
	public static boolean isLoggedIn(Context context) {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
	}

	/**
	 * Record login state in shared preferences
	 * 
	 * @param context
	 */
	public static void recordLogin(Context context) {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		sharedPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, true).commit();
	}

	/**
	 * Record logout state in shared preferences
	 * 
	 * @param context
	 */
	public static void recordLogout(Context context) {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		sharedPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, false).commit();
	}

	public static boolean isGPSEnabled(Context context) {
		LocationManager manager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}

	public static void promptEnableGPS(final Context context) {

		final AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(
				"Your GPS seems to be disabled, do you want to enable it?")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(final DialogInterface dialog,
									final int id) {
								context.startActivity(new Intent(
										android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(final DialogInterface dialog,
							final int id) {
						dialog.cancel();
					}
				});
		final AlertDialog alert = builder.create();
		alert.show();

	}

	public static double[] getLocationCoordinate(Context context) {
		// Get the location manager
		double[] coordinate = new double[2];
		LocationManager locationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		String bestProvider = locationManager.getBestProvider(criteria, false);
		Location location = locationManager.getLastKnownLocation(bestProvider);

		try {
			coordinate[0] = location.getLatitude();
			coordinate[1] = location.getLongitude();
		} catch (NullPointerException e) {
			coordinate[0] = -1.0;
			coordinate[1] = -1.0;
		}

		return coordinate;
	}
}