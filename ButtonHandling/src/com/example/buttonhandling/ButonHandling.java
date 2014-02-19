package com.example.buttonhandling;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class ButonHandling extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buton_handling);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.buton_handling, menu);
		return true;
	}
	
	public void butonmesaj(View v)
	{
		Button b = (Button)v;
		String name = b.getText().toString();
		Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
	}
	public void imagebuttonmesaj(View v)
	{
		ImageButton ib = (ImageButton)v;
		int btnID = ib.getId();
		String s =ib.getTag().toString();
		
		Toast.makeText(this,s, Toast.LENGTH_SHORT).show();
		
	}
	public void sayfa2Goster(View v ) 
	{
		Intent it = new Intent(this,MainActivity.class);
		startActivity(it);
	}

}
