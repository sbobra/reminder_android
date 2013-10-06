package com.rakesh.alarmmanagerexample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.rakesh.alarmmanagerexample.R;

import controller.LoginController;

public class LoginActivity extends Activity {
	LoginController controller;
	private Button loginButton;
	private Button newUserButton;
	private EditText usernameTextBox;
	private EditText passwordTextBox;
	private EditText nameTextBox;
	private LinearLayout nameLayout;

	// @Override
	// protected void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	// controller = new LoginController(this);
	// setContentView(R.layout.activity_login);
	// }
	
	//
	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_login);
		controller = new LoginController(this);

		usernameTextBox = (EditText) findViewById(R.id.usernameTextBox);
		passwordTextBox = (EditText) findViewById(R.id.passwordTextBox);
		nameTextBox = (EditText) findViewById(R.id.nameTextBox);
		nameLayout = (LinearLayout) findViewById(R.id.nameLayout);

		loginButton = (Button) findViewById(R.id.loginButton);
		loginButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (nameLayout.getVisibility() == View.VISIBLE) {
					controller.onNewUserPressed(nameTextBox.getText()
							.toString(), usernameTextBox.getText().toString(),
							passwordTextBox.getText().toString());
				} else {
					controller.onLoginPressed(usernameTextBox.getText()
							.toString(), passwordTextBox.getText().toString());
				}
				startActivity(new Intent(getBaseContext(), DataActivity.class));
				finish();
			}
		});
		newUserButton = (Button) findViewById(R.id.newAcctButton);
		newUserButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				onNewUserPressed();
				newUserButton.setVisibility(View.GONE);
			}
		});

	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
	
	public void onNewUserPressed() {
		nameLayout.setVisibility(View.VISIBLE);
	}

}
