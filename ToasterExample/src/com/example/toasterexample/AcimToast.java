package com.example.toasterexample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AcimToast extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Button btn1 = (Button) findViewById(R.id.button1);
		setContentView(R.layout.activity_acim_toast);
		btn1.setOnClickListener(new Toaster());
		
	}

	private class Toaster implements OnClickListener {
		
		public void onClick(View clickedButton) {
		String greetingText = "Hello from Android!";
		Toast tempMessage =
		Toast.makeText(AcimToast.this,
		greetingText,
		Toast.LENGTH_SHORT);
		tempMessage.show();
		}
		}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.acim_toast, menu);
		return true;
	}

}
