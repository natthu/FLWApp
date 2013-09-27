package com.facebook.flw;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
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

        final ListView list = (ListView)findViewById(R.id.rest_list);
        final ListAdapter adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_multiple_choice, restaurants);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        list.setAdapter(adapter);

        Button submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray checked = list.getCheckedItemPositions();
                ArrayList<String> selectedNames = new ArrayList<String>();
                for (int i = 0; i < adapter.getCount(); ++i) {
                    if (checked.get(i)) {
                        selectedNames.add((String)adapter.getItem(i));
                    }
                }

                Log.d("natthu", "checked: " + selectedNames);
            }
        });


        final ProgressDialog dialog = ProgressDialog.show(this, "", "Searching restaurants nearby ...", true);
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
