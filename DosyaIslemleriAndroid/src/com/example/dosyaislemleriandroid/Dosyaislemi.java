package com.example.dosyaislemleriandroid;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Dosyaislemi extends Activity {

	
	TextView textview1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dosyaislemi);
		
		textview1 = (TextView)findViewById(R.id.textView1);
		
		
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
	
	
//	ArrayList<String> sonsiparis;
//
//	public void SiparisId(View v) {
//		
//		int siparisId=0;
//		String aradigimsatir;
//
//		File root = android.os.Environment.getExternalStorageDirectory();
//		File file = new File(root.getAbsolutePath() +File.separator+"siparisler.txt");
////		File file = new File(dir, "siparisler.txt");
//		if (!file.exists()) {
//			throw new RuntimeException("File not found");
//		}
//		else{
//			Log.e("Yihuu", "Yihuuuu");
//		}
//		Log.e("Testing", "Starting to read");
//		BufferedReader reader = null;
//		try {
//			reader = new BufferedReader(new FileReader(file));
//			StringBuilder builder = new StringBuilder();
//			for (String line; (line = reader.readLine()) != null;) {
//
//				builder.append(line);
//
//				sonsiparis.add(line);
//
//				if (sonsiparis.size() > 100) {
//					sonsiparis.remove(0);
//				}
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (reader != null) {
//				try {
//					reader.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		for (int i = sonsiparis.size() - 1; i >= 0; i--) {
//
//			if (sonsiparis.get(i).contains("Siparis_Id:")) {
//				aradigimsatir = sonsiparis.get(i);
//				String[] satirinParcalari = aradigimsatir.split(" ");
//				siparisId = Integer.valueOf(satirinParcalari[1]);
//
//				
//				textview1.setText("siparis id = "+siparisId);
//			}
//		}
//		textview1.setText("siparis id = "+siparisId);


}
