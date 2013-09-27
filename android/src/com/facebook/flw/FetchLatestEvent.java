package com.facebook.flw;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import com.facebook.flw.model.Event;
import com.facebook.flw.model.Restaurant;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ostrulovich
 * Date: 9/27/13
 * Time: 1:21 AM
 * To change this template use File | Settings | File Templates.
 */
class FetchLatestEvent extends AsyncTask<Void, Void, Event> {
  private PickRestaurantActivity pickRestaurantActivity;

  public FetchLatestEvent(PickRestaurantActivity pickRestaurantActivity) {
    this.pickRestaurantActivity = pickRestaurantActivity;
  }

  // Override this method to do custom remote calls
  protected Event doInBackground(Void... params) {

    ParseQuery<Event> query = new ParseQuery<Event>("Restaurants")
        .orderByDescending("_created_at");

    query.setLimit(1);


    try {
      List<Event> events = query.find();
      return events.isEmpty() ? new Event() : events.get(0);
    } catch (ParseException e) {
      Log.e(FreeLunchWednesdayApplication.TAG, "oops", e);
      return new Event();
    }
  }

  @Override
  protected void onPreExecute() {
//    pickRestaurantActivity.progressDialog = ProgressDialog.show(pickRestaurantActivity, "",
//        "Loading...", true);
    super.onPreExecute();
  }

  @Override
  protected void onProgressUpdate(Void... values) {

    super.onProgressUpdate(values);
  }

  @Override
  protected void onPostExecute(Event result) {
    // Put the list of restaurants into the list view
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(pickRestaurantActivity, R.layout.todo_row);
    for (Restaurant restaurant : result.getRestaurants()) {
      adapter.add(restaurant.getName());
    }
    pickRestaurantActivity.setListAdapter(adapter);
//    pickRestaurantActivity.progressDialog.dismiss();
//    TextView empty = (TextView) pickRestaurantActivity.findViewById(android.R.id.empty);
//    empty.setVisibility(View.VISIBLE);
  }
}
