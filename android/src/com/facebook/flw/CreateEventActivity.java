package com.facebook.flw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.facebook.flw.model.Event;
import com.facebook.flw.model.Restaurant;
import com.parse.ParseException;
import com.parse.SaveCallback;

import java.util.ArrayList;

public class CreateEventActivity extends Activity {

    private static final String[] restaurants = {
            "Test 1",
            "Test 2",
            "Test 3",
            "Test 6",
            "Test 7"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event);
      setTitle("Choose Restaurants");

        ListView list = (ListView)findViewById(R.id.rest_list);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        list.setAdapter(new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_multiple_choice, restaurants));

    }

  private void createEventWithRestaurants() {
      Event event = new Event();
      event.addRestaurants(new ArrayList<Restaurant>());
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
