package com.example.kredihesaplama;

import java.util.HashMap;
import java.util.Map;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;

public class KrediHesap extends Activity {

	Spinner spinner;
	SeekBar seekbar1;
	SeekBar seekbar2;
	Button buton;
	
	Map<String, Float> kk = new HashMap<String,Float>();
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kredi_hesap);
		
		spinner = (Spinner)findViewById(R.id.spinner1);
		seekbar1 = (SeekBar)findViewById(R.id.seekBar1);
		seekbar2 = (SeekBar)findViewById(R.id.seekBar2);
		buton = (Button)findViewById(R.id.button1);
		
		kk.put("Tekne Kredisi",1.417f);
		kk.put("Bireysel ihtiyac kredisi", 1.18f);
		kk.put("Egitim Kredisi",1.29f);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.kredi_hesap, menu);
		return true;
	}

}
