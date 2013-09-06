package com.zen.patrolace.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public class CommonUtil {

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
	
	public static void switchActivity(Activity thisActivity, Class<?> nextActivityClass, boolean finishThis)
	{
		Intent intent = new Intent(thisActivity, nextActivityClass);
		thisActivity.startActivity(intent);
		if (finishThis)
			thisActivity.finish();
	}
	
	public static void hideKeyboard(Activity activity) {

        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
}