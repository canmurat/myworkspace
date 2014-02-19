package com.example.buttonradiobuttontogether;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class MainActivity extends Activity {

	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}


	public void Kontrol(View v ){
		
		RadioButton radiobutton = (RadioButton)v;
		Boolean bool1 = radiobutton.isChecked();
		Boolean bool2 = radiobutton.isChecked();
		
		
	}

}
