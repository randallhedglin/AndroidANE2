package com.wb.software;

import android.app.Activity;
import android.view.View;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class getLongestDisplaySide implements FREFunction
{
	// call() -- required function
	@Override public FREObject call(FREContext context, FREObject[] argv)
	{
		// get main activity
		Activity activity = context.getActivity();
		
		// get decor view
		View decorView = activity.getWindow().getDecorView();
		
		// get width & height
		int w = decorView.getWidth();
		int h = decorView.getHeight();

		// set return value
		int val = (w > h) ? w : h;

		// attempt reply to caller
		try
		{
			// create & return object
			return(FREObject.newObject(val));
		}
		catch(Exception e)
		{
			// output error data
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
				
		// fail
		return(null);
	}
}
