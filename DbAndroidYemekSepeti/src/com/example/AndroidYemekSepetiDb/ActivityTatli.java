package com.example.AndroidYemekSepetiDb;

import java.util.ArrayList;
import java.util.List;

import com.example.VeriTabani.VeriTabanıTatlılar;
import com.example.VeriTabani.VeriTabanıYiyecekler;

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
public class ActivityTatli extends Activity {

	// OLUSTURULAN TUM YAPILAR DIGER YIYECEK ACTIVITY'LERI ILE AYNI YAPIDA..
	
	Spinner spinnertatli;
	Button btntatlisiparis;
	Button btntatlisepet;
	TextView adettexttatli;
	EditText edittexttatli;

	List<CharSequence> TatlilarTumListe;

	public static String secilenTatlı;

	public static int secilenTatliFiyati;
	public static int secilenTatliAdeti;

	int butonDegeri;

	final List<CharSequence> tatlilarListesi = new ArrayList<CharSequence>();
	final List<Integer> tatliFiyatlarıListesi = new ArrayList<Integer>();

	VeriTabanıTatlılar veritabanı;
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tatli);

		veritabanı = new VeriTabanıTatlılar(this);
		
		TatlilarTumListe = new ArrayList<CharSequence>();
	
		SQLiteDatabase db = veritabanı.getReadableDatabase(); 
		int sayac=0;
		// Veritabanının Tatlılar tablosundaki tüm veriler Spinner'a eklenmek uzere Listeye atılıyor.
		Cursor cur = db.rawQuery("SELECT * FROM " + VeriTabanıTatlılar.TABLE_NAME, null);
		if (cur != null) {
		    if (cur.moveToFirst()) {
		        do {
		        	String tatlininAdi = cur.getString(cur.getColumnIndex("Ad"));
		        	int tatlininFiyati = cur.getInt(cur.getColumnIndex("Fiyat"));
		        	tatlilarListesi.add(sayac,tatlininAdi);
		        	TatlilarTumListe.add(tatlininAdi + " " +tatlininFiyati + " TL");
		        	tatliFiyatlarıListesi.add(tatlininFiyati);
		        	sayac++;
		        } while (cur.moveToNext());
		    }
		}
		sayac=0;
		ArrayAdapter<CharSequence> adapter4 = new ArrayAdapter<CharSequence>(
				this, android.R.layout.simple_list_item_1, TatlilarTumListe);

		spinnertatli = (Spinner) findViewById(R.id.spinnertatli);
		btntatlisiparis = (Button) findViewById(R.id.btntatlisiparis);
		btntatlisepet = (Button) findViewById(R.id.btntatlisepet);
		adettexttatli = (TextView) findViewById(R.id.adettexttatli);
		edittexttatli = (EditText) findViewById(R.id.edittexttatli);

		spinnertatli.setAdapter(adapter4);

	}

	public void yemekSepeti(View v) {
		Intent intent = new Intent(ActivityTatli.this, SiparisVer.class);
		startActivity(intent);

	}

	public void btnSiparis(View v) {
		butonDegeri = 4;

		if (edittexttatli.getText().toString() == null
				|| edittexttatli.getText().toString().trim().equals("")
				|| edittexttatli.getText().toString().isEmpty()) {
			secilenTatliAdeti = 1;
			Toast toast = Toast.makeText(ActivityTatli.this,
					"Adet miktarı en az 1 dir !", Toast.LENGTH_SHORT);
			toast.show();
		} else if (edittexttatli.getText().toString() == "0") {
			Toast toast = Toast.makeText(ActivityTatli.this,
					"En az 1 degerini girmeniz gerekmektedir. !",
					Toast.LENGTH_SHORT);
			toast.show();
		} else {
			secilenTatliAdeti = Integer.parseInt(edittexttatli.getText()
					.toString());
		}
		int tatliIndex = spinnertatli.getSelectedItemPosition();
		secilenTatlı = (String) tatlilarListesi.get(tatliIndex);
		secilenTatliFiyati = tatliFiyatlarıListesi.get(tatliIndex);

		Intent intent = new Intent(ActivityTatli.this, SiparisVer.class);
		Bundle newActivityInfo = new Bundle();

		Toast toast = Toast.makeText(ActivityTatli.this,
				"Seçilen Tatlilar Sepete eklendi !", Toast.LENGTH_SHORT);

		newActivityInfo.putString("secilenTatli", secilenTatlı);
		newActivityInfo.putInt("secilenTatliFiyati", secilenTatliFiyati);
		newActivityInfo.putInt("secilenTatliAdeti", secilenTatliAdeti);
		newActivityInfo.putInt("butonDegeri", butonDegeri);

		intent.putExtras(newActivityInfo);

		// intent.putExtra("secilenTatli", secilenTatlı);
		// intent.putExtra("secilenTatliFiyati", secilenTatliFiyati);
		// intent.putExtra("adetTatlilar", adetTatlilar);
		// intent.putExtra("butonDegeri", butonDegeri);
		toast.show();
		startActivity(intent);

	}

	public void btnCikis(View v)
	{
		Intent intent = new Intent(ActivityTatli.this, KullaniciGiris.class);
		startActivity(intent);
	}

}
