package com.example.getpostwrite;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);
		
		Button butonTikla = (Button)findViewById(R.id.button1);
		final TextView textview = (TextView)findViewById(R.id.textView1); 
		final EditText edittext = (EditText)findViewById(R.id.editText1);
		
		butonTikla.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0) {
				 textview.setText(edittext.getText());
			
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my, menu);
		return true;
	}

}
