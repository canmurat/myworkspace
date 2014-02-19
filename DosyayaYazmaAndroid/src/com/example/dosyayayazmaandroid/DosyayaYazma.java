package com.example.dosyayayazmaandroid;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class DosyayaYazma extends Activity {

	TextView textview1 = (TextView)findViewById(R.id.textView1);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dosyayayazma);
		
		
	}
	
	
}
