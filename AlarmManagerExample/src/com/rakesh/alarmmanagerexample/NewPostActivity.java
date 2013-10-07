package com.rakesh.alarmmanagerexample;

import model.Post;
import model.State;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TableRow;

//import com.example.reminder.controller.NewPostController;

public class NewPostActivity extends Activity {
	// NewPostController controller;
	public Button next;
	public int buttonSelected = 0;
	public int[] imageArray = { R.id.imageView1, R.id.imageView2,
			R.id.imageView3, R.id.imageView4, R.id.imageView5, R.id.imageView6,
			R.id.imageView7 };
	public int[] tableRowArray = { R.id.tableRow1, R.id.tableRow2,
			R.id.tableRow3, R.id.tableRow4, R.id.tableRow5, R.id.tableRow6,
			R.id.tableRow7 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// controller = new NewPostController(this);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_newpost);
		next = (Button) findViewById(R.id.newpost_next1);
		next.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Post p = new Post();
				p.setFeeling(buttonSelected);
				State.getInstance().setNewPost(p);
				startActivity(new Intent(getBaseContext(),
						NewPostActivity2.class));
				finish();
			}
		});
		setRowOnClicks();
	}

	public void setRowOnClicks() {
		for (int i = 0; i < tableRowArray.length; i++) {
			final int j = i;
			((TableRow) findViewById(tableRowArray[j]))
					.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							((ImageView) findViewById(imageArray[buttonSelected]))
									.setImageResource(R.drawable.unchecked_checkbox);
							((TableRow) findViewById(tableRowArray[buttonSelected]))
									.setBackgroundResource(R.color.lightgray);
							buttonSelected = j;
							((ImageView) findViewById(imageArray[buttonSelected]))
									.setImageResource(R.drawable.checked_checkbox);
							((TableRow) findViewById(tableRowArray[buttonSelected]))
									.setBackgroundResource(R.color.darkgray);
						}
					});
		}
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }
	@Override
	public void onBackPressed() {
		startActivity(new Intent(this, DataActivity.class));
		finish();
	}
}
