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
import android.widget.RadioButton;
//import com.example.reminder.controller.NewPostController;

public class NewPostActivity extends Activity {
	//NewPostController controller;
	public Button next;
	public int buttonSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //controller = new NewPostController(this);
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
				startActivity(new Intent(getBaseContext(), NewPostActivity2.class));
				finish();
			}
		});
    }
    
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.newPost1_op1:
                if (checked) 
                	buttonSelected = 0;
                break;
            case R.id.newPost1_op2:
                if (checked)
                    buttonSelected = 1;
                break;
            case R.id.newPost1_op3:
                if (checked)
                    buttonSelected = 2;
                break;
            case R.id.newPost1_op4:
                if (checked)
                    buttonSelected = 3;
                break;
            case R.id.newPost1_op5:
                if (checked)
                    buttonSelected = 4;
                break;
        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
    @Override
    public void onBackPressed() {
		startActivity(new Intent(this, DataActivity.class));
		finish();
    }
}
