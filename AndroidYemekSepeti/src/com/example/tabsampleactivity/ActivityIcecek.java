package com.example.tabsampleactivity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
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
	final List<Integer> icecekFiyatlar�Listesi = new ArrayList<Integer>();

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_icecek);

		IceceklerTumListe = new ArrayList<CharSequence>();
		//Tum icecek isimleri ve fiyatlarinin tutuldugu liste.
		IceceklerTumListe.add("Cay - 3 TL ");
		IceceklerTumListe.add("Kahve - 5 TL");
		IceceklerTumListe.add("S�cak �ikolata - 5 TL");
		IceceklerTumListe.add("Salep - 5 TL");
		IceceklerTumListe.add("Su  - 1 TL");
		//iceceklerin ayr� tutuldugu liste
		iceceklerListesi.add(0, "Cay");
		iceceklerListesi.add(1, "Kahve");
		iceceklerListesi.add(2, "S�cak �ikolata");
		iceceklerListesi.add(3, "Salep");
		iceceklerListesi.add(4, "Su");
		//fiyatlar�n tutuldugu liste.
		icecekFiyatlar�Listesi.add(3);
		icecekFiyatlar�Listesi.add(5);
		icecekFiyatlar�Listesi.add(5);
		icecekFiyatlar�Listesi.add(5);
		icecekFiyatlar�Listesi.add(1);

		ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter<CharSequence>(
				this, android.R.layout.simple_list_item_1, IceceklerTumListe);
		//degerler adapter'a ekleniyor.
		spinnericecek = (Spinner) findViewById(R.id.spinnericecek);
		btniceceksiparis = (Button) findViewById(R.id.btniceceksiparis);
		btniceceksepet = (Button) findViewById(R.id.btniceceksepet);
		adettexticecek = (TextView) findViewById(R.id.adettexticecek);
		edittexticecek = (EditText) findViewById(R.id.edittexticecek);

		spinnericecek.setAdapter(adapter2);

	}
	//yemek sepetine yolculuk.
	public void yemekSepeti(View v) {
		Intent intent = new Intent(ActivityIcecek.this, SiparisVer.class);
		startActivity(intent);
	}

	public void btnSiparis(View v) {
		
		// Hangi butondan gidildigini anlayablimek icin butonDegerleri tan�mlad�m
		// Gelen buton degerine gore Yemeklerden, iceceklerden .. geldigni anlayabiliyorm.
		
		butonDegeri = 2;

		// Adet girilmemis ise 1 al.
		if (edittexticecek.getText().toString() == null
				|| edittexticecek.getText().toString().trim().equals("")
				|| edittexticecek.getText().toString().trim().isEmpty()) {
			Toast toast = Toast.makeText(ActivityIcecek.this,
					"Adet miktar� en az 1 dir !", Toast.LENGTH_SHORT);
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

		//secilen iceccek index'i spinner'dan al�n�yor.
		// o index ' e gore icecek ismi  ve fiyat� belirleniyor.
		int icecekIndex = spinnericecek.getSelectedItemPosition();
		secilenIcecek = (String) iceceklerListesi.get(icecekIndex);
		secilenIcecekFiyati = icecekFiyatlar�Listesi.get(icecekIndex);

		//SiparisVer Activity'ine Bundle ile gerekli deger aktar�mlar� yap�l�yor.
		Intent intent = new Intent(ActivityIcecek.this, SiparisVer.class);
		Bundle newActivityInfo = new Bundle();

		newActivityInfo.putString("secilenIcecek", secilenIcecek);
		newActivityInfo.putInt("secilenIcecekFiyati", secilenIcecekFiyati);
		newActivityInfo.putInt("secilenIcecekAdeti", secilenIcecekAdeti);
		newActivityInfo.putInt("butonDegeri", butonDegeri);

		Toast toast = Toast.makeText(ActivityIcecek.this,
				"Se�ilen Icecekler Sepete eklendi !", Toast.LENGTH_SHORT);
		//degerler at�l�yor . aktivity baslat�l�yor.
		intent.putExtras(newActivityInfo);
		startActivity(intent);
		toast.show();

	}

}
