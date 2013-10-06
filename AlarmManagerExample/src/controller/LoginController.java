package controller;

import android.content.Intent;

import com.rakesh.alarmmanagerexample.AlarmManagerActivity;
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
}