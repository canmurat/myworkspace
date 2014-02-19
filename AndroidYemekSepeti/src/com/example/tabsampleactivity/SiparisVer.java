package com.example.tabsampleactivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SiparisVer extends Activity {

	TextView text1;
	TextView text2;
	HashMap<String, Integer> map;
	SimpleAdapter adapter;
	ListView lv;
	String[] from;
	int[] to;
	List<HashMap<String, String>> fillMaps;
	ArrayList<String> itemler = new ArrayList<String>();

	static String secilen;
	static int secilenFiyat�;
	static int secilenAdeti;
	static int butonDegeri;

	// secilen degerleri tutmak icin birer birer ArrayList olusturuldu
	static ArrayList<Integer> secilenlerAdetler = new ArrayList<Integer>();
	static ArrayList<String> secilenlerListesi = new ArrayList<String>();
	static ArrayList<Integer> secilenlerFiyatlar = new ArrayList<Integer>();
	HashMap<String, Integer> hesap = new HashMap<String, Integer>();
	HashMap<Integer, Integer> degerler;
	HashMap<Integer, Integer> sayaclar = new HashMap<Integer, Integer>();

	static int deger;
	int sonuc = 0;
	int sayac;
	SiparisleriGoster sp = new SiparisleriGoster();

	static int SiparisId = 0;

	int silinendeger;
	int silinecekdeger;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_item);
		Intent intent = getIntent();
		Bundle loanInfo = intent.getExtras();

		text1 = (TextView) findViewById(R.id.siparisleriGoster);
		text2 = (TextView) findViewById(R.id.textalinanlar);

		if (loanInfo != null) {
			butonDegeri = loanInfo.getInt("butonDegeri");
			System.out.println("loan degeri null degil .");
		} else {
			System.out.println("Loan�nfo bos = 1");
		}

		if (butonDegeri == 1 && loanInfo != null) {

			secilen = loanInfo.getString("secilenYemek");
			secilenAdeti = loanInfo.getInt("secilenYemekAdeti");
			secilenFiyat� = loanInfo.getInt("secilenYemekFiyati");

			secilenlerListesi.add(secilen);
			secilenlerAdetler.add(secilenAdeti);
			secilenlerFiyatlar.add(secilenFiyat�);

		} else if (butonDegeri == 2 && loanInfo != null) {

			secilen = loanInfo.getString("secilenIcecek");
			secilenAdeti = loanInfo.getInt("secilenIcecekAdeti");
			secilenFiyat� = loanInfo.getInt("secilenIcecekFiyati");

			secilenlerListesi.add(secilen);
			secilenlerAdetler.add(secilenAdeti);
			secilenlerFiyatlar.add(secilenFiyat�);

		} else if (butonDegeri == 3 && loanInfo != null) {

			secilen = loanInfo.getString("secilenSalata");
			secilenAdeti = loanInfo.getInt("secilenSalataAdeti");
			secilenFiyat� = loanInfo.getInt("secilenSalataFiyati");

			secilenlerListesi.add(secilen);
			secilenlerAdetler.add(secilenAdeti);
			secilenlerFiyatlar.add(secilenFiyat�);

		} else if (butonDegeri == 4 && loanInfo != null) {

			secilen = loanInfo.getString("secilenTatli");
			secilenAdeti = loanInfo.getInt("secilenTatliAdeti");
			secilenFiyat� = loanInfo.getInt("secilenTatliFiyati");

			secilenlerListesi.add(secilen);
			secilenlerAdetler.add(secilenAdeti);
			secilenlerFiyatlar.add(secilenFiyat�);
		} else {
			Log.d("LoanInfoBos", "Probleemm");
		}

		lv = (ListView) findViewById(R.id.listview);

		// map = new HashMap<String, Integer>();
		// map.put(secilen, secilenFiyat�);

		// create the grid item mapping
		String[] from = new String[] { "rowid", "adet", "al�nan", "fiyat",
				"kald�r" };
		int[] to = new int[] { R.id.rowid, R.id.adet, R.id.alinan, R.id.fiyat,
				R.id.delete };
		degerler = new HashMap<Integer, Integer>();
		fillMaps = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < secilenlerListesi.size(); i++) {

			sayaclar.put(i, 0);

			HashMap<String, String> map1 = new HashMap<String, String>();
			map1.put("rowid", "" + i);
			map1.put("adet", String.valueOf(secilenlerAdetler.get(i)));
			map1.put("al�nan", secilenlerListesi.get(i));
			map1.put("fiyat", String.valueOf(secilenlerFiyatlar.get(i) * secilenlerAdetler.get(i)));
			map1.put("kald�r", "X");
			degerler.put(i, secilenlerAdetler.get(i));
			fillMaps.add(map1);
			hesap.put(secilenlerListesi.get(i), secilenlerAdetler.get(i)
					* secilenlerFiyatlar.get(i));
			sonuc += hesap.get(secilenlerListesi.get(i));

		}

		text2.setText("" + sonuc);

		adapter = new SimpleAdapter(this, fillMaps, R.layout.grid_item, from,
				to);
		lv.setAdapter(adapter);

		AlertDialog.Builder builder = new AlertDialog.Builder(this);

	}

	public void siparisVer(View v) {

		// Siparis ver butonuna bas�ldg� zaman text'e gerekli yaz�lma islemleri yap�l�yor.
		
		BufferedWriter writer = null;
		try {
			// writer MODE_APPEND beirlendi cunki text'deki degerler 0 lans�n istenmiyor.
			writer = new BufferedWriter(new OutputStreamWriter(openFileOutput(
					"siparisler.txt", Context.MODE_APPEND)));
			// Degerler dongu ile yaz�l�yor.
			for (int i = 0; i < secilenlerListesi.size(); i++) {

				String al�nan = secilenlerListesi.get(i);
				int adeti = secilenlerAdetler.get(i);
				int fiyati = secilenlerFiyatlar.get(i)*adeti;

				writer.append(" Siparis_Id:" + " " + (sp.siparisIdAl()+1));
				writer.newLine();
				writer.append(" al�nan: " + al�nan + " adet: " + adeti
						+ " fiyat�: " + fiyati + "TL\n ");
				// Dongu icerisindeki degerler flush edildi .(Gonderildi.)
				writer.flush();
			}
			//Is�m�z bittigi zaman writer'� kapat.
			writer.close();
			// Yazma isleminde sorun olmazsa siparisler yaz�ld� mesaj�
			Toast.makeText(this, "Siparisler Text Dosyasina yazildi.",
					Toast.LENGTH_SHORT).show();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Yemeklere donmesi icin intent baslat�l�yor
	public void geriDonYemekler(View v) {

		Intent intent = new Intent(SiparisVer.this, ActivityYemek.class);
		startActivity(intent);

	}
	// Iceceklere donmesi icin intent baslat�l�yor
	public void geriDonIcecekler(View v) {

		Intent intent = new Intent(SiparisVer.this, ActivityIcecek.class);
		startActivity(intent);

	}
	// Salatalara donmesi icin intent baslat�l�yor
	public void geriDonSalatalar(View v) {

		Intent intent = new Intent(SiparisVer.this, ActivitySalata.class);
		startActivity(intent);

	}
	// Tatl�lara donmesi icin intent baslat�l�yor
	public void geriDonTatlilar(View v) {

		Intent intent = new Intent(SiparisVer.this, ActivityTatli.class);
		startActivity(intent);

	}

	// Sat�r silmek istedigi zaman , yani yemek silecegi zaman cal�scak yordam
	// Yemekler id degerlerine gore tutuluyor. Bu y�zden silmek istenilen yemegin id degerindeen
	// silme islemi gerceklesiyor.
	public void DeleteRow(View v) {

		/*Lineer Layout'un 0'nc� index'indeki deger , yemekler icin id degerini tutan textview.
		 * Bu degeri alarak silinecek yemek listelerden temizleniyor.*/
		
		LinearLayout llMain = (LinearLayout) v.getParent();
		TextView row = (TextView) llMain.getChildAt(0);
		String row_no = row.getText().toString();

		/* Yemeklerin dogru s�rada silinebilmesi icin . bir hashmap olusturldu . ismi sayaclar.
		 * bu hashmap'e gore , listedeki boyut'a gore 1-den boyut'a kadar numaralar olusturuluyor ve,
		 * silme islemi yap�ld�g�nda bu numara nin value degerine 1 konuluyor. */
		
		int row_no_int = Integer.parseInt(row_no);// silinen'in sutun numaras� al�n�r..
		
		// silinecek deger degiskene at�ld�.
		silinecekdeger = row_no_int;

		System.out.println("row_no_int onceki = " + row_no_int);
		
		// Listenin , daha sonraki silme islemlerinde hata dondurmemesi icin buras� olsuturldu.
		// Burda amac, Listenin asag�ya ne kdar kayd�g�n� bulmakt�r. (Ne kdar deger silindi.)
		// bu deger sayac'da tutuluyor.
		for (int i = 0; i < sayaclar.size(); i++) {
			if (row_no_int >= i) {
				if (sayaclar.get(i) == 1) {
					sayac += sayaclar.get(i);
				}
			}
		}
		row_no_int -= sayac;
		
		// Kontrol yap�lar�. 'sayaclar' hashmap'n�n isleyip islemedigini anlamak icin.
		System.out.println("sayac.get(0)" + sayaclar.get(0));
		System.out.println("sayac.get(1)" + sayaclar.get(1));
		System.out.println("sayac.get(2)" + sayaclar.get(2));
		System.out.println("sayac.get(3)" + sayaclar.get(3));
		System.out.println("sayac.get(4)" + sayaclar.get(4));
		System.out.println("secilenlerListesi'nin sizesi = "
				+ secilenlerListesi.size());
		System.out.println("row_no_int sonraki = " + row_no_int);
		System.out.println("sayaclar'in sizesi = " + sayaclar.size());
		System.out.println("sayac degeri = "+ sayac );
		sonuc -= hesap.get(secilenlerListesi.get(row_no_int));

		secilenlerListesi.remove(row_no_int);
		secilenlerFiyatlar.remove(row_no_int);
		secilenlerAdetler.remove(row_no_int);
		fillMaps.remove(row_no_int);
		sayaclar.put(silinecekdeger, 1);
		sayac = 0;
		adapter.notifyDataSetChanged();

		text2.setText("" + sonuc);

	}

	// Siparisleri goster mesi icin yordam .O Activity cal�st�r�l�yor.
	public void siparisleriGoster(View v) {
		Intent intent = new Intent(SiparisVer.this, SiparisleriGoster.class);
		startActivity(intent);
	}
	
}
