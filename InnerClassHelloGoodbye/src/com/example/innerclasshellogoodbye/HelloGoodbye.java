package com.example.innerclasshellogoodbye;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HelloGoodbye extends Activity {

	TextView text1;
	TextView text2;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hello_goodbye);
		
		Button btn1 = (Button)findViewById(R.id.button1);
		Button btn2 = (Button)findViewById(R.id.button2);
		text1 = (TextView)findViewById(R.id.textView1);
		text2 = (TextView)findViewById(R.id.textView2);	
		btn1.setOnClickListener(new Mesaj("Merhaba, Hosgeldin"));
		btn2.setOnClickListener(new Mesaj2("Gule Gule Git.."));

	}
	public void setHello(String helloString)
	{
		text1.setText(helloString);
	}
	public void setGoodbye(String goodbyeString)
	{
		text2.setText(goodbyeString);
	}
	
	public class Mesaj implements OnClickListener
	{
		String hello;
		public Mesaj(String hello)
		{
			this.hello = hello;
		}
		public void onClick(View v) {
			
			setHello(hello);
		}
		
	}
	
	public class Mesaj2 implements OnClickListener
	{
		String goodbye;
		
		public Mesaj2(String goodbye)
		{
			this.goodbye = goodbye;
		}

		public void onClick(View v) {
		
			setGoodbye(goodbye);
			
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hello_goodbye, menu);
		return true;
	}

}
