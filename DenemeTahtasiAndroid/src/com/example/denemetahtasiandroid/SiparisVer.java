package com.example.denemetahtasiandroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SiparisVer extends Activity {

	HashMap<String, Integer> map;
	SimpleAdapter adapter;
	ListView lv;
	String[] from;
	int[] to;
	List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
	ArrayList<String> itemler = new ArrayList<String>();

	static String secilen;
	static int secilenFiyatý;
	static int secilenAdeti;
	static int butonDegeri;

	static ArrayList<Integer> secilenlerAdetler = new ArrayList<Integer>();
	static ArrayList<String> secilenlerListesi = new ArrayList<String>();
	static ArrayList<Integer> secilenlerFiyatlar = new ArrayList<Integer>();
	static int deger;

	TextView textview1;		

	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_item);

		textview1 = (TextView)findViewById(R.id.tt);
		Intent intent = getIntent();
		Bundle loanInfo = intent.getExtras();

		if (loanInfo != null) {
			butonDegeri = loanInfo.getInt("butonDegeri");
			System.out.println("loan degeri null degil .");
		} else {
			System.out.println("Loanýnfo bos = 1");
		}
		if (loanInfo == null) {
			butonDegeri = 1;
			Toast.makeText(this, "Buton Degeri ," + butonDegeri,
					Toast.LENGTH_SHORT).show();
		}

		if (butonDegeri == 1 && loanInfo != null) {
			// secilenAdeti = MainActivity.secilenYemekAdeti;
			// secilen = MainActivity.secilenYemek;
			// secilenFiyatý = MainActivity.secilenYemekFiyati;

//			secilenlerAdetler = loanInfo.getIntegerArrayList("secilenYemekAdeti");
//			secilenlerListesi = loanInfo.getStringArrayList("secilenYemek");
//			secilenlerFiyatlar = loanInfo.getIntegerArrayList("secilenYemekFiyati");
			
			secilen = loanInfo.getString("secilenYemek");
			secilenAdeti = loanInfo.getInt("secilenYemekAdeti");
			secilenFiyatý = loanInfo.getInt("secilenYemekFiyati");
			
			secilenlerListesi.add(secilen);
			secilenlerAdetler.add(secilenAdeti);
			secilenlerFiyatlar.add(secilenFiyatý);
			
			

		} else if (butonDegeri == 2 && loanInfo != null) {
//
//			secilenlerAdetler = loanInfo.getIntegerArrayList("secilenIcecekAdeti");
//			secilenlerListesi = loanInfo.getStringArrayList("secilenIcecek");
//			secilenlerFiyatlar = loanInfo.getIntegerArrayList("secilenIcecekFiyati");
			// secilenAdeti = MainActivity.secilenIcecekAdeti;
			// secilen = MainActivity.secilenYemek;
			// secilenFiyatý = MainActivity.secilenYemekFiyati;
			
			secilen = loanInfo.getString("secilenIcecek");
			secilenAdeti = loanInfo.getInt("secilenIcecekAdeti");
			secilenFiyatý = loanInfo.getInt("secilenIcecekFiyati");
			
			secilenlerListesi.add(secilen);
			secilenlerAdetler.add(secilenAdeti);
			secilenlerFiyatlar.add(secilenFiyatý);
			
			

		} else if (butonDegeri == 3 && loanInfo != null) {
//			secilenlerAdetler = loanInfo.getIntegerArrayList("secilenSalataAdeti");
//			secilenlerListesi = loanInfo.getStringArrayList("secilenSalata");
//			secilenlerFiyatlar = loanInfo.getIntegerArrayList("secilenSalataFiyati");
			// secilenAdeti = MainActivity.secilenSalataAdeti;
			// secilen = MainActivity.secilenYemek;
			// secilenFiyatý = MainActivity.secilenYemekFiyati;
			
			secilen = loanInfo.getString("secilenSalata");
			secilenAdeti = loanInfo.getInt("secilenSalataAdeti");
			secilenFiyatý = loanInfo.getInt("secilenSalataFiyati");
			
			secilenlerListesi.add(secilen);
			secilenlerAdetler.add(secilenAdeti);
			secilenlerFiyatlar.add(secilenFiyatý);
			
			
		} else if (butonDegeri == 4 && loanInfo != null) {

//			secilenlerAdetler = loanInfo.getIntegerArrayList("secilenTatliAdeti");
//			secilenlerListesi = loanInfo.getStringArrayList("secilenTatli");
//			secilenlerFiyatlar = loanInfo.getIntegerArrayList("secilenTatliFiyati");
			// secilenAdeti = MainActivity.secilenTatliAdeti;
			// secilen = MainActivity.secilenYemek;
			// secilenFiyatý = MainActivity.secilenYemekFiyati;
			
			secilen = loanInfo.getString("secilenTatli");
			secilenAdeti = loanInfo.getInt("secilenTatliAdeti");
			secilenFiyatý = loanInfo.getInt("secilenTatliFiyati");
			
			secilenlerListesi.add(secilen);
			secilenlerAdetler.add(secilenAdeti);
			secilenlerFiyatlar.add(secilenFiyatý);
		} else {
			Log.d("LoanInfoBos", "Probleemm");
		}

		lv = (ListView) findViewById(R.id.listview);
	 

		// map = new HashMap<String, Integer>();
		// map.put(secilen, secilenFiyatý);

		// create the grid item mapping
		String[] from = new String[] { "rowid", "col_1", "col_2", "col_3" };
		int[] to = new int[] { R.id.item1, R.id.item2, R.id.item3, R.id.item4 };
//		fillMaps = new ArrayList<HashMap<String, String>>();
		
		

		for (int i = 0; i < secilenlerListesi.size(); i++) {
			
			HashMap<String, String> map1 = new HashMap<String, String>();
			map1.put("rowid", String.valueOf(secilenlerAdetler.get(i)));
			map1.put("col_1", secilenlerListesi.get(i));
			map1.put("col_2", String.valueOf(secilenlerFiyatlar.get(i)));
			map1.put("col_3", "X");
			fillMaps.add(map1);
			textview1.setText("size = "+secilenlerListesi.size());
		
		}
	

		
		adapter = new SimpleAdapter(this, fillMaps, R.layout.grid_item, from,
				to);
		lv.setAdapter(adapter);

	}

	public void GeriDon(View v) {

		Intent intent = new Intent(SiparisVer.this, MainActivity.class);
		startActivity(intent);

	}
	
	public final void  OnAdding() {

		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("rowid", String.valueOf(MainActivity.secilenYemekAdeti));
		map1.put("col_1", MainActivity.secilenYemek);
		map1.put("col_2", String.valueOf(MainActivity.secilenYemekFiyati));
		map1.put("col_3", "X");
		fillMaps.add(map1);
//		adapter.notifyDataSetChanged();// refreshing adapter
	}

	 public void DeleteRow(View v) {
	 LinearLayout llMain = (LinearLayout) v.getParent();
	 TextView row = (TextView) llMain.getChildAt(0);
	 String row_no = row.getText().toString();
	 int row_no_int = Integer.parseInt(row_no);// get row number of deleted
	 // item from list
	 secilenlerListesi.remove(row_no_int - 1);
	 fillMaps.remove(row_no_int - 1);
	 adapter.notifyDataSetChanged();
	
	 }

	protected void onRestart() {
		super.onRestart();
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

	}

}
