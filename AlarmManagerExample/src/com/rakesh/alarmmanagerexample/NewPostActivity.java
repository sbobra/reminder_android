package com.rakesh.alarmmanagerexample;

import com.rakesh.alarmmanagerexample.R;


//import com.example.reminder.controller.NewPostController;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class NewPostActivity extends Activity {
	//NewPostController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //controller = new NewPostController(this);
        setContentView(R.layout.activity_main);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
    
}
