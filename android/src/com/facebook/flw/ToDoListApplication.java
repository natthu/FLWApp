package com.facebook.flw;

import com.parse.Parse;
import com.parse.ParseACL;

import com.parse.ParseUser;

import android.app.Application;

public class ToDoListApplication extends Application {

  public static final String CLIENT_KEY = "nTTqsoV7zXXH44CZQdPRS9eZ9ShcXEbzgCz1uGDs";
  public static final String APPLICATION_ID = "NPq8I3xej4ijLpuNW5NyZfCMwB59zonOfUVjR3q2";

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
