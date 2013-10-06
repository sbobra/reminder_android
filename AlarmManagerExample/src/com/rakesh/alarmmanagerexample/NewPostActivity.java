package com.rakesh.alarmmanagerexample;

import com.rakesh.alarmmanagerexample.R;


//import com.example.reminder.controller.NewPostController;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class NewPostActivity extends Activity {
	//NewPostController controller;
	public Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //controller = new NewPostController(this);
        setContentView(R.layout.activity_newpost);
		next = (Button) findViewById(R.id.newpost_next1);
		next.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(getBaseContext(), NewPostActivity2.class));
				finish();
			}
		});
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
    
}
