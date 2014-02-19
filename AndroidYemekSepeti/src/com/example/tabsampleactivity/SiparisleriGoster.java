package com.example.tabsampleactivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SiparisleriGoster extends Activity {

	// Amac, siparisleri text dosyasýnda gostermek . , verilen dosya isminie
	// gore dgerler okunup textview'e aktarýlýyor.

	TextView text;
	String[] aradigimsatir;
	static int siparisId = 0;
	int sayac = 0;
	File myFile;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.siparisler);

		text = (TextView) findViewById(R.id.siparisleriGoster);
		text.setMovementMethod(new ScrollingMovementMethod());

		myFile = getFilesDir();
		File file = new File(myFile +"/siparisler.txt");
		if (file.exists())
		{
			text.append("bu dosya gayette burada mevcut !  ");
		}
	//	file.mkdirs();
		String eol = System.getProperty("line.separator");
		String fname=new File(getFilesDir(), "siparisler.txt").getAbsolutePath();
		try {

			
				 InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(fname));
				 BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {

				text.append(line + eol);
			//	text.append("myfile="+myFile.toString());
				sayac = line.length() + sayac;
//				if (line.contains("Siparis_Id:")) {
//					String aradigimsatir[] = line.split(" ");
//					siparisId = Integer.valueOf(aradigimsatir[1]);
//
//				}
//				else {
//					siparisId = 0;
//				}
			
			}
			inputStreamReader.close();
			
		
	

		}
		catch (FileNotFoundException e) {
			 Log.e(ACCESSIBILITY_SERVICE, "File not found: " + e.toString());
			 } catch (IOException e) {
			 Log.e(ACCESSIBILITY_SERVICE, "Can not read file: " + e.toString());
			 }
	}

	public int siparisIdAl() {
		return sayac;
	}

	// SiparisVer Activity'sine geri don.
	public void geriDon(View v) {

		Intent intent = new Intent(SiparisleriGoster.this, SiparisVer.class);
		startActivity(intent);

	}

}

