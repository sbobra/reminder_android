package com.rakesh.alarmmanagerexample;

import java.io.InputStream;

import model.Post;
import model.State;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableRow;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import controller.RESTClient;
//import com.example.reminder.controller.NewPostController;

public class NewPostActivity3 extends Activity {
	// NewPostController controller;
	public Button submit;
	public int buttonSelected = 0;
	public int[] imageArray = { R.id.imageView1, R.id.imageView2,
			R.id.imageView3, R.id.imageView4, R.id.imageView5 };
	public int[] tableRowArray = { R.id.tableRow1, R.id.tableRow2,
			R.id.tableRow3, R.id.tableRow4, R.id.tableRow5 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// controller = new NewPostController(this);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_newpost3);
		submit = (Button) findViewById(R.id.newpost3_next1);
		submit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Post p = State.getInstance().getNewPost();
				p.setFunctioning(buttonSelected);
				String note = ((EditText) findViewById(R.id.noteText)).getText().toString();
				p.setNote(note);
				State.getInstance().setNewPost(p);
				Log.i("NewPostActivity3",
						"Getting ready to post: " + p.getFeeling() + ", "
								+ p.getActivityId() + ", " + p.getFunctioning() + ", " + p.getNote());

				new PostTask().execute();

				startActivity(new Intent(getBaseContext(), MenuActivity.class));
				finish();
			}
		});
		setRowOnClicks();
	}

	public void postData2() throws JSONException {
		RequestParams params = new RequestParams();
		params.put("feeling",
				String.valueOf(State.getInstance().getNewPost().getFeeling()));
		params.put(
				"functioning",
				String.valueOf(State.getInstance().getNewPost()
						.getFunctioning()));
		params.put("activity_id", String.valueOf(State.getInstance()
				.getNewPost().getActivityId()));
		params.put("notes", "Sam test");
		RESTClient.post("/posts.json", params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray data) {
				// Pull out the first event on the public timeline
				Log.i("NewPostActivity3", "Successfully posted hella JSON");
			}
		});
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

	private class PostTask extends AsyncTask<Void, Void, String> {

		public String getString(Object[] data) {

			String str = "";
			str += "email=" + (String) data[1] + "&";
			str += "password=" + (String) data[3] + "&";
			str += "name=" + (String) data[2];
			return str;
		}

		@Override
		protected String doInBackground(Void... params) {
			//Object[] data = (Object[]) params[0];
			String URL = "http://re-minder.herokuapp.com/posts.json";
			Log.i("JSONController", URL);
			//String str = getString(data);
			Post p = State.getInstance().getNewPost();
			JSONObject json = new JSONObject();
			String str;
			try {
				json.put("feeling", p.getFeeling() + 1);
				json.put("functioning", p.getFunctioning() + 1);
				json.put("activity_id", p.getActivityId() + 1);
				json.put("notes", p.getNote());
				str = json.toString();

			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				str = "";
			}
			Log.i("JSONController", str);
			HttpClient client = new DefaultHttpClient();
			HttpConnectionParams
					.setConnectionTimeout(client.getParams(), 10000); // Timeout
																		// Limit
			HttpResponse response;
			try {
				HttpPost post = new HttpPost(URL);
				StringEntity se = new StringEntity(str);
				se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,
						"application/json"));
				post.setEntity(se);
				response = client.execute(post);

				/* Checking response */
				if (response != null) {
					InputStream in = response.getEntity().getContent(); // Get
																		// the
																		// data
																		// in
																		// the
																		// entity
					Log.i("JSONController", "Response received");
					return ((String) convertStreamToString(in));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}

		public String convertStreamToString(InputStream is) {
			java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
			return s.hasNext() ? s.next() : "";
		}

		@Override
		protected void onPostExecute(String result) {

			Log.i("NewPostActivity3", result);

		}
	}
	
	@Override
    public void onBackPressed() {
		startActivity(new Intent(this, NewPostActivity2.class));
		finish();
    }

}
