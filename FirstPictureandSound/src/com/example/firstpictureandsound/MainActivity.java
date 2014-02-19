package com.example.firstpictureandsound;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final MediaPlayer muzugum = MediaPlayer.create(MainActivity.this,R.raw.laughing);
		final Button buton1 = (Button)findViewById(R.id.button1);
		buton1.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				
				startActivity(new Intent("com.example.firstpictureandsound.IKINCI"));
				muzugum.start();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
