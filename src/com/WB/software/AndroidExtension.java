package com.wb.software;

// Compiler notes:
//   Right-click project, Properties
//   Java Build Path, Libraries
//   Add External Jars...
//   [Flash Builder 4.7 install]/eclipse/plugins/com.adobe.flash.compiler_xxx/
//     AIRSDK/lib/android/FlashRuntimeExtensions.jar

// To update JAR file:
//   Belongs in Release folder
//   Delete existing
//   Right-click project, Export...

// To build/rebuild ANE
//   ActionScript Build Packaging, Google Android
//   Native Extensions, uncheck Package, Apply
//   Rebuild using BAT script
//   Recheck Package, OK

// To first add ANE file:
//   Right-click project, Properties
//   ActionScript Build Path, Native Extensions
//   Add ANE...

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;

public class AndroidExtension implements FREExtension
{
	// createContext() -- required function
	@Override public FREContext createContext(String argv)
	{
		// return new context
		return(new AndroidContext());
	}
	
	// initialize() -- required function
	@Override public void initialize()
	{
		// nothing to do
	}
	
	// dispose() -- required function
	@Override public void dispose()
	{
		// nothing to do
	}
}