package com.facebook.flw;

import com.parse.Parse;
import com.parse.ParseACL;

import com.parse.ParseUser;

import android.app.Application;

public class FreeLunchWednesdayApplication extends Application {

  /** Parse application id */
  public static final String APPLICATION_ID = "41qaY4G3bqSixIBWtavcWAfMScmUjM288KFM4bmA";

  /** Parse client key */
  public static final String CLIENT_KEY = "rogRhk3NHbGn53BaFkDVyTEwvt3zdDiA7Rwx0Aef";
  public static final String TAG = FreeLunchWednesdayApplication.class.getSimpleName();


  @Override
	public void onCreate() {
		super.onCreate();

		Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);


		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
		// Optionally enable public read access.
		// defaultACL.setPublicReadAccess(true);
		ParseACL.setDefaultACL(defaultACL, true);
	}

}
