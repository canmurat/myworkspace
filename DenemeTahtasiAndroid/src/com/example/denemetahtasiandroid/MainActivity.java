package com.example.denemetahtasiandroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
	public static String mcan = "";

	Spinner spinnerYemekler;
	Spinner spinnerIcecekler;
	Spinner spinnerSalatalar;
	Spinner spinnerTatlilar;
	Button butonYemek;
	Button butonIcecek;
	Button butonSalata;
	Button butonTatli;
	Button yemekSepeti;
	EditText edittextYemek;
	EditText edittextIcecek;
	EditText edittextSalata;
	EditText edittextTatli;
	int adetYemekler = 1, adetIcecekler = 1, adetSalatalar = 1,
			adetTatlilar = 1;
	int butonDegeri;
	Boolean sepetBos = true;
	public static String secilenYemek;
	public static String secilenIcecek;
	public static String secilenSalata;
	public static String secilenTatl�;
	public static int secilenYemekFiyati;
	public static int secilenIcecekFiyati;
	public static int secilenSalataFiyati;
	public static int secilenTatliFiyati;
	public static int secilenYemekAdeti;
	public static int secilenIcecekAdeti;
	public static int secilenSalataAdeti;
	public static int secilenTatliAdeti;

	public static ArrayList<Integer> secilenlerAdetler;
	public static ArrayList<String> secilenlerListesi;
	public static ArrayList<Integer> secilenlerFiyatlar;
	
	static int deger;

	TextView textview1;
	protected void onCreate(Bundle savedInstanceState) {
		
		textview1 = (TextView)findViewById(R.id.hahay);
		
		secilenlerAdetler = new ArrayList<Integer>();
		secilenlerListesi = new ArrayList<String>();
		secilenlerFiyatlar = new ArrayList<Integer>();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		spinnerYemekler = (Spinner) findViewById(R.id.spinnerYemekler);
		spinnerIcecekler = (Spinner) findViewById(R.id.spinnerIcecekler);
		spinnerSalatalar = (Spinner) findViewById(R.id.spinnerSalatalar);
		spinnerTatlilar = (Spinner) findViewById(R.id.spinnerTatlilar);
		butonYemek = (Button) findViewById(R.id.butonYemek);
		butonIcecek = (Button) findViewById(R.id.butonIcecek);
		butonSalata = (Button) findViewById(R.id.butonSalata);
		butonTatli = (Button) findViewById(R.id.butonTatli);
		yemekSepeti = (Button) findViewById(R.id.yemeksepeti);
		edittextYemek = (EditText) findViewById(R.id.edittextYemek);
		edittextIcecek = (EditText) findViewById(R.id.edittextIcecek);
		edittextSalata = (EditText) findViewById(R.id.edittextSalata);
		edittextTatli = (EditText) findViewById(R.id.edittextTatli);

		HashMap<String, Integer> yemekler = new HashMap<String, Integer>();
		HashMap<String, Integer> icecekler = new HashMap<String, Integer>();
		HashMap<String, Integer> salatalar = new HashMap<String, Integer>();
		HashMap<String, Integer> tatlilar = new HashMap<String, Integer>();

		yemekler.put("F�r�nda K�fte", 13);
		yemekler.put("Tavuk Sote", 15);
		yemekler.put("Ali Nazik", 15);
		yemekler.put("Oltu Kebab�", 15);
		yemekler.put("Pilav", 15);
		yemekler.put("Makarna", 15);
		yemekler.put("Kuru Fasulye", 15);
		yemekler.put("Mant�", 6);
		icecekler.put("Kola", 3);
		icecekler.put("Cay", 2);
		icecekler.put("Kahve", 4);
		icecekler.put("S�cak �ikolata", 6);
		icecekler.put("Salep", 6);
		salatalar.put("Coban Salata", 10);
		salatalar.put("Meyve Salatas�", 12);
		salatalar.put("Yogurtlu Salata", 8);
		salatalar.put("Rus Salatas�", 10);
		salatalar.put("B�r�lce Salatas�", 10);
		tatlilar.put("Revani", 7);
		tatlilar.put("Kaday�f", 8);
		tatlilar.put("S�tla�", 7);
		tatlilar.put("Ekmek Kaday�f�", 10);
		tatlilar.put("Seker Pare", 7);

		List<CharSequence> YemeklerTumListe = new ArrayList<CharSequence>();
		List<CharSequence> IceceklerTumListe = new ArrayList<CharSequence>();
		List<CharSequence> SalatalarTumListe = new ArrayList<CharSequence>();
		List<CharSequence> TatlilarTumListe = new ArrayList<CharSequence>();

		final List<CharSequence> yemeklerListesi = new ArrayList<CharSequence>();
		final List<CharSequence> iceceklerListesi = new ArrayList<CharSequence>();
		final List<CharSequence> salatalarListesi = new ArrayList<CharSequence>();
		final List<CharSequence> tatlilarListesi = new ArrayList<CharSequence>();

		final List<Integer> yemekFiyatlar�Listesi = new ArrayList<Integer>();
		final List<Integer> icecekFiyatlar�Listesi = new ArrayList<Integer>();
		final List<Integer> salataFiyatlar�Listesi = new ArrayList<Integer>();
		final List<Integer> tatliFiyatlar�Listesi = new ArrayList<Integer>();

		YemeklerTumListe.add("F�r�nda K�fte - 15 TL");
		YemeklerTumListe.add("Tavuk Sote - 12 TL");
		YemeklerTumListe.add("Ali Nazik - 12 TL");
		YemeklerTumListe.add("Oltu Kebab� - 20 TL");
		YemeklerTumListe.add("Pilav - 5 TL ");
		YemeklerTumListe.add("Makarna - 6 TL ");
		YemeklerTumListe.add("Kuru Fasulye - 8 TL");
		IceceklerTumListe.add("Cay - 3 TL ");
		IceceklerTumListe.add("Kahve - 5 TL");
		IceceklerTumListe.add("S�cak �ikolata - 5 TL");
		IceceklerTumListe.add("Salep - 5 TL");
		IceceklerTumListe.add("Su  - 1 TL");
		SalatalarTumListe.add("Coban Salata - 10 TL");
		SalatalarTumListe.add("Meyve Salatas� - 10 TL");
		SalatalarTumListe.add("Yogurtlu Salata - 10 TL");
		SalatalarTumListe.add("Rus Salatas� - 10 TL");
		SalatalarTumListe.add("B�r�lce Salatas� - 10 TL");
		TatlilarTumListe.add("Revani - 8 TL");
		TatlilarTumListe.add("Kaday�f - 8 TL");
		TatlilarTumListe.add("S�tla� - 8 TL");
		TatlilarTumListe.add("Ekmek Kaday�f� - 8 TL");
		TatlilarTumListe.add("Seker Pare - 8 TL");

		yemeklerListesi.add(0, "F�r�nda K�fte");
		yemeklerListesi.add(1, "Tavuk Sote");
		yemeklerListesi.add(2, "Ali Nazik");
		yemeklerListesi.add(3, "Oltu Kebab�");
		yemeklerListesi.add(4, "Pilav");
		yemeklerListesi.add(5, "Makarna");
		yemeklerListesi.add(6, "Kuru Fasulye");

		iceceklerListesi.add(0, "Cay");
		iceceklerListesi.add(1, "Kahve");
		iceceklerListesi.add(2, "S�cak �ikolata");
		iceceklerListesi.add(3, "Salep");
		iceceklerListesi.add(4, "Su");

		salatalarListesi.add(0, "Coban Salatas�");
		salatalarListesi.add(1, "Meyve Salatas�");
		salatalarListesi.add(2, "Yogurtlu Salata");
		salatalarListesi.add(3, "Rus Salatas�");
		salatalarListesi.add(4, "B�r�lce Salatas�");

		tatlilarListesi.add(0, "Revani");
		tatlilarListesi.add(1, "Kaday�f");
		tatlilarListesi.add(2, "S�tla�");
		tatlilarListesi.add(3, "Ekmek Kaday�f�");
		tatlilarListesi.add(4, "Seker Pare");

		yemekFiyatlar�Listesi.add(15);
		yemekFiyatlar�Listesi.add(12);
		yemekFiyatlar�Listesi.add(12);
		yemekFiyatlar�Listesi.add(20);
		yemekFiyatlar�Listesi.add(5);
		yemekFiyatlar�Listesi.add(6);
		yemekFiyatlar�Listesi.add(8);

		icecekFiyatlar�Listesi.add(3);
		icecekFiyatlar�Listesi.add(5);
		icecekFiyatlar�Listesi.add(6);
		icecekFiyatlar�Listesi.add(6);
		icecekFiyatlar�Listesi.add(3);

		salataFiyatlar�Listesi.add(7);
		salataFiyatlar�Listesi.add(8);
		salataFiyatlar�Listesi.add(7);
		salataFiyatlar�Listesi.add(7);
		salataFiyatlar�Listesi.add(8);

		tatliFiyatlar�Listesi.add(6);
		tatliFiyatlar�Listesi.add(6);
		tatliFiyatlar�Listesi.add(6);
		tatliFiyatlar�Listesi.add(6);
		tatliFiyatlar�Listesi.add(6);

		ArrayAdapter<CharSequence> arrayYemekler = new ArrayAdapter<CharSequence>(
				this, android.R.layout.simple_spinner_dropdown_item,
				(YemeklerTumListe));
		ArrayAdapter<CharSequence> arrayIcecekler = new ArrayAdapter<CharSequence>(
				this, android.R.layout.simple_spinner_dropdown_item,
				IceceklerTumListe);
		ArrayAdapter<CharSequence> arraySalatalar = new ArrayAdapter<CharSequence>(
				this, android.R.layout.simple_spinner_dropdown_item,
				SalatalarTumListe);
		ArrayAdapter<CharSequence> arrayTatlilar = new ArrayAdapter<CharSequence>(
				this, android.R.layout.simple_spinner_dropdown_item,
				TatlilarTumListe);

		spinnerYemekler.setAdapter(arrayYemekler);
		spinnerIcecekler.setAdapter(arrayIcecekler);
		spinnerSalatalar.setAdapter(arraySalatalar);
		spinnerTatlilar.setAdapter(arrayTatlilar);

		butonYemek.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				butonDegeri = 1;

				sepetBos = false;

				if (edittextYemek.getText().toString() == null
						|| edittextYemek.getText().toString().trim().equals("")
						|| edittextYemek.getText().toString().trim().isEmpty()) {
					Toast toast = Toast.makeText(MainActivity.this,
							"Adet miktar� en az 1 dir !", Toast.LENGTH_SHORT);
					toast.show();
					secilenYemekAdeti = 1;

				} else if (edittextYemek.getText().toString() == "0") {
					secilenYemekAdeti = 1;
					Toast toast = Toast.makeText(MainActivity.this,
							"Adet miktar� en az 1 dir !", Toast.LENGTH_SHORT);
					toast.show();
				} else {
					String no = edittextYemek.getText().toString();
					secilenYemekAdeti = Integer.parseInt(no);
				}

				int yemekIndex = spinnerYemekler.getSelectedItemPosition();
				secilenYemek = (String) yemeklerListesi.get(yemekIndex);
				secilenYemekFiyati = yemekFiyatlar�Listesi.get(yemekIndex);

				secilenlerAdetler.add(secilenYemekAdeti);
				secilenlerListesi.add(secilenYemek);
				secilenlerFiyatlar.add(secilenYemekFiyati);

				Intent intent = new Intent(MainActivity.this, SiparisVer.class);

				Bundle newActivityInfo = new Bundle();

//				newActivityInfo.putStringArrayList("secilenYemek",
//						secilenlerListesi);
//				newActivityInfo.putIntegerArrayList("secilenYemekFiyati",
//						secilenlerFiyatlar);
//				newActivityInfo.putIntegerArrayList("secilenYemekAdeti",
//						secilenlerAdetler);
				
				newActivityInfo.putString("secilenYemek",secilenYemek);
				newActivityInfo.putInt("secilenYemekFiyati",secilenYemekFiyati);
				newActivityInfo.putInt("secilenYemekAdeti",secilenYemekAdeti);
				newActivityInfo.putInt("butonDegeri", butonDegeri);
				newActivityInfo.putInt("size",secilenlerListesi.size());
				Toast toast = Toast
						.makeText(MainActivity.this,
								"Se�ilen Yemekler Sepete eklendi !",
								Toast.LENGTH_SHORT);

				intent.putExtras(newActivityInfo);

				// intent.putExtra("secilenYemek", secilenYemek);
				// intent.putExtra("secilenYemekFiyati", secilenYemekFiyati);
				// intent.putExtra("adetYemekler", adetYemekler);
				// intent.putExtra("butonDegeri", butonDegeri);

				startActivity(intent);
				toast.show();

			}
		});
		butonIcecek.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				butonDegeri = 2;
				sepetBos = false;

				if (edittextIcecek.getText().toString() == null
						|| edittextIcecek.getText().toString().trim()
								.equals("")
						|| edittextIcecek.getText().toString().trim().isEmpty()) {
					secilenIcecekAdeti = 1;
				} else if (edittextIcecek.getText().toString() == "0") {
					Toast toast = Toast.makeText(MainActivity.this,
							"En az 1 degerini girmeniz gerekmektedir. !",
							Toast.LENGTH_SHORT);
					toast.show();
				} else {
					String no = edittextIcecek.getText().toString();
					secilenIcecekAdeti = Integer.parseInt(no);
				}

				int icecekIndex = spinnerIcecekler.getSelectedItemPosition();
				secilenIcecek = (String) iceceklerListesi.get(icecekIndex);
				secilenIcecekFiyati = icecekFiyatlar�Listesi.get(icecekIndex);

				Intent intent = new Intent(MainActivity.this, SiparisVer.class);
				Bundle newActivityInfo = new Bundle();

				secilenlerAdetler.add(secilenIcecekAdeti);
				secilenlerListesi.add(secilenIcecek);
				secilenlerFiyatlar.add(secilenIcecekFiyati);

//				newActivityInfo.putStringArrayList("secilenIcecek",
//						secilenlerListesi);
//				newActivityInfo.putIntegerArrayList("secilenIcecekFiyati",
//						secilenlerFiyatlar);
//				newActivityInfo.putIntegerArrayList("secilenIcecekAdeti",
//						secilenlerAdetler);
				
				newActivityInfo.putString("secilenIcecek",secilenIcecek);
				newActivityInfo.putInt("secilenIcecekFiyati",secilenIcecekFiyati);
				newActivityInfo.putInt("secilenIcecekAdeti",secilenIcecekAdeti);
				newActivityInfo.putInt("butonDegeri", butonDegeri);
			
				Toast toast = Toast.makeText(MainActivity.this,
						"Se�ilen Icecekler Sepete eklendi !",
						Toast.LENGTH_SHORT);

				intent.putExtras(newActivityInfo);

				// intent.putExtra("secilenIcecek", secilenIcecek);
				// intent.putExtra("secilenIcecekFiyati", secilenIcecekFiyati);
				// intent.putExtra("adetIcecekler", adetIcecekler);
				// intent.putExtra("butonDegeri", butonDegeri);
				startActivity(intent);
				toast.show();

			}
		});
		butonSalata.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				butonDegeri = 3;
				sepetBos = false;

				if (edittextSalata.getText().toString() == null
						|| edittextSalata.getText().toString().trim()
								.equals("")
						|| edittextSalata.getText().toString().trim().isEmpty()) {
					secilenSalataAdeti = 1;
				} else if (edittextSalata.getText().toString() == "0") {
					Toast toast = Toast.makeText(MainActivity.this,
							"En az 1 degerini girmeniz gerekmektedir. !",
							Toast.LENGTH_SHORT);
					toast.show();
				} else {
					String no = edittextSalata.getText().toString();
					secilenSalataAdeti = Integer.parseInt(no);
				}
				int salataIndex = spinnerSalatalar.getSelectedItemPosition();
				secilenSalata = (String) salatalarListesi.get(salataIndex);
				secilenSalataFiyati = salataFiyatlar�Listesi.get(salataIndex);

				Intent intent = new Intent(MainActivity.this, SiparisVer.class);
				Bundle newActivityInfo = new Bundle();
				secilenlerAdetler.add(secilenSalataAdeti);
				secilenlerListesi.add(secilenSalata);
				secilenlerFiyatlar.add(secilenSalataFiyati);

//				newActivityInfo.putStringArrayList("secilenSalata",
//						secilenlerListesi);
//				newActivityInfo.putIntegerArrayList("secilenSalataFiyati",
//						secilenlerFiyatlar);
//				newActivityInfo.putIntegerArrayList("secilenSalataAdeti",
//						secilenlerAdetler);
				
				newActivityInfo.putString("secilenSalata",secilenSalata);
				newActivityInfo.putInt("secilenSalataFiyati",secilenSalataFiyati);
				newActivityInfo.putInt("secilenSalataAdeti",secilenSalataAdeti);
				newActivityInfo.putInt("butonDegeri", butonDegeri);
				intent.putExtras(newActivityInfo);

				Toast toast = Toast
						.makeText(MainActivity.this,
								"Se�ilen Yemekler Sepete eklendi !",
								Toast.LENGTH_SHORT);

				// intent.putExtra("secilenSalata", secilenSalata);
				// intent.putExtra("secilenIcecekFiyati", secilenIcecekFiyati);
				// intent.putExtra("adetSalatalar", adetSalatalar);
				// intent.putExtra("butonDegeri", butonDegeri);
				toast.show();

				startActivity(intent);

			}
		});
		butonTatli.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				butonDegeri = 4;
				sepetBos = false;

				if (edittextTatli.getText().toString() == null || edittextTatli.getText().toString().trim().equals("") || edittextTatli.getText().toString().trim().isEmpty()) {
					secilenTatliAdeti = 1;
				} else if (edittextTatli.getText().toString() == "0") {
					Toast toast = Toast.makeText(MainActivity.this,
							"En az 1 degerini girmeniz gerekmektedir. !",
							Toast.LENGTH_SHORT);
					toast.show();
				} else {
					secilenTatliAdeti = Integer.parseInt(edittextTatli
							.getText().toString());
				}
				int tatliIndex = spinnerYemekler.getSelectedItemPosition();
				secilenTatl� = (String) tatlilarListesi.get(tatliIndex);
				secilenTatliFiyati = tatliFiyatlar�Listesi.get(tatliIndex);

				Intent intent = new Intent(MainActivity.this, SiparisVer.class);
				Bundle newActivityInfo = new Bundle();
				secilenlerAdetler.add(secilenTatliAdeti);
				secilenlerListesi.add(secilenTatl�);
				secilenlerFiyatlar.add(secilenTatliFiyati);
				int deger = secilenlerListesi.size();

//				newActivityInfo.putStringArrayList("secilenTatli",secilenlerListesi);
//				newActivityInfo.putIntegerArrayList("secilenTatliFiyati",secilenlerFiyatlar);
//				newActivityInfo.putIntegerArrayList("secilenTatliAdeti",secilenlerAdetler);
				newActivityInfo.putInt("size",deger);
				Toast toast = Toast
						.makeText(MainActivity.this,
								"Se�ilen Tatlilar Sepete eklendi !",
								Toast.LENGTH_SHORT);
				
				newActivityInfo.putString("secilenTatli",secilenTatl�);
				newActivityInfo.putInt("secilenTatliFiyati",secilenTatliFiyati);
				newActivityInfo.putInt("secilenTatliAdeti",secilenTatliAdeti);
				newActivityInfo.putInt("butonDegeri", butonDegeri);

				intent.putExtras(newActivityInfo);

				// intent.putExtra("secilenTatli", secilenTatl�);
				// intent.putExtra("secilenTatliFiyati", secilenTatliFiyati);
				// intent.putExtra("adetTatlilar", adetTatlilar);
				// intent.putExtra("butonDegeri", butonDegeri);
				toast.show();
				startActivity(intent);

			}
		});

		
		
	}
	
	public void yemekSepeti(View v)
	{
		Intent intent = new Intent(MainActivity.this, SiparisVer.class);
		startActivity(intent);
		
	}
	

}
