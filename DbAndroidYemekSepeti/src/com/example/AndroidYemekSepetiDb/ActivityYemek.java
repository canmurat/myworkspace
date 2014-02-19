package com.example.AndroidYemekSepetiDb;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.VeriTabani.VeriTabanýYiyecekler;

@SuppressLint("NewApi")
public class ActivityYemek extends Activity {

	Spinner spinneryemek;
	Button btnyemeksiparis;
	Button btnyemeksepet;
	TextView adettextyemek;
	EditText edittextyemek;

	List<CharSequence> YemeklerTumListe;

	public static String secilenYemek;
	public static int secilenYemekFiyati;
	public static int secilenYemekAdeti;

	int butonDegeri;

	// yemekler listesini tutan CharSequence Listesi
	final List<CharSequence> yemeklerListesi = new ArrayList<CharSequence>();

	final List<Integer> yemekFiyatlarýListesi = new ArrayList<Integer>();

	VeriTabanýYiyecekler veritabaný;
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yemek);

		
		veritabaný = new VeriTabanýYiyecekler(this);
		
		// Spinnerda goruntulenecek olan Listeler
		YemeklerTumListe = new ArrayList<CharSequence>();
		
		SQLiteDatabase db = veritabaný.getReadableDatabase(); 
		int sayac=0;
		// Veritabanýnýn Yemekler tablosundaki tüm veriler Spinner'a eklenmek uzere Listeye atýlýyor.
		Cursor cur = db.rawQuery("SELECT * FROM " + VeriTabanýYiyecekler.TABLE_NAME, null);
		if (cur != null) {
		    if (cur.moveToFirst()) {
		        do {
		        	String yemeginAdi = cur.getString(cur.getColumnIndex("Ad"));
		        	int yemeginFiyatý = cur.getInt(cur.getColumnIndex("Fiyat"));
		        	yemeklerListesi.add(sayac,yemeginAdi);
		        	YemeklerTumListe.add(yemeginAdi + " " +yemeginFiyatý+ " TL");
		        	yemekFiyatlarýListesi.add(yemeginFiyatý);
		        	sayac++;
		        } while (cur.moveToNext());
		    }
		}

		sayac=0;
		//tanýmlamalar..
		spinneryemek = (Spinner) findViewById(R.id.spinneryemek);
		btnyemeksiparis = (Button) findViewById(R.id.btnyemeksiparis);
		btnyemeksepet = (Button) findViewById(R.id.btnyemeksepet);
		adettextyemek = (TextView) findViewById(R.id.adettextyemek);
		edittextyemek = (EditText) findViewById(R.id.edittextyemek);

		//YemeklerTumListe , ArrayAdapter'a ekleniyor.
		ArrayAdapter<CharSequence> adapter1 = new ArrayAdapter<CharSequence>(
				this, android.R.layout.simple_dropdown_item_1line,
				YemeklerTumListe);
		spinneryemek.setAdapter(adapter1);

		//yemek siparis onclick olayý.
		btnyemeksiparis.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				butonDegeri = 1;
				//Edit text'ten adet olarak deger girilmemis ise 1 al.
				if (edittextyemek.getText().toString() == null
						|| edittextyemek.getText().toString().trim().equals("")
						|| edittextyemek.getText().toString().trim().isEmpty()) {
					Toast toast = Toast.makeText(ActivityYemek.this,
							"Adet miktarý en az 1 dir !", Toast.LENGTH_SHORT);
					toast.show();
					secilenYemekAdeti = 1;

				} else if (edittextyemek.getText().toString() == "0") {
					secilenYemekAdeti = 1;
					Toast toast = Toast.makeText(ActivityYemek.this,
							"Adet miktarý en az 1 dir !", Toast.LENGTH_SHORT);
					toast.show();
					//eger deger girilmis ise , ne girildiyse onu al.
				} else {
					String no = edittextyemek.getText().toString();
					secilenYemekAdeti = Integer.parseInt(no);
				}

				//secilen yemegin' index'ini spinner'dan alýyorm ve ayný index degerinden fiyatýna  erisiyorm
				int yemekIndex = spinneryemek.getSelectedItemPosition();
				secilenYemek = (String) yemeklerListesi.get(yemekIndex);
				secilenYemekFiyati = yemekFiyatlarýListesi.get(yemekIndex);

				Intent intent = new Intent(ActivityYemek.this, SiparisVer.class);

				Bundle newActivityInfo = new Bundle();
				
				//Secilen degerler Diger intent'e aktarýlmak icin BUndle'a atýldý.
				
				newActivityInfo.putString("secilenYemek", secilenYemek);
				newActivityInfo
						.putInt("secilenYemekFiyati", secilenYemekFiyati);
				newActivityInfo.putInt("secilenYemekAdeti", secilenYemekAdeti);
				newActivityInfo.putInt("butonDegeri", butonDegeri);
				Toast toast = Toast
						.makeText(ActivityYemek.this,
								"Seçilen Yemekler Sepete eklendi !",
								Toast.LENGTH_SHORT);
				//Secilen yemekler sepete atýlýnca Toast dondu.
				intent.putExtras(newActivityInfo);

				startActivity(intent);
				toast.show();

			}
		});

	}
// Yemek sepetine gitmesi icin yemek sepeti intent'i baslatýlýyor.
	public void yemekSepeti(View v) {
		Intent intent = new Intent(ActivityYemek.this, SiparisVer.class);
		startActivity(intent);

	}
	public void btnCikis(View v)
	{
		Intent intent = new Intent(ActivityYemek.this, KullaniciGiris.class);
		startActivity(intent);
	}

}
