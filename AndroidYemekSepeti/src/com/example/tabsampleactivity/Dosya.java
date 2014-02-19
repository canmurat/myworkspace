package com.example.tabsampleactivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Dosya extends Activity {

	public void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
	
    }
	
	ArrayList<String> sonsiparis;

	int sayac=0;
	static int siparisId = 0;
	String aradigimsatir;
	StringBuilder stringBuilder;

	BufferedReader input = null;
	public int SiparisId() throws IOException {
//		
		
		try {
			input = new BufferedReader(new InputStreamReader(
					openFileInput("siparisler.txt")));
			String line;
			// StringBuffer buffer = new StringBuffer();
			while ((line = input.readLine()) != null) {
				
				sayac++;
			}
			siparisId = sayac; 
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}}
		return siparisId;
	}
	

	// dosyadan satýr satýr okuyoruz

	// try {
	// InputStream inputStream = openFileInput("siparisler.txt");
	//
	// if (inputStream != null) {
	// InputStreamReader inputStreamReader = new InputStreamReader(
	// inputStream);
	// BufferedReader bufferedReader = new BufferedReader(
	// inputStreamReader);
	// String receiveString = "";
	// stringBuilder = new StringBuilder();
	//
	// while ((receiveString = bufferedReader.readLine()) != null) {
	// stringBuilder.append(receiveString);
	// }
	//
	// inputStream.close();
	// ret = stringBuilder.toString();
	// }
	// } catch (FileNotFoundException e) {
	// Log.e(ALARM_SERVICE, "File not found: " + e.toString());
	// } catch (IOException e) {
	// Log.e(ALARM_SERVICE, "Can not read file: " + e.toString());
	// }

	public String islem() {

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

		// return ++siparisId;

	}

	// String aradigimsatir;
	//
	// sonsiparis = new ArrayList<String>();
	// BufferedReader input = null;
	//
	// try {
	// input = new BufferedReader(new InputStreamReader(
	// openFileInput("siparisler.txt")));
	// String line;
	// // StringBuffer buffer = new StringBuffer();
	// while ((line = input.readLine()) != null) {
	//
	// sonsiparis.add(line);
	//
	// if (sonsiparis.size() > 20) {
	// sonsiparis.remove(0);
	// }
	//
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// for (int i = sonsiparis.size() - 1; i >= 0; i--) {
	//
	// if (sonsiparis.get(i).contains("Siparis_Id:")) {
	// aradigimsatir = sonsiparis.get(i);
	// String[] satirinParcalari = aradigimsatir.split(" ");
	// siparisId = Integer.valueOf(satirinParcalari[1]);
	//
	//
	// return ++siparisId;
	// }
	// }
	//

	// int siparisId = 0;
	// String aradigimsatir;
	//
	// File root = android.os.Environment.getExternalStorageDirectory();
	// File dir = new File(root.getAbsolutePath());
	// File file = new File(dir, "siparisler.txt");
	// if (!file.exists()) {
	// throw new RuntimeException("File not found");
	// }
	// Log.e("Testing", "Starting to read");
	// BufferedReader reader = null;
	// try {
	// reader = new BufferedReader(new FileReader(file));
	// // StringBuilder builder = new StringBuilder();
	// for (String line; (line = reader.readLine()) != null;) {
	//
	// // builder.append(line);
	//
	// sonsiparis.add(line);
	//
	// if (sonsiparis.size() > 100) {
	// sonsiparis.remove(0);
	// }
	//
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// if (reader != null) {
	// try {
	// reader.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// }
	// for (int i = sonsiparis.size() - 1; i >= 0; i--) {
	//
	// if (sonsiparis.get(i).contains("Siparis_Id:")) {
	// aradigimsatir = sonsiparis.get(i);
	// String[] satirinParcalari = aradigimsatir.split(" ");
	// siparisId = Integer.valueOf(satirinParcalari[1]);
	//
	// return siparisId;
	// }
	// }
	// return siparisId;

	// int siparisId = 0;
	// String aradigimsatir;
	//
	// File root = android.os.Environment.getExternalStorageDirectory();
	// File dir = new File(root.getAbsolutePath());
	// File file = new File(dir, "siparisler.txt");
	// try {
	//
	// FileInputStream f = new FileInputStream(file);
	// InputStreamReader isr = new InputStreamReader(f);
	// BufferedReader br = new BufferedReader(isr, 8192);
	// for (String line; (line = br.readLine()) != null;) {
	//
	// sonsiparis.add(line);
	//
	// if (sonsiparis.size() > 100) {
	// sonsiparis.remove(0);
	// }
	//
	// }
	// br.close();
	// isr.close();
	// is.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// for (int i = sonsiparis.size() - 1; i >= 0; i--) {
	//
	// if (sonsiparis.get(i).contains("Siparis_Id:")) {
	// aradigimsatir = sonsiparis.get(i);
	// String[] satirinParcalari = aradigimsatir.split(" ");
	// siparisId = Integer.valueOf(satirinParcalari[1]);
	//
	// return siparisId;
	// }
	// }
	// return siparisId;

}
