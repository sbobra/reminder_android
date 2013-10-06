package com.rakesh.alarmmanagerexample;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MenuActivity extends Activity  {
	
	public Button newPostButton;
	public Button settingsButton;
	public Button dataButton;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
//		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		newPostButton = (Button) findViewById(R.id.menu_newpost);
		newPostButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(getBaseContext(), NewPostActivity.class));
				finish();
			}
		});
		dataButton = (Button) findViewById(R.id.menu_data);
		dataButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(getBaseContext(), DataActivity.class));
				finish();
			}
		});
		settingsButton = (Button) findViewById(R.id.menu_settings);
		settingsButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(getBaseContext(), AlarmManagerActivity.class));
				finish();
			}
		});
		
		ActionBar ab = getActionBar();
	    ab.setTitle("My Posts");
	    ab.setDisplayShowHomeEnabled(false);
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


