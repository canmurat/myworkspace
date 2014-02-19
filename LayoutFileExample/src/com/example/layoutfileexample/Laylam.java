package com.example.layoutfileexample;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;


public class Laylam extends Activity {

	private View mColorRegion;
	private int[] mColorChoices =
	{ Color.BLACK, Color.BLUE };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_laylam);
		mColorRegion = findViewById(R.id.color_region);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.laylam, menu);
		return true;
	}

	public void setRegionColor(int color) {
		mColorRegion.setBackgroundColor(color);
		}
		public void randomizeColor(View v) {
		Random generator = new Random();
		int index = generator.nextInt(mColorChoices.length);
		setRegionColor(mColorChoices[index]);
		}
		
}

