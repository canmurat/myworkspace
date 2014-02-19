package com.example.anonymousinnerclass;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AnonymousExample extends Activity {

	private View mColorRegion;
	private int[] mColorChoices =
	{ Color.BLACK, Color.BLUE };
	
	
	private void setRegionColor(int color) {
		mColorRegion.setBackgroundColor(color);
		}
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_anonymous_example);
		mColorRegion = findViewById(R.id.textView1);
		Button colorButton =
		(Button)findViewById(R.id.button1);
		colorButton.setOnClickListener(new OnClickListener() {
		@Override
			public void onClick(View v) {
				Random generator = new Random();
				int index = generator.nextInt(mColorChoices.length);
				setRegionColor(mColorChoices[index]);
			}
		});
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.anonymous_example, menu);
		return true;
	}

}
