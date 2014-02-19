package com.example.activityandintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText txtuserID;
	EditText txtpassword;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txtuserID = (EditText)findViewById(R.id.userID);
		txtpassword = (EditText)findViewById(R.id.password);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void loginKontrol(View v)
	{
		String userID = txtuserID.getText().toString();
		String password = txtpassword.getText().toString();
		Intent yeniintent = new Intent(this,LoginActivity.class);
		Bundle bnd = new Bundle();
		bnd.putString(password, password);
		bnd.putString(password, password);
		}

}
