package com.example.tabsampleactivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Sonuc extends Activity {
	
	
	// EXTERNAL YAZIM DENEMESÝ . YAZILDI AMA OKUNAMADI...
	
	
	// BU SINIF KULLANIM DISI 

//	String alýnan = "";
//	int adeti;
//	int fiyati;
//	int hesap;
//	File root;
//	File dir;
//
//	ArrayList<Integer> secilenlerAdetler = new ArrayList<Integer>();
//	ArrayList<String> secilenlerListesi = new ArrayList<String>();
//	ArrayList<Integer> secilenlerFiyatlar = new ArrayList<Integer>();
//	ArrayList<CharSequence> sonucListesi = new ArrayList<CharSequence>();
//
	TextView sonuctext;
	TextView textview1;
//
//	final String TAG = Sonuc.class.getName();
//	Dosya dosya = new Dosya();
//
//	public static int siparisId = 0;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.sonuc);

		sonuctext = (TextView) findViewById(R.id.textalinanlar);
		textview1 = (TextView) findViewById(R.id.siparisleriGoster);
		
		
		
		

//		Intent intent = getIntent();
//		Bundle loanInfo = intent.getExtras();
//		secilenlerAdetler = loanInfo.getIntegerArrayList("secilenlerAdetler");
//		secilenlerFiyatlar = loanInfo.getIntegerArrayList("secilenlerFiyatlar");
//		secilenlerListesi = loanInfo.getStringArrayList("secilenlerListesi");
//		hesap = loanInfo.getInt("sonuc");
//
//		File root = android.os.Environment.getExternalStorageDirectory();
//		File dir = new File(root.getAbsolutePath());
//		dir.mkdirs();
//		File file = new File(dir, "siparisler.txt");
//
//		FileOutputStream f;
//
//		try {
//			f = new FileOutputStream(file);
//			PrintWriter pw = new PrintWriter(f);
//
//			for (int i = 0; i < secilenlerListesi.size(); i++) {
//
//				alýnan = secilenlerListesi.get(i);
//				adeti = secilenlerAdetler.get(i);
//				fiyati = secilenlerFiyatlar.get(i);
//
//				pw.println("SiparisId:" + " " + (dosya.SiparisId() + 1) + " "
//						+ "alýnan " + alýnan + " adet " + adeti + " fiyatý: "
//						+ fiyati + "TL" + "\n");
//				Toast.makeText(this, "Sonuclar dosyaya yazýldý.", Toast.LENGTH_SHORT).show();
//				pw.flush();
//			}
//			
//			pw.close();
//			f.close();
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
	}
	
	public String islem(View v)
	{
		String ret = "";

		try {
			InputStream inputStream = openFileInput("siparisler.txt");

			if (inputStream != null) {
				InputStreamReader inputStreamReader = new InputStreamReader(
						inputStream);
				BufferedReader bufferedReader = new BufferedReader(
						inputStreamReader);
				String receiveString = "";
				StringBuilder stringBuilder = new StringBuilder();

				while ((receiveString = bufferedReader.readLine()) != null) {
					stringBuilder.append(receiveString);
				}

				inputStream.close();
				ret = stringBuilder.toString();
			}
		} catch (FileNotFoundException e) {
			Log.e(ALARM_SERVICE, "File not found: " + e.toString());
		} catch (IOException e) {
			Log.e(ALARM_SERVICE, "Can not read file: " + e.toString());
		}
		return ret;
	
	}

	public void sepeteDon(View v) {
		Intent intent = new Intent(Sonuc.this, SiparisVer.class);
		startActivity(intent);
	}

}
