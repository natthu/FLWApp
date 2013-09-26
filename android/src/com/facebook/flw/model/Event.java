package com.facebook.flw.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ostrulovich
 * Date: 9/26/13
 * Time: 10:06 PM
 * To change this template use File | Settings | File Templates.
 */
@ParseClassName("Event")
public class Event extends ParseObject {

  public Event() {

  }

  public List<Restaurant> getRestaurants() {
    return getList("restaurants");
  }
}
