package com.example.activityandintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class LoginActivity extends Activity{

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		TextView txtuserID = (TextView)findViewById(R.id.textView1);
		TextView txtpassword = (TextView)findViewById(R.id.textView2);
		Intent gelenintent = getIntent();
		Bundle gelenbnd = gelenintent.getExtras();
		txtuserID.setText(gelenbnd.getString("userID"));
		txtpassword.setText(gelenbnd.getString("password"));
	}

	
	
}
