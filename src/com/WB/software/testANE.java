package com.wb.software;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class testANE implements FREFunction
{
	// call() -- required function
	@Override public FREObject call(FREContext context, FREObject[] argv)
	{
		// attempt reply to caller
		try
		{
			// get passed value
			int val = argv[0].getAsInt();
			
			// ok
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