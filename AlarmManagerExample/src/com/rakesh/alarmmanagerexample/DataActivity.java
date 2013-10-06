package com.rakesh.alarmmanagerexample;

import model.Post;

import org.json.*;
import com.loopj.android.http.*;

import controller.RESTClient;
import android.app.ActionBar;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DataActivity extends Activity {

	public Button refreshButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		refreshButton = (Button) findViewById(R.id.refreshButton);
		refreshButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// refresh that shit
				// startActivity(new Intent(getBaseContext(),
				// NewPostActivity.class));
				// finish();
				clearTable();
				try {
					getData();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					Log.i("DataActivity", e.getMessage());
				}
			}
		});
		
		ActionBar ab = getActionBar();
	    ab.setTitle("My Posts");
	    ab.setDisplayShowHomeEnabled(false);
	}
	
	public void getData() throws JSONException {
		RESTClient.get("/posts.json", null, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray data) {
				// Pull out the first event on the public timeline
				for (int i = 0; i < data.length(); i++) {
					JSONObject object;
					Integer feeling = null;
					Integer functioning=null;
					Integer activityId = null;
					String notes = "";
					try {
						object = data.getJSONObject(i);
						feeling = object.getInt("feeling");
						functioning = object.getInt("functioning");
						activityId = object.getInt("activity_id");
						notes = object.getString("notes");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					// Do something with the response
					Log.i("DataActivity", feeling + "," + functioning + "," + activityId + ", " + notes);
					addRow(new Post(feeling, functioning, activityId, notes));
				}
			}
		});
	}

	public void addRow(final Post p) {
		runOnUiThread(new Runnable(){
		    public void run(){
		    	TableLayout table = (TableLayout) findViewById(R.id.tableLayout);
				// Inflate your row "template" and fill out the fields.
				final TableRow row = (TableRow) LayoutInflater.from(getBaseContext()).inflate(
						R.layout.row_data, null);
				((TextView) row.findViewById(R.id.row_data_note)).setText("Note: " + p.getNote());

				((TextView) row.findViewById(R.id.row_data_functioning)).setText("Functioning: "+p.getFunctioning());

				((TextView) row.findViewById(R.id.row_data_feeling)).setText("Feeling: " + p.getFeeling());

				((TextView) row.findViewById(R.id.row_data_activity_id)).setText("Activity: " + p.getActivityId());
				table.addView(row);

				table.requestLayout();
		    }
		});
		
	}

	public void clearTable() {
		TableLayout table = (TableLayout) findViewById(R.id.tableLayout);
		table.removeAllViews();

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_menu, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.action_add:
	        	startActivity(new Intent(getBaseContext(), NewPostActivity.class));
				finish();
	            return true;
	        case R.id.action_settings:
	        	startActivity(new Intent(this, AlarmManagerActivity.class));
				finish();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
