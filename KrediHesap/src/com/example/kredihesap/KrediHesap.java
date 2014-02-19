package com.example.kredihesap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class KrediHesap extends Activity {

	TextView textview1;
	TextView textview2;
	TextView textview3;
	TextView textview4;
	Button buton;

	TextView kreditutarisonuc;
	TextView kredivadesisonuc;
	TextView faizhesabisonuc;

	SeekBar seekbar1;
	SeekBar seekbar2;

	Spinner spinner;
	//kredi tiplerini ve karsýlarýnda faiz oranlarýný tuttugum Hashmap
	Map<String, Float> kk = new HashMap<String, Float>();

	// Kredi Tiplerini tuttugum String dizisi 
	private static String[] kredi_tipleri = new String[] { "Tekne Kredisi",
			"Bireysel ihtiyac kredisi", "Egitim Kredisi", "Evlilik Kredisi",
			"Konut","Motosiklet Kredisi","Tadilat Kredisi" };

	// Günlük Faiz Getirisi = (Ana Para / 100) X (Faiz Oraný / 365) X Gün Sayýsý
	//
	// Aylýk Faiz Getirisi = (Ana Para / 100) X (Faiz Oraný / 12) X Ay Sayýsý
	//
	// Yýllýk Faiz Getirisi = (Ana Para / 100) X (Faiz Oraný) X Yýl Sayýsý
	//

	final static int yil = 100;
	final static int ay = 12000;
	final static int gun = 365000;
	
	public static double ayliktaksitsonucu;
	public static double geriodenecektoplamtutar;
	int kreditutari;
	float faiz;
	float faizorani;
	int ay_suresi;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kredi_hesap);

		//tanýmlamalar.
		kreditutarisonuc = (TextView) findViewById(R.id.kreditutarisonuc);
		kredivadesisonuc = (TextView) findViewById(R.id.kredivadesisonuc);
		faizhesabisonuc = (TextView) findViewById(R.id.faizoranisonuc);
		textview1 = (TextView) findViewById(R.id.textView1);
		textview2 = (TextView) findViewById(R.id.sonuc1);
		textview3 = (TextView) findViewById(R.id.textView3);
		textview4 = (TextView) findViewById(R.id.sonuc2);
		seekbar1 = (SeekBar) findViewById(R.id.seekBar1);
		seekbar2 = (SeekBar) findViewById(R.id.seekBar2);

		buton = (Button) findViewById(R.id.button1);

		faizhesabisonuc = (TextView) findViewById(R.id.faizoranisonuc);
		spinner = (Spinner) findViewById(R.id.spinner1);

		//Degerler eslestiriliyor.
		kk.put("Tekne Kredisi", 1.417f);
		kk.put("Bireysel ihtiyac kredisi", 1.18f);
		kk.put("Egitim Kredisi", 1.29f);
		kk.put("Evlilik Kredisi", 1.18f);
		kk.put("Konut", 0.98f);
		kk.put("Motosiklet Kredisi",1.69f);
		kk.put("Tadilat Kredisi",1.04f);
	

		// ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
		// this, android.R.layout.simple_list_item_1, krediTipleri);
		// spinner.setAdapter(adapter);

		// SeekBar kontrolünün event'i yakalanýyor

		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
				R.layout.kredi_tipleri, kredi_tipleri);

		spinner.setAdapter(arrayAdapter);
		
		
		//spinner 'da ki degisikliklere gore faiz oraný hesaplanýyor ve yazýlýyor.
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {

				String secilenDeger = spinner.getItemAtPosition(arg2)
						.toString();

				faizorani = kk.get(secilenDeger);
				faizhesabisonuc.setText("" + kk.get(secilenDeger));

			}

			public void onNothingSelected(AdapterView<?> arg0) {

			}

		});

		//ay suresi degeri alýnýyor .
		seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				ay_suresi = progress;
				kredivadesisonuc.setText(String.valueOf(progress));
			}

			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}
		});
		
		//kredi tutari degeri alýnýyor.
		seekbar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
					
				kreditutari = progress * 1000;
				kreditutarisonuc.setText(String.valueOf(kreditutari));

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}
		});
		
	}
	// faiz hesabý , geriodenecek tutar ve aylýk taksit sonuclarý hesaplanýp diger activity'e aktarýlýyor.
	public void hesapla(View v){
		
		faiz = (kreditutari * faizorani * ay_suresi) / ay;
		geriodenecektoplamtutar = kreditutari + faiz;
		ayliktaksitsonucu = geriodenecektoplamtutar / ay_suresi;
		
		Intent intent = new Intent(KrediHesap.this,Sonuc.class);
		startActivity(intent);
		
	}
}
