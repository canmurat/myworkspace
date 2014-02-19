	package com.example.innerclassornek;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends Activity {

    private View mColorRegion;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mColorRegion = findViewById(R.id.textView1);
		Button b1 = (Button)findViewById(R.id.button1);
		Button b2 = (Button)findViewById(R.id.button2);
		Button b3 = (Button)findViewById(R.id.button3);
		RadioButton r1 = (RadioButton)findViewById(R.id.radioButton1);
		RadioButton r2 = (RadioButton)findViewById(R.id.radioButton2);
		RadioButton r3 = (RadioButton)findViewById(R.id.radioButton3);
		
		b1.setOnClickListener(new ColorSetter(Color.RED));
		b2.setOnClickListener(new ColorSetter(Color.BLUE));
		b3.setOnClickListener(new ColorSetter(Color.YELLOW));
		r1.setOnClickListener(new ColorSetter(Color.RED));
		r2.setOnClickListener(new ColorSetter(Color.BLUE));
		r3.setOnClickListener(new ColorSetter(Color.YELLOW));
		
	}
	private void setRegionColor(int color)
	{
		mColorRegion.setBackgroundColor(color);
		
	}
	
	private class ColorSetter implements OnClickListener
	{
		private int regionColor;

		public ColorSetter(int regionColor)
		{
			this.regionColor = regionColor;
		}
		
		
		public void onClick(View v) {
			
			setRegionColor(regionColor);
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
