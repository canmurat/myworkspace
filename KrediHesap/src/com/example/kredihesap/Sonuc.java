package com.example.kredihesap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Sonuc extends Activity {
	
	TextView sonuc1;
	TextView sonuc2;
	

	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sonuc);
		
		sonuc1 = (TextView)findViewById(R.id.sonuc1);
		sonuc2 = (TextView)findViewById(R.id.sonuc2);
		
		
		
		//Sayýlarýn son 3 basamagýndan sonrasý yuvarlanýyor.
		
		sonuc1.setText("" +round((Double.valueOf(KrediHesap.ayliktaksitsonucu)),3) + " TL");
		sonuc2.setText("" +round((Double.valueOf(KrediHesap.geriodenecektoplamtutar)),3)+" TL");
	}
	
	
	// Bir onceki ekrana don
	public void krediEkraninaDon(View v)
	{
		Intent intent = new Intent(Sonuc.this,KrediHesap.class);
		startActivity(intent);
	}
	
	//Yuvarlama methodu
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}

}
