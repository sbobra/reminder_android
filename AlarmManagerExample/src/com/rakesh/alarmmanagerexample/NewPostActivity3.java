package com.rakesh.alarmmanagerexample;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import controller.RESTClient;
import model.Post;
import model.State;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
//import com.example.reminder.controller.NewPostController;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class NewPostActivity3 extends Activity {
	// NewPostController controller;
	public Button submit;
	public int buttonSelected = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// controller = new NewPostController(this);
		setContentView(R.layout.activity_newpost3);
		submit = (Button) findViewById(R.id.newpost3_next1);
		submit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Post p = State.getInstance().getNewPost();
				p.setFunctioning(buttonSelected);
				State.getInstance().setNewPost(p);
				Log.i("NewPostActivity3",
						"Getting ready to post: " + p.getFeeling() + ", "
								+ p.getActivityId() + ", " + p.getFunctioning());

				new PostTask().execute();

				startActivity(new Intent(getBaseContext(), MenuActivity.class));
				finish();
			}
		});
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

	public void onRadioButtonClicked(View view) {
		// Is the button now checked?
		boolean checked = ((RadioButton) view).isChecked();
		// Check which radio button was clicked
		switch (view.getId()) {
		case R.id.newPost3_op1:
			if (checked)
				buttonSelected = 0;
			break;
		case R.id.newPost3_op2:
			if (checked)
				buttonSelected = 1;
			break;
		case R.id.newPost3_op3:
			if (checked)
				buttonSelected = 2;
			break;
		case R.id.newPost3_op4:
			if (checked)
				buttonSelected = 3;
			break;
		case R.id.newPost3_op5:
			if (checked)
				buttonSelected = 4;
			break;
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
				json.put("feeling", p.getFeeling());
				json.put("functioning", p.getFunctioning());
				json.put("activity_id", p.getActivityId());
				json.put("notes", "testing from sam");
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
