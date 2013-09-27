package com.facebook.flw;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.flw.model.Event;
import com.facebook.flw.model.Restaurant;
import com.facebook.model.GraphPlace;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class CreateEventActivity extends Activity {

    private ListAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event);
        setTitle("Choose Restaurants");

        final ListView list = (ListView)findViewById(R.id.rest_list);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        Button submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray checked = list.getCheckedItemPositions();
                ArrayList<Restaurant> selectedRestaurants = new ArrayList<Restaurant>();
                for (int i = 0; i < adapter.getCount(); ++i) {
                    if (checked.get(i)) {
                        Restaurant restaurant = new Restaurant();
                        restaurant.setName((String)adapter.getItem(i));
                        selectedRestaurants.add(restaurant);
                    }
                }

                createEventWithRestaurants(selectedRestaurants);
            }
        });

        final ProgressDialog dialog = ProgressDialog.show(this, "", "Searching restaurants nearby ...", true);
        Location loc = new Location("fakeProvider");
        loc.setLatitude(51.5114);
        loc.setLongitude(-0.123);
        Request searchRequest = Request.newPlacesSearchRequest(
                ParseFacebookUtils.getSession(),
                loc,
                1500,
                15,
                "Restaurant",
                new Request.GraphPlaceListCallback() {
            @Override
            public void onCompleted(List<GraphPlace> places, Response response) {
                dialog.dismiss();
                ArrayList<String> restaurants = new ArrayList<String>();
                for (GraphPlace place : places) {
                    restaurants.add(place.getName());
                }
                adapter = new ArrayAdapter<String>(
                        CreateEventActivity.this, android.R.layout.simple_list_item_multiple_choice, restaurants);
                list.setAdapter(adapter);
            }
        });
        searchRequest.executeAsync();
    }

  private void createEventWithRestaurants(ArrayList<Restaurant> restaurants) {
      Event event = new Event();
      event.addRestaurants(restaurants);
      event.saveInBackground(redirectToPickRestaurant());
  }

  private SaveCallback redirectToPickRestaurant() {
    return new SaveCallback() {
      @Override
      public void done(ParseException e) {
        Intent intent = new Intent(CreateEventActivity.this, PickRestaurantActivity.class);
        startActivity(intent);
      }
    };
  }
}
