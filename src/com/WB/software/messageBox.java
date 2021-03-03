package com.wb.software;

import android.app.Activity;
import android.app.AlertDialog;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class messageBox implements FREFunction
{
	// call() -- required function
	@Override public FREObject call(FREContext context, FREObject[] argv)
	{
		// get main activity
		Activity activity = context.getActivity();
		
		// verify objects
		if(argv.length == 2)
		{
			// create alert dialog
			AlertDialog alertDlg = (new AlertDialog.Builder(activity)).create();
			
			// formulate alert
			try
			{
				// set title & message
				alertDlg.setTitle  (argv[0].getAsString());
				alertDlg.setMessage(argv[1].getAsString());

				// display alert
				alertDlg.show();
			}
			catch(Exception e)
			{
				// output error data
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		
		// ok
		return(null);
	}
}
