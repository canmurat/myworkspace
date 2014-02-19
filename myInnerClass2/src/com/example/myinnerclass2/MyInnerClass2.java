package com.example.myinnerclass2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MyInnerClass2 extends Activity {

	Button btn1 = (Button)findViewById(R.id.button1);
	TextView text1 = (TextView)findViewById(R.id.textView1);
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_inner_class2);
		
		
		btn1.setOnClickListener(new InnerClass("Merhaba"));
	}
	
	
	public void yazdir(String yazi)
	{
		text1.setText(yazi);
	}

	public class InnerClass implements OnClickListener
	{
		String text;
		public InnerClass(String text)
		{
			this.text = text;
		}
		
		public void onClick(View v) {
			
			yazdir(text);
		}
		
	}
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_inner_class2, menu);
		return true;
	}

}
