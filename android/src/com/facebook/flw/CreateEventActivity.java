package com.facebook.flw;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.facebook.Request;

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

        ListView list = (ListView)findViewById(R.id.rest_list);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        list.setAdapter(new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_multiple_choice, restaurants));
    }
}
