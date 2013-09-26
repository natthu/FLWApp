package com.facebook.flw.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created with IntelliJ IDEA.
 * User: ostrulovich
 * Date: 9/26/13
 * Time: 8:39 PM
 * To change this template use File | Settings | File Templates.
 */
@ParseClassName("Restaurant")
public class Restaurant extends ParseObject {

  public Restaurant() {
    // default c'tor required by Parse
  }

  String getName() {
    return getString("name");
  }
}
