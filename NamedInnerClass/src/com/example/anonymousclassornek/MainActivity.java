package com.example.anonymousclassornek;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView text1;
	TextView text2;
	
	ColorRandomizer cr1 = new ColorRandomizer("Merhaba");
	ColorRandomizer2 cr2 = new ColorRandomizer2("GuleGule");
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button colorButton = (Button)findViewById(R.id.button1);
		Button colorButton2 = (Button)findViewById(R.id.button2);
		text1 = (TextView)findViewById(R.id.textView1);
		text2 = (TextView)findViewById(R.id.textView2);
		colorButton.setOnClickListener(cr1);
		colorButton2.setOnClickListener(cr2);
		}
	
	public void setHello(String text) 
	{
		cr1.mn.text1.setText(text);
	}
	
	public void setGoodbye(String text)
	{
		cr2.mn2.text2.setText(text);
	}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
