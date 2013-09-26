package com.facebook.flw;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;
import com.facebook.flw.model.Restaurant;
import com.parse.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FreeLunchWednesdayApplication extends Application {

  /** Parse application id */
  public static final String APPLICATION_ID = "41qaY4G3bqSixIBWtavcWAfMScmUjM288KFM4bmA";

  /** Parse client key */
  public static final String CLIENT_KEY = "rogRhk3NHbGn53BaFkDVyTEwvt3zdDiA7Rwx0Aef";
  public static final String TAG = FreeLunchWednesdayApplication.class.getSimpleName();


  @Override
	public void onCreate() {
      super.onCreate();

    try {
      PackageInfo info = getPackageManager().getPackageInfo(
          "com.facebook.flw", PackageManager.GET_SIGNATURES);
      for (Signature signature : info.signatures) {
        MessageDigest md = MessageDigest.getInstance("SHA");
        md.update(signature.toByteArray());
        Log.e("MY KEY HASH:",
            Base64.encodeToString(md.digest(), Base64.DEFAULT));
      }
    } catch (PackageManager.NameNotFoundException e) {

    } catch (NoSuchAlgorithmException e) {

    }

      ParseObject.registerSubclass(Restaurant.class);

      Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);
      ParseFacebookUtils.initialize(getString(R.string.facebook_app_id));


      ParseUser.enableAutomaticUser();
      ParseACL defaultACL = new ParseACL();
      // Optionally enable public read access.
      // defaultACL.setPublicReadAccess(true);
      ParseACL.setDefaultACL(defaultACL, true);
	}

}
