package com.rakesh.alarmmanagerexample;

import model.Post;
import model.State;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//import com.example.reminder.controller.NewPostController;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class NewPostActivity2 extends Activity {
	//NewPostController controller;
	public Button next;
	int buttonSelected = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //controller = new NewPostController(this);
        setContentView(R.layout.activity_newpost2);
        next = (Button) findViewById(R.id.newpost2_next1);
		next.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Post p = State.getInstance().getNewPost();
				p.setActivityId(buttonSelected);
				State.getInstance().setNewPost(p);
				startActivity(new Intent(getBaseContext(), NewPostActivity3.class));
				finish();
			}
		});
    }
    
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.newPost2_op1:
                if (checked) 
                	buttonSelected = 0;
                break;
            case R.id.newPost2_op2:
                if (checked)
                    buttonSelected = 1;
                break;
            case R.id.newPost2_op3:
                if (checked)
                    buttonSelected = 2;
                break;
            case R.id.newPost2_op4:
                if (checked)
                    buttonSelected = 3;
                break;
            case R.id.newPost2_op5:
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
		startActivity(new Intent(this, NewPostActivity.class));
		finish();
    }
    
}
