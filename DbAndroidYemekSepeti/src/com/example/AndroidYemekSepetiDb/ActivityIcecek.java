package com.example.AndroidYemekSepetiDb;

import java.util.ArrayList;
import java.util.List;

import com.example.VeriTabani.VeriTabanýIcecekler;
import com.example.VeriTabani.VeriTabanýYiyecekler;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class ActivityIcecek extends Activity {

	Spinner spinnericecek;
	Button btniceceksiparis;
	Button btniceceksepet;
	TextView adettexticecek;
	EditText edittexticecek;

	List<CharSequence> IceceklerTumListe;
	public static String secilenIcecek;
	public static int secilenIcecekFiyati;
	public static int secilenIcecekAdeti;

	static int butonDegeri;

	final List<CharSequence> iceceklerListesi = new ArrayList<CharSequence>();
	final List<Integer> icecekFiyatlarýListesi = new ArrayList<Integer>();

	VeriTabanýIcecekler veritabaný;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_icecek);

		veritabaný = new VeriTabanýIcecekler(this);
		
		IceceklerTumListe = new ArrayList<CharSequence>();

		SQLiteDatabase db = veritabaný.getReadableDatabase();
		int sayac = 0;
		// Veritabanýnýn Icecekler tablosundaki tüm veriler Spinner'a eklenmek
		// uzere Listeye atýlýyor.
		Cursor cur = db.rawQuery("SELECT * FROM "
				+ VeriTabanýIcecekler.TABLE_NAME, null);
		if (cur != null) {
			if (cur.moveToFirst()) {
				do {
					String iceceginAdi = cur.getString(cur.getColumnIndex("Ad"));
					int iceceginFiyatý = cur.getInt(cur.getColumnIndex("Fiyat"));
					iceceklerListesi.add(sayac, iceceginAdi);
					IceceklerTumListe.add(iceceginAdi + " " + iceceginFiyatý + " TL");
					icecekFiyatlarýListesi.add(iceceginFiyatý);
					sayac++;
				} while (cur.moveToNext());
			}
		}
		sayac=0;
		ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter<CharSequence>(
				this, android.R.layout.simple_list_item_1, IceceklerTumListe);
		// degerler adapter'a ekleniyor.
		spinnericecek = (Spinner) findViewById(R.id.spinnericecek);
		btniceceksiparis = (Button) findViewById(R.id.btniceceksiparis);
		btniceceksepet = (Button) findViewById(R.id.btniceceksepet);
		adettexticecek = (TextView) findViewById(R.id.adettexticecek);
		edittexticecek = (EditText) findViewById(R.id.edittexticecek);

		spinnericecek.setAdapter(adapter2);

	}

	// yemek sepetine yolculuk.
	public void yemekSepeti(View v) {
		Intent intent = new Intent(ActivityIcecek.this, SiparisVer.class);
		startActivity(intent);
	}

	public void btnSiparis(View v) {

		// Hangi butondan gidildigini anlayablimek icin butonDegerleri
		// tanýmladým
		// Gelen buton degerine gore Yemeklerden, iceceklerden .. geldigni
		// anlayabiliyorm.

		butonDegeri = 2;

		// Adet girilmemis ise 1 al.
		if (edittexticecek.getText().toString() == null
				|| edittexticecek.getText().toString().trim().equals("")
				|| edittexticecek.getText().toString().trim().isEmpty()) {
			Toast toast = Toast.makeText(ActivityIcecek.this,
					"Adet miktarý en az 1 dir !", Toast.LENGTH_SHORT);
			toast.show();
			secilenIcecekAdeti = 1;
		} else if (edittexticecek.getText().toString() == "0") {
			Toast toast = Toast.makeText(ActivityIcecek.this,
					"En az 1 degerini girmeniz gerekmektedir. !",
					Toast.LENGTH_SHORT);
			toast.show();
		} else {
			String no = edittexticecek.getText().toString();
			secilenIcecekAdeti = Integer.parseInt(no);
		}

		// secilen iceccek index'i spinner'dan alýnýyor.
		// o index ' e gore icecek ismi ve fiyatý belirleniyor.
		int icecekIndex = spinnericecek.getSelectedItemPosition();
		secilenIcecek = (String) iceceklerListesi.get(icecekIndex);
		secilenIcecekFiyati = icecekFiyatlarýListesi.get(icecekIndex);

		// SiparisVer Activity'ine Bundle ile gerekli deger aktarýmlarý
		// yapýlýyor.
		Intent intent = new Intent(ActivityIcecek.this, SiparisVer.class);
		Bundle newActivityInfo = new Bundle();

		newActivityInfo.putString("secilenIcecek", secilenIcecek);
		newActivityInfo.putInt("secilenIcecekFiyati", secilenIcecekFiyati);
		newActivityInfo.putInt("secilenIcecekAdeti", secilenIcecekAdeti);
		newActivityInfo.putInt("butonDegeri", butonDegeri);

		Toast toast = Toast.makeText(ActivityIcecek.this,
				"Seçilen Icecekler Sepete eklendi !", Toast.LENGTH_SHORT);
		// degerler atýlýyor . aktivity baslatýlýyor.
		intent.putExtras(newActivityInfo);
		startActivity(intent);
		toast.show();

	}

	public void btnCikis(View v) {
		Intent intent = new Intent(ActivityIcecek.this, KullaniciGiris.class);
		startActivity(intent);
	}

}
