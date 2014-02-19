package com.example.myinnerclass;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	Button btn1 = (Button)findViewById(R.id.button1);
	Button btn2 = (Button)findViewById(R.id.button2);
	TextView text1 = (TextView)findViewById(R.id.textView1);
	TextView text2 = (TextView)findViewById(R.id.textView2);
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		btn1.setOnClickListener(new SelamVer("Merhaba"));
		btn2.setOnClickListener(new VedaEt("Gulegule"));
	}
	

	public class SelamVer implements OnClickListener
	{
		String merhaba;
		
		
		public SelamVer(String merhaba )
		{
			this.merhaba = merhaba;
		}
		
		public void onClick(View v) {
			
			text1.setText(merhaba);
			
		}
		
	}
	public class VedaEt implements OnClickListener
	{
		String gulegule;
		public VedaEt(String gulegule)
		{
			this.gulegule = gulegule;
		}

		@Override
		public void onClick(View v) {
			
			text2.setText(gulegule);
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
