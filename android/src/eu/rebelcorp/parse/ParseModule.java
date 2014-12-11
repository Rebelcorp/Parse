/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2013 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package eu.rebelcorp.parse;

import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;

import org.appcelerator.titanium.TiApplication;
import org.appcelerator.kroll.common.Log;

import android.content.Context;

import com.parse.Parse;
import com.parse.ParsePush;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.PushService;
import com.parse.ParseUser;
import com.parse.LogInCallback;
import com.parse.ParseException;

@Kroll.module(name="Parse", id="eu.rebelcorp.parse")
public class ParseModule extends KrollModule
{

	// Module instance
	private static ParseModule module;
	
	// Standard Debugging variables
	private static final String TAG = "ParseModule";
  
    // tiapp.xml properties containing Parse's app id and client key
    public static String PROPERTY_APP_ID = "Parse_AppId";
    public static String PROPERTY_CLIENT_KEY = "Parse_ClientKey";

	// You can define constants with @Kroll.constant, for example:
	// @Kroll.constant public static final String EXTERNAL_NAME = value;

	public ParseModule()
	{
		super();
        
		module = this;
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app)
	{
        String appId = TiApplication.getInstance().getAppProperties().getString(ParseModule.PROPERTY_APP_ID, "");
        String clientKey = TiApplication.getInstance().getAppProperties().getString(ParseModule.PROPERTY_CLIENT_KEY, "");
        
        Log.d(TAG, "Initializing with: " + appId + ", " + clientKey + ";");
        
        Parse.initialize(TiApplication.getInstance(), appId, clientKey);
    }

	// Methods
	@Kroll.method
	public void start()
	{
        // Track Push opens
        ParseAnalytics.trackAppOpened(TiApplication.getAppRootOrCurrentActivity().getIntent());
        
        ParseInstallation.getCurrentInstallation().saveInBackground();
	}

    @Kroll.method
    public void enablePush() {
		// Deprecated. Now happens automatically
    }
    
    @Kroll.method
    public void authenticate(@Kroll.argument String sessionToken) {
        ParseUser.becomeInBackground(sessionToken, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    // Hooray! The user is logged in.
                } else {
                    // Signup failed. Look at the ParseException to see what happened.
                }
            }
        });
    }

	@Kroll.method
	public void subscribeChannel(@Kroll.argument String channel) {
		ParsePush.subscribeInBackground(channel);
	}

	@Kroll.method
	public void unsubscribeChannel(@Kroll.argument String channel) {
		ParsePush.unsubscribeInBackground(channel);
	}
    
    @Kroll.method
    public void putValue(@Kroll.argument String key, @Kroll.argument Object value) {
        ParseInstallation.getCurrentInstallation().put(key, value);
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }

	public static ParseModule getInstance() {
		return module;
	}

}
