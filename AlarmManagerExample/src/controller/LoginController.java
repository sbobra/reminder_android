package controller;

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
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.rakesh.alarmmanagerexample.LoginActivity;


public class LoginController {
	private LoginActivity view;

	public LoginController(LoginActivity activity) {
		this.view = activity;

	}
	
	public void onNewUserPressed(final String name, final String username,
			String password) {
//		password = Utils.sha256(password);
//		User user = new User(name, username, password);
//		user.save(new StackMobModelCallback() {
//			@Override
//			public void success() {
//				// TODO: save username and password for persistent login
//				Log.i("LoginController", "Successful new user!");
//				State.getInstance().setName(name);
//				State.getInstance().setLoggedIn(true);
//
//				view.startActivity(new Intent(view, HomeActivity.class));
//				view.finish();
//
//			}
//
//			@Override
//			public void failure(StackMobException e) {
//				Toast.makeText(view.getApplicationContext(), "error!",
//						Toast.LENGTH_SHORT).show();
//
//				Log.i("LoginController", e.getMessage());
//			}
//		});

	}

	public void onLoginPressed(final String username, String password) {
		Object[] data = new Object[2];
		data[0] = username;
		data[1] = password;
		new LoginTask().execute(data);
//		password = Utils.sha256(password);
//		User user = new User(username, password);
//		user.login(new StackMobModelCallback() {
//
//			@Override
//			public void success() {
//				Log.i("LoginController", "Successful login!");
//				getUserData(username);
//			}
//
//			@Override
//			public void failure(StackMobException e) {
//				Log.i("LoginController", e.getMessage());
//			}
//		});

	}
	
	private class LoginTask extends AsyncTask<Object[], Void, String> {

		public String getString(Object[] data) {

			String str = "";
			str += "email=" + (String) data[1] + "&";
			str += "password=" + (String) data[3] + "&";
			str += "name=" + (String) data[2];
			return str;
		}

		@Override
		protected String doInBackground(Object[]... params) {
			//Object[] data = (Object[]) params[0];
			String URL = "http://re-minder.herokuapp.com/api/user_sessions";
			Log.i("JSONController", URL);
			//String str = getString(data);
			Post p = State.getInstance().getNewPost();
			JSONObject json = new JSONObject();
			String str;
			try {
				json.put("email", (String)params[0][0]);
				json.put("password", (String)params[0][1]);
				Log.i("LoginController", (String)params[0][0]);
				Log.i("LoginController", (String)params[0][1]);
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

			Log.i("LoginController", result);

		}
	}
}