package com.facebook.flw;

import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.facebook.flw.model.Event;
import com.facebook.flw.model.Restaurant;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class PickRestaurantActivity extends ListActivity {
	private static final int ACTIVITY_CREATE = 0;
	private static final int ACTIVITY_EDIT = 1;

	public static final int INSERT_ID = Menu.FIRST;

	private Dialog progressDialog;

	private class FetchLatestEvent extends AsyncTask<Void, Void, Event> {
		// Override this method to do custom remote calls
		protected Event doInBackground(Void... params) {

			ParseQuery<Event> query = new ParseQuery<Event>("Restaurants")
        .orderByDescending("_created_at");

      query.setLimit(1);


			try {
				List<Event> events = query.find();
			  return events.get(0);
			} catch (ParseException e) {
        Log.e(FreeLunchWednesdayApplication.TAG, "oops", e);
        return null;
			}
		}

		@Override
		protected void onPreExecute() {
			PickRestaurantActivity.this.progressDialog = ProgressDialog.show(PickRestaurantActivity.this, "",
					"Loading...", true);
			super.onPreExecute();
		}

		@Override
		protected void onProgressUpdate(Void... values) {

			super.onProgressUpdate(values);
		}

		@Override
		protected void onPostExecute(Event result) {
			// Put the list of restaurants into the list view
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(PickRestaurantActivity.this, R.layout.todo_row);
			for (Restaurant restaurant : result.getRestaurants()) {
				adapter.add(restaurant.getName());
			}
			setListAdapter(adapter);
			PickRestaurantActivity.this.progressDialog.dismiss();
			TextView empty = (TextView) findViewById(android.R.id.empty);
			empty.setVisibility(View.VISIBLE);
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		TextView empty = (TextView) findViewById(android.R.id.empty);
		empty.setVisibility(View.INVISIBLE);

		new FetchLatestEvent().execute();
//		registerForContextMenu(getListView());
	}

//	private void createTodo() {
//		Intent i = new Intent(this, CreateFreeLunchWednesday.class);
//		startActivityForResult(i, ACTIVITY_CREATE);
//	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (intent == null) {
			return;
		}
		final Bundle extras = intent.getExtras();

		switch (requestCode) {
		case ACTIVITY_CREATE:
//			new FetchLatestEvent() {
//				protected Void doInBackground(Void... params) {
//					String name = extras.getString("name");
//					ParseObject todo = new ParseObject("Todo");
//					todo.put("name", name);
//					try {
//						todo.save();
//					} catch (ParseException e) {
//					}
//
//					super.doInBackground();
//					return null;
//				}
//			}.execute();
			break;
		case ACTIVITY_EDIT:
			// Edit the remote object
//			final ParseObject todo;
//			todo = restaurants.get(extras.getInt("position"));
//			todo.put("name", extras.getString("name"));
//
//			new FetchLatestEvent() {
//				protected Void doInBackground(Void... params) {
//					try {
//						todo.save();
//					} catch (ParseException e) {
//					}
//					super.doInBackground();
//					return null;
//				}
//			}.execute();
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);
//		menu.add(0, INSERT_ID, 0, R.string.menu_insert);
		return result;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
//		menu.add(0, DELETE_ID, 0, R.string.menu_delete);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		}
		return super.onContextItemSelected(item);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case INSERT_ID:
//			createTodo();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent intent = new Intent(this, CreateFreeLunchWednesday.class);

//		intent.putExtra("name", restaurants.get(position).getString("name").toString());
		intent.putExtra("position", position);
		startActivityForResult(intent, ACTIVITY_EDIT);
	}

}
