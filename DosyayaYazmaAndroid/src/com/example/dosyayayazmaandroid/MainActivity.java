package com.example.dosyayayazmaandroid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	TextView tv;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	public void gonder(View v) {

		tv = (TextView) findViewById(R.id.textView1);

		File root = android.os.Environment.getExternalStorageDirectory();
		tv.append("\nExternal file system root: " + root);

		File dir = new File(root.getAbsolutePath());
		dir.mkdirs();
		File file = new File(dir, "siparisler.txt");

		try {
			FileOutputStream f = new FileOutputStream(file);
			PrintWriter pw = new PrintWriter(f);
			pw.println("Hi , How are you");
			pw.println("Hello");
			Toast.makeText(this, "Yazildi", Toast.LENGTH_SHORT).show();
			pw.flush();
			pw.close();
			f.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Log.i(ACCESSIBILITY_SERVICE,
					"******* File not found. Did you"
							+ " add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
		} catch (IOException e) {
			e.printStackTrace();
		}
		tv.append("\n\nFile written to " + file);
	}
}
