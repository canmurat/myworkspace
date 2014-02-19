package com.example.derstespinnerornek;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner1.setOnItemSelectedListener(new SpinnerInfo());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void showToast(String string)
	{
		Toast.makeText(this,string,Toast.LENGTH_LONG).show();
	}
private	class SpinnerInfo implements OnItemSelectedListener {
		
		private boolean isFirst = true;

		public SpinnerInfo() {
			// TODO Auto-generated constructor stub
		}
		@Override
		public void onItemSelected(AdapterView<?> spinner, View selectedView,
				int selectedIndex, long id) {
			if (isFirst) {
				isFirst = false;
			} else {
				String selection = spinner.getItemAtPosition(selectedIndex).toString();
				//String message = String.format(mItemSelectedMessageTemplate,
				//		selection);
				showToast(selection);
			}
			
		}
		

		@Override
		public void onNothingSelected(AdapterView<?> spinner) {
			// Won’t be invoked unless you programmatically remove entries
		}
	}

}

