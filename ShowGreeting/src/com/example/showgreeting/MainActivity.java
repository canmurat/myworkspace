package com.example.showgreeting;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button greetingButton =
				(Button)findViewById(R.id.greeting_buton);
				greetingButton.setOnClickListener(new Toaster());
	}
	
	private class Toaster implements OnClickListener {
		@Override
		public void onClick(View clickedButton) {
		String greetingText = getString(R.string.greeting_text);
		Toast tempMessage =
		Toast.makeText(MainActivity.this,
		greetingText,
		Toast.LENGTH_SHORT);
		tempMessage.show();
		}
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
