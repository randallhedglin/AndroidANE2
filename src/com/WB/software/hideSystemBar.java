package com.wb.software;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class hideSystemBar implements FREFunction,
									  View.OnSystemUiVisibilityChangeListener
{
	// stored context
	FREContext m_context = null;
	
	// system ui flags
	int m_sysUiFlags = 0;
	
	// init flag
	boolean m_listenerSet = false;
	
	// call() -- required function
	@Override public FREObject call(FREContext context, FREObject[] argv)
	{
		// store the context
		m_context = context;
		
		// get activity
		Activity activity = context.getActivity();
		
		// get decor view
		View decorView = activity.getWindow().getDecorView();
		
		// set fullscreen mode according to build version
		if(Build.VERSION.SDK_INT >= 19)
		{
			// set window flags appropriate for version
			int winFlags = WindowManager.LayoutParams.FLAG_FULLSCREEN             |
					 	   WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN       |
					 	   WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION |
					 	   WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS     ;
			
			// apply window flags
			activity.getWindow().addFlags(winFlags);

			// set ui flags appropriate for version
			m_sysUiFlags = View.SYSTEM_UI_FLAG_FULLSCREEN             |
						   View.SYSTEM_UI_FLAG_HIDE_NAVIGATION        |
						   View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY       |
						   View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN      |
						   View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
						   View.SYSTEM_UI_FLAG_LAYOUT_STABLE          |
						   View.SYSTEM_UI_FLAG_LOW_PROFILE;
			
			// update ui flags
			decorView.setSystemUiVisibility(m_sysUiFlags);
			
			// set listener
			setSysUiListener(decorView);
		}
		else if(Build.VERSION.SDK_INT >= 16)
		{
			// set window flags appropriate for version
			int winFlags = WindowManager.LayoutParams.FLAG_FULLSCREEN       |
					 	   WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN ;
			
			// apply window flags
			activity.getWindow().addFlags(winFlags);

			// set ui flags appropriate for version
			m_sysUiFlags = View.SYSTEM_UI_FLAG_FULLSCREEN             |
						   View.SYSTEM_UI_FLAG_HIDE_NAVIGATION        |
						   View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN      |
						   View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
						   View.SYSTEM_UI_FLAG_LAYOUT_STABLE          |
						   View.SYSTEM_UI_FLAG_LOW_PROFILE;
			
			// update ui flags
			decorView.setSystemUiVisibility(m_sysUiFlags);
			
			// set listener
			setSysUiListener(decorView);
		}
		else if(Build.VERSION.SDK_INT >= 14)
		{
			// set window flags appropriate for version
			int winFlags = WindowManager.LayoutParams.FLAG_FULLSCREEN       |
					 	   WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN ;
			
			// apply window flags
			activity.getWindow().addFlags(winFlags);

			// set ui flags appropriate for version
			m_sysUiFlags = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
						   View.SYSTEM_UI_FLAG_LOW_PROFILE;
			
			// update ui flags
			decorView.setSystemUiVisibility(m_sysUiFlags);
			
			// set listener
			setSysUiListener(decorView);
		}
		else
		{
			// set window flags appropriate for version
			int winFlags = WindowManager.LayoutParams.FLAG_FULLSCREEN       |
					 	   WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN ;
			
			// apply window flags
			activity.getWindow().addFlags(winFlags);
		}
		
		// ok
		return(null);
	}
	
	// onSystemUiVisibilityChange() -- callback for ui change events
	@Override public void onSystemUiVisibilityChange(int visibility)
	{
		// ignore notifications of our own changes
		if(visibility == m_sysUiFlags)
			return;
		
		// inform app
		m_context.dispatchStatusEventAsync("sysUiVisibilityChange", "general");
	}
	
	// setSysUiListener() --  set listener for system ui changes
	private void setSysUiListener(View decorView)
	{
		// check init flag
		if(m_listenerSet)
			return;
		
		// set listener
		decorView.setOnSystemUiVisibilityChangeListener(this);
		
		// set init flag
		m_listenerSet = true;
	}
}
