/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2013 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package eu.rebelcorp.parse;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import java.util.Set;
import org.appcelerator.kroll.KrollDict;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import eu.rebelcorp.parse.ParseModule;


public class ParseModuleBroadcastReceiver extends BroadcastReceiver {
	private static final String TAG = "ParseModuleBroadcastReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {

		try {

			if (intent == null) {
				Log.d(TAG, "Receiver intent null");
			} else {
				String action = intent.getAction();
				Log.d(TAG, "got action " + action );

				if (action.equals("com.google.android.c2dm.intent.RECEIVE")) {

					String data = intent.getExtras().getString("data");
					Log.d(TAG, "and data " + data);

					JSONObject json = new JSONObject(data);
					KrollDict dict = new KrollDict(json);
	        
	        Log.d(TAG, "in notification.");
					ParseModule.getInstance().fireEvent("notification", dict);

				}
			}

		} catch (Exception e) {
			Log.d(TAG, "Exception: " + e.toString());
		}

	}
}