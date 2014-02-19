package com.example.tabsampleactivity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

	final List<Integer> yemekFiyatlar�Listesi = new ArrayList<Integer>();

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yemek);
		// Spinnerda goruntulenecek olan Listeler
		YemeklerTumListe = new ArrayList<CharSequence>();

		YemeklerTumListe.add("F�r�nda K�fte - 15 TL");
		YemeklerTumListe.add("Tavuk Sote - 12 TL");
		YemeklerTumListe.add("Ali Nazik - 12 TL");
		YemeklerTumListe.add("Oltu Kebab� - 20 TL");
		YemeklerTumListe.add("Pilav - 5 TL ");
		YemeklerTumListe.add("Makarna - 6 TL ");
		YemeklerTumListe.add("Kuru Fasulye - 8 TL");

		//sadece yemek isimlerinin oldugu liste
		yemeklerListesi.add(0, "F�r�nda K�fte");
		yemeklerListesi.add(1, "Tavuk Sote");
		yemeklerListesi.add(2, "Ali Nazik");
		yemeklerListesi.add(3, "Oltu Kebab�");
		yemeklerListesi.add(4, "Pilav");
		yemeklerListesi.add(5, "Makarna");
		yemeklerListesi.add(6, "Kuru Fasulye");

		//sadece fiyatlar�n oldugu liste
		yemekFiyatlar�Listesi.add(15);
		yemekFiyatlar�Listesi.add(12);
		yemekFiyatlar�Listesi.add(12);
		yemekFiyatlar�Listesi.add(20);
		yemekFiyatlar�Listesi.add(5);
		yemekFiyatlar�Listesi.add(6);
		yemekFiyatlar�Listesi.add(8);

		//tan�mlamalar..
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

		//yemek siparis onclick olay�.
		btnyemeksiparis.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				butonDegeri = 1;
				//Edit text'ten adet olarak deger girilmemis ise 1 al.
				if (edittextyemek.getText().toString() == null
						|| edittextyemek.getText().toString().trim().equals("")
						|| edittextyemek.getText().toString().trim().isEmpty()) {
					Toast toast = Toast.makeText(ActivityYemek.this,
							"Adet miktar� en az 1 dir !", Toast.LENGTH_SHORT);
					toast.show();
					secilenYemekAdeti = 1;

				} else if (edittextyemek.getText().toString() == "0") {
					secilenYemekAdeti = 1;
					Toast toast = Toast.makeText(ActivityYemek.this,
							"Adet miktar� en az 1 dir !", Toast.LENGTH_SHORT);
					toast.show();
					//eger deger girilmis ise , ne girildiyse onu al.
				} else {
					String no = edittextyemek.getText().toString();
					secilenYemekAdeti = Integer.parseInt(no);
				}

				//secilen yemegin' index'ini spinner'dan al�yorm ve ayn� index degerinden fiyat�na  erisiyorm
				int yemekIndex = spinneryemek.getSelectedItemPosition();
				secilenYemek = (String) yemeklerListesi.get(yemekIndex);
				secilenYemekFiyati = yemekFiyatlar�Listesi.get(yemekIndex);

				Intent intent = new Intent(ActivityYemek.this, SiparisVer.class);

				Bundle newActivityInfo = new Bundle();
				
				//Secilen degerler Diger intent'e aktar�lmak icin BUndle'a at�ld�.
				
				newActivityInfo.putString("secilenYemek", secilenYemek);
				newActivityInfo
						.putInt("secilenYemekFiyati", secilenYemekFiyati);
				newActivityInfo.putInt("secilenYemekAdeti", secilenYemekAdeti);
				newActivityInfo.putInt("butonDegeri", butonDegeri);
				Toast toast = Toast
						.makeText(ActivityYemek.this,
								"Se�ilen Yemekler Sepete eklendi !",
								Toast.LENGTH_SHORT);
				//Secilen yemekler sepete at�l�nca Toast dondu.
				intent.putExtras(newActivityInfo);

				startActivity(intent);
				toast.show();

			}
		});

	}
// Yemek sepetine gitmesi icin yemek sepeti intent'i baslat�l�yor.
	public void yemekSepeti(View v) {
		Intent intent = new Intent(ActivityYemek.this, SiparisVer.class);
		startActivity(intent);

	}

}
