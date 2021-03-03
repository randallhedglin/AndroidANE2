package com.wb.software;

import android.app.Activity;
import android.view.WindowManager;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class keepScreenOn implements FREFunction
{
	// call() -- required function
	@Override public FREObject call(FREContext context, FREObject[] argv)
	{
		// get main activity
		Activity activity = context.getActivity();
		
		// add screen lock
		activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		// ok
		return(null);
	}
}