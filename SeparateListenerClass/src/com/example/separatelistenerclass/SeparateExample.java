package com.example.separatelistenerclass;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;


public class SeparateExample extends Activity {

	TextView text1;
	TextView text2;

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_separate_example);

		Button btn1 = (Button)findViewById(R.id.button1);
		Button btn2 = (Button)findViewById(R.id.button2);
		text1 = (TextView)findViewById(R.id.textView1);
		text2 = (TextView)findViewById(R.id.textView2);
		
		btn1.setOnClickListener(new Mesaj1("Merhaba , Hosgeldin.",this));
		btn2.setOnClickListener(new Mesaj2("Gule gule git.",this));
	

	}
	public void setHello(String helloString)
	{
		text1.setText(helloString);
	}
	public void setGoodbye(String goodbyeString)
	{
		text2.setText(goodbyeString);
	}

	
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.separate_example, menu);
		return true;
	}

}
