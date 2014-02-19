package com.example.denemetahtasiandroid2;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Spinner drop_down_list;
	private ArrayList<String> bayan_isimleri = new ArrayList<String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bayan_isimleri.add("Zeynep");
		bayan_isimleri.add("Ayse");
		bayan_isimleri.add("Isil");
		bayan_isimleri.add("Yasemin");
		// drowdownlist
		drop_down_list = (Spinner) findViewById(R.id.spinner1);
		// dizi adaptera ekleniyor ve dropdown seklinde bir layout secilir
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		android.R.layout.simple_spinner_dropdown_item, bayan_isimleri);
		// spinnera dropdownlist ekleniyor
		drop_down_list.setAdapter(adapter);
		// bir eleman secildiginde olay tetikleniyor ve interface metodun icinde
		// yapilmak istenenler ekleniyor

		drop_down_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					// dizideki elemanlardan hangisi secilirse o ekranda
					// gosterilir.
					// note: int arg2 arg2 secilen elemanin indexidir.
					// bayan_isimleri.get(arg2) ile de listedeki degere
					// erisiyoruz.
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						Toast.makeText(getApplicationContext(),
								bayan_isimleri.get(arg2), Toast.LENGTH_LONG).show();

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}


				});
	}
}