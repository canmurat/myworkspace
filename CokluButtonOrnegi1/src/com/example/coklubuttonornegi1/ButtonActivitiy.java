package com.example.coklubuttonornegi1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ButtonActivitiy extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buttons);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.button_activitiy, menu);
		return true;
	}

}
