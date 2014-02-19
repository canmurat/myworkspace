package com.example.imagebutton02;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ImageActivity02 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_activity02);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_activity02, menu);
		return true;
	}

}
