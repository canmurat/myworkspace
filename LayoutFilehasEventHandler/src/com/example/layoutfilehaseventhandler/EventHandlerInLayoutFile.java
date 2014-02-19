package com.example.layoutfilehaseventhandler;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class EventHandlerInLayoutFile extends Activity {

	TextView text1;
	TextView text2;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_handler_in_layout_file);
		text1  = (TextView)findViewById(R.id.textView1);
		text2 =  (TextView)findViewById(R.id.textView2);
	}
	public void setHello(View v)
	{
		text1.setText("Merhaba Hosgeldin");
	}
	public void setGoodbye(View v)
	{
		text2.setText("Gule Gule git..");
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_handler_in_layout_file, menu);
		return true;
	}

}
