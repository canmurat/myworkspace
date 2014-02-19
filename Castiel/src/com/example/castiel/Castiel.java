package com.example.castiel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Castiel extends Activity {

	TextView tv;

	private String filename = "siparisler.txt";
	private String filepath = Environment.getExternalStorageDirectory()
			.getAbsolutePath();
	File myExternalFile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_castiel);

		tv = (TextView) findViewById(R.id.textView1);

	}

	public void writeFileToInternalStorage(View v) {

		String eol = System.getProperty("line.separator");
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(openFileOutput(
					"siparisler.txt", Context.MODE_PRIVATE)));
			for (int i = 0; i < 20; i++) {
				
			
			writer.write("This is a test1." + eol);
			writer.write("This is a test2." + eol);
			}
			Toast.makeText(this, "Haydar'a yazildi", Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void readFileFromInternalStorage(View v) {

		String eol = System.getProperty("line.separator");
		BufferedReader input = null;
		try {
			input = new BufferedReader(new InputStreamReader(
					openFileInput("haydar.txt")));
			String line;
			// StringBuffer buffer = new StringBuffer();
			while ((line = input.readLine()) != null) {
				tv.append(line + eol);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
