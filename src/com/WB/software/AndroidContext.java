package com.wb.software;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;

import java.util.HashMap;
import java.util.Map;

public class AndroidContext extends FREContext
{
	// getFunctions() -- required function
	@Override public Map<String, FREFunction> getFunctions()
	{
		// create function map
		Map<String, FREFunction> functionMap = new HashMap<String, FREFunction>();
		
		// add functions
		functionMap.put("detectFocusChanges"   , new detectFocusChanges());
		functionMap.put("getLongestDisplaySide", new getLongestDisplaySide());
		functionMap.put("hideSystemBar"        , new hideSystemBar());
		functionMap.put("keepScreenOn"         , new keepScreenOn());
		functionMap.put("messageBox"           , new messageBox());
		functionMap.put("testANE"              , new testANE());
		
		// return map
		return(functionMap);
	}

	// dispose() -- required function
	@Override public void dispose()
	{
		// nothing to do
	}
}
