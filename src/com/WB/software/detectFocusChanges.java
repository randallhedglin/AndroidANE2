package com.wb.software;

import android.app.Activity;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.accessibility.AccessibilityEvent;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class detectFocusChanges implements FREFunction,
										   Window.Callback
{
	// stored context
	FREContext m_context = null;
	
	// original window callback	
	Window.Callback m_origCallback = null;
	
	// call() -- required function
	@Override public FREObject call(FREContext context, FREObject[] argv)
	{
		// store the context
		m_context = context;
		
		// get activity
		Activity activity = context.getActivity();
		
		// save original callback
		m_origCallback = activity.getWindow().getCallback();
		
		// hijack it!
		activity.getWindow().setCallback(this);
		
		// ok
		return(null);
	}

	// onWindowFocusChanged() -- override to inform app of focus changes
	@Override public void onWindowFocusChanged(boolean hasFocus)
	{
		// relay back to ActionScript
		m_context.dispatchStatusEventAsync("onWindowFocusChanged", hasFocus ? "true" : "false");
		
		// pass to original callback
		m_origCallback.onWindowFocusChanged(hasFocus);
	}

	// override all other methods by passing to original callback
	@Override public boolean dispatchGenericMotionEvent(MotionEvent event) { return m_origCallback.dispatchGenericMotionEvent(event); }
	@Override public boolean dispatchKeyEvent(KeyEvent event) { return m_origCallback.dispatchKeyEvent(event); }
	@Override public boolean dispatchKeyShortcutEvent(KeyEvent event) { return m_origCallback.dispatchKeyShortcutEvent(event); }
	@Override public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) { return m_origCallback.dispatchPopulateAccessibilityEvent(event); }
	@Override public boolean dispatchTouchEvent(MotionEvent event) { return m_origCallback.dispatchTouchEvent(event); }
	@Override public boolean dispatchTrackballEvent(MotionEvent event) { return m_origCallback.dispatchTrackballEvent(event); }
	@Override public void onActionModeFinished(ActionMode mode) { m_origCallback.onActionModeFinished(mode); }
	@Override public void onActionModeStarted(ActionMode mode) { m_origCallback.onActionModeStarted(mode); }
	@Override public void onAttachedToWindow() { m_origCallback.onAttachedToWindow(); }
	@Override public void onContentChanged() { m_origCallback.onContentChanged(); }
	@Override public boolean onCreatePanelMenu(int featureId, Menu menu) { return m_origCallback.onCreatePanelMenu(featureId, menu); }
	@Override public View onCreatePanelView(int featureId) { return m_origCallback.onCreatePanelView(featureId); }
	@Override public void onDetachedFromWindow() { m_origCallback.onDetachedFromWindow(); }
	@Override public boolean onMenuItemSelected(int featureId, MenuItem item) { return m_origCallback.onMenuItemSelected(featureId, item); }
	@Override public boolean onMenuOpened(int featureId, Menu menu) { return m_origCallback.onMenuOpened(featureId, menu); }
	@Override public void onPanelClosed(int featureId, Menu menu) { m_origCallback.onPanelClosed(featureId, menu); }
	@Override public boolean onPreparePanel(int featureId, View view, Menu menu) { return m_origCallback.onPreparePanel(featureId, view, menu); }
	@Override public boolean onSearchRequested() { return m_origCallback.onSearchRequested(); }
	@Override public boolean onSearchRequested(SearchEvent searchEvent) { return m_origCallback.onSearchRequested(searchEvent); }
	@Override public void onWindowAttributesChanged(LayoutParams attrs) { m_origCallback.onWindowAttributesChanged(attrs); }
	@Override public ActionMode onWindowStartingActionMode(Callback callback) { return m_origCallback.onWindowStartingActionMode(callback); }
	@Override public ActionMode onWindowStartingActionMode(Callback callback, int type) { return m_origCallback.onWindowStartingActionMode(callback, type); }
}
