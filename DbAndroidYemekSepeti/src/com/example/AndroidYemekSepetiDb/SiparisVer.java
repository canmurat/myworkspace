package com.example.AndroidYemekSepetiDb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.VeriTabani.VeriTabanýSiparisler;

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
	static int secilenFiyatý;
	static int secilenAdeti;
	static int butonDegeri;

	// secilen degerleri tutmak icin birer birer ArrayList olusturuldu
	static ArrayList<Integer> secilenlerAdetler = new ArrayList<Integer>();
	static ArrayList<String> secilenlerListesi = new ArrayList<String>();
	static ArrayList<Integer> secilenlerFiyatlar = new ArrayList<Integer>();

	static ArrayList<String> secilenlerListesiYemekler = new ArrayList<String>();
	static ArrayList<String> secilenlerListesiIcecekler = new ArrayList<String>();
	static ArrayList<String> secilenlerListesiSalatalar = new ArrayList<String>();
	static ArrayList<String> secilenlerListesiTatlýlar = new ArrayList<String>();

	static ArrayList<Integer> secilenlerFiyatlarYemekler = new ArrayList<Integer>();
	static ArrayList<Integer> secilenlerFiyatlarIcecekler = new ArrayList<Integer>();
	static ArrayList<Integer> secilenlerFiyatlarSalatalar = new ArrayList<Integer>();
	static ArrayList<Integer> secilenlerFiyatlarTatlilar = new ArrayList<Integer>();

	static ArrayList<Integer> secilenlerAdetlerYemekler = new ArrayList<Integer>();
	static ArrayList<Integer> secilenlerAdetlerIcecekler = new ArrayList<Integer>();
	static ArrayList<Integer> secilenlerAdetlerSalatalar = new ArrayList<Integer>();
	static ArrayList<Integer> secilenlerAdetlerTatlilar = new ArrayList<Integer>();

	HashMap<String, Integer> hesap = new HashMap<String, Integer>();
	HashMap<Integer, Integer> degerler;
	HashMap<Integer, Integer> sayaclar = new HashMap<Integer, Integer>();

	static int deger;
	int sonuc = 0;
	int sayac;

	static int SiparisId = 0;

	int silinendeger;
	int silinecekdeger;

	VeriTabanýSiparisler veritabaný;

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
			System.out.println("Loanýnfo bos = 1");
		}

		if (butonDegeri == 1 && loanInfo != null) {

			secilen = loanInfo.getString("secilenYemek");
			secilenAdeti = loanInfo.getInt("secilenYemekAdeti");
			secilenFiyatý = loanInfo.getInt("secilenYemekFiyati");

			secilenlerListesi.add(secilen);
			secilenlerAdetler.add(secilenAdeti);
			secilenlerFiyatlar.add(secilenFiyatý);

			secilenlerListesiYemekler.add(secilen);
			secilenlerAdetlerYemekler.add(secilenAdeti);
			secilenlerFiyatlarYemekler.add(secilenFiyatý);

		} else if (butonDegeri == 2 && loanInfo != null) {

			secilen = loanInfo.getString("secilenIcecek");
			secilenAdeti = loanInfo.getInt("secilenIcecekAdeti");
			secilenFiyatý = loanInfo.getInt("secilenIcecekFiyati");

			secilenlerListesi.add(secilen);
			secilenlerAdetler.add(secilenAdeti);
			secilenlerFiyatlar.add(secilenFiyatý);

			secilenlerListesiIcecekler.add(secilen);
			secilenlerAdetlerIcecekler.add(secilenAdeti);
			secilenlerFiyatlarIcecekler.add(secilenFiyatý);

		} else if (butonDegeri == 3 && loanInfo != null) {

			secilen = loanInfo.getString("secilenSalata");
			secilenAdeti = loanInfo.getInt("secilenSalataAdeti");
			secilenFiyatý = loanInfo.getInt("secilenSalataFiyati");

			secilenlerListesi.add(secilen);
			secilenlerAdetler.add(secilenAdeti);
			secilenlerFiyatlar.add(secilenFiyatý);

			secilenlerListesiSalatalar.add(secilen);
			secilenlerAdetlerSalatalar.add(secilenAdeti);
			secilenlerFiyatlarSalatalar.add(secilenFiyatý);

		} else if (butonDegeri == 4 && loanInfo != null) {

			secilen = loanInfo.getString("secilenTatli");
			secilenAdeti = loanInfo.getInt("secilenTatliAdeti");
			secilenFiyatý = loanInfo.getInt("secilenTatliFiyati");

			secilenlerListesi.add(secilen);
			secilenlerAdetler.add(secilenAdeti);
			secilenlerFiyatlar.add(secilenFiyatý);

			secilenlerListesiTatlýlar.add(secilen);
			secilenlerAdetlerTatlilar.add(secilenAdeti);
			secilenlerFiyatlarTatlilar.add(secilenFiyatý);
		} else {
			Log.d("LoanInfoBos", "Probleemm");
		}

		lv = (ListView) findViewById(R.id.listview);

		// map = new HashMap<String, Integer>();
		// map.put(secilen, secilenFiyatý);

		// create the grid item mapping
		String[] from = new String[] { "rowid", "adet", "alýnan", "fiyat",
				"kaldýr" };
		int[] to = new int[] { R.id.rowid, R.id.adet, R.id.alinan, R.id.fiyat,
				R.id.delete };
		degerler = new HashMap<Integer, Integer>();
		fillMaps = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < secilenlerListesi.size(); i++) {

			sayaclar.put(i, 0);

			HashMap<String, String> map1 = new HashMap<String, String>();
			map1.put("rowid", "" + i);
			map1.put("adet", String.valueOf(secilenlerAdetler.get(i)));
			map1.put("alýnan", secilenlerListesi.get(i));
			map1.put("fiyat",String.valueOf(secilenlerFiyatlar.get(i)
							* secilenlerAdetler.get(i)));
			map1.put("kaldýr", "X");
			// degerler.put(i, secilenlerAdetlerYemekler.get(i));
			fillMaps.add(map1);
			hesap.put(secilenlerListesi.get(i), secilenlerAdetler.get(i)
					* secilenlerFiyatlar.get(i));
			sonuc += hesap.get(secilenlerListesi.get(i));

		}

		text2.setText("" + sonuc);

		adapter = new SimpleAdapter(this, fillMaps, R.layout.grid_item, from,
				to);
		lv.setAdapter(adapter);

	}

	public long siparisVer(View v) {

		veritabaný = new VeriTabanýSiparisler(this);
		SQLiteDatabase db = veritabaný.getWritableDatabase();
		ContentValues satir = new ContentValues();

		String yemek = "";
		String icecek = "";
		String salata = "";
		String tatli = "";
		int yemekAdeti = 0;
		int icecekAdeti =0;
		int salataAdeti = 0;
		int tatliAdeti = 0;
		int yemekFiyatý = 0;
		int icecekFiyatý = 0;
		int salataFiyatý = 0;
		int tatliFiyatý = 0;
		
		satir.put("KullaniciId", KullaniciGiris.KullaniciId);
		for (int i = 0; i < secilenlerListesiYemekler.size(); i++){
			yemek = secilenlerListesiYemekler.get(i);
			yemekAdeti = secilenlerAdetlerYemekler.get(i);
			yemekFiyatý = secilenlerFiyatlarYemekler.get(i);
			
			 
			satir.put("Yemekler", yemek+", "+ yemekFiyatý +" TL, " +"Adet: "+ yemekAdeti);
		}
			
		for (int i = 0; i < secilenlerListesiIcecekler.size(); i++)
		{
			icecek = secilenlerListesiIcecekler.get(i);
			icecekAdeti = secilenlerAdetlerIcecekler.get(i);
			icecekFiyatý = secilenlerFiyatlarIcecekler.get(i);
			
			satir.put("Icecekler", icecek+", Fiyat: "+icecekFiyatý +" TL, " +"Adet: "+ icecekAdeti);
		}
			
		for (int i = 0; i < secilenlerListesiSalatalar.size(); i++)
		{
			salata = secilenlerListesiSalatalar.get(i);
			salataAdeti = secilenlerAdetlerSalatalar.get(i);
			salataFiyatý = secilenlerFiyatlarSalatalar.get(i);
			
			satir.put("Salatalar", salata+ ", "+salataFiyatý +" TL, " +"Adet: " + salataAdeti );
		}
			
		for (int i = 0; i < secilenlerListesiTatlýlar.size(); i++)
		{
			tatli = secilenlerListesiTatlýlar.get(i);
			tatliAdeti = secilenlerAdetlerTatlilar.get(i);
			tatliFiyatý = secilenlerFiyatlarTatlilar.get(i);
			
			satir.put("Tatlilar", tatli + ", "+ tatliFiyatý +" TL, "+"Adet: "+ tatliAdeti );
		}
		
		satir.put("Toplam",sonuc +" TL");
		
		String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
		
		satir.put("Tarih",mydate);

		long eklenenKelimeId = db.insert(VeriTabanýSiparisler.TABLE_NAME, null,
				satir);
		Toast.makeText(this, "Siparis Basari ile eklendi. !",
				Toast.LENGTH_SHORT).show();
		return eklenenKelimeId;	

	}

	// Yemeklere donmesi icin intent baslatýlýyor
	public void geriDonYemekler(View v) {

		Intent intent = new Intent(SiparisVer.this, ActivityYemek.class);
		startActivity(intent);

	}

	// Iceceklere donmesi icin intent baslatýlýyor
	public void geriDonIcecekler(View v) {

		Intent intent = new Intent(SiparisVer.this, ActivityIcecek.class);
		startActivity(intent);

	}

	// Salatalara donmesi icin intent baslatýlýyor
	public void geriDonSalatalar(View v) {

		Intent intent = new Intent(SiparisVer.this, ActivitySalata.class);
		startActivity(intent);

	}

	// Tatlýlara donmesi icin intent baslatýlýyor
	public void geriDonTatlilar(View v) {

		Intent intent = new Intent(SiparisVer.this, ActivityTatli.class);
		startActivity(intent);

	}

	// Satýr silmek istedigi zaman , yani yemek silecegi zaman calýscak yordam
	// Yemekler id degerlerine gore tutuluyor. Bu yüzden silmek istenilen
	// yemegin id degerindeen
	// silme islemi gerceklesiyor.
	public void DeleteRow(View v) {

		/*
		 * Lineer Layout'un 0'ncý index'indeki deger , yemekler icin id degerini
		 * tutan textview. Bu degeri alarak silinecek yemek listelerden
		 * temizleniyor.
		 */

		LinearLayout llMain = (LinearLayout) v.getParent();
		TextView row = (TextView) llMain.getChildAt(0);
		String row_no = row.getText().toString();

		/*
		 * Yemeklerin dogru sýrada silinebilmesi icin . bir hashmap olusturldu .
		 * ismi sayaclar. bu hashmap'e gore , listedeki boyut'a gore 1-den
		 * boyut'a kadar numaralar olusturuluyor ve, silme islemi yapýldýgýnda
		 * bu numara nin value degerine 1 konuluyor.
		 */

		int row_no_int = Integer.parseInt(row_no);// silinen'in sutun numarasý
													// alýnýr..

		// silinecek deger degiskene atýldý.
		silinecekdeger = row_no_int;

		System.out.println("row_no_int onceki = " + row_no_int);

		// Listenin , daha sonraki silme islemlerinde hata dondurmemesi icin
		// burasý olsuturldu.
		// Burda amac, Listenin asagýya ne kdar kaydýgýný bulmaktýr. (Ne kdar
		// deger silindi.)
		// bu deger sayac'da tutuluyor.
		for (int i = 0; i < sayaclar.size(); i++) {
			if (row_no_int >= i) {
				if (sayaclar.get(i) == 1) {
					sayac += sayaclar.get(i);
				}
			}
		}
		row_no_int -= sayac;

		// Kontrol yapýlarý. 'sayaclar' hashmap'nýn isleyip islemedigini anlamak
		// icin.
		System.out.println("sayac.get(0)" + sayaclar.get(0));
		System.out.println("sayac.get(1)" + sayaclar.get(1));
		System.out.println("sayac.get(2)" + sayaclar.get(2));
		System.out.println("sayac.get(3)" + sayaclar.get(3));
		System.out.println("sayac.get(4)" + sayaclar.get(4));
		System.out.println("secilenlerListesi'nin sizesi = "
				+ secilenlerListesi.size());
		System.out.println("row_no_int sonraki = " + row_no_int);
		System.out.println("sayaclar'in sizesi = " + sayaclar.size());
		System.out.println("sayac degeri = " + sayac);

		sonuc -= hesap.get(secilenlerListesi.get(row_no_int));

		
		if (!secilenlerListesiYemekler.isEmpty() && secilenlerListesiYemekler.get(row_no_int) == secilenlerListesi.get(row_no_int)) {
			secilenlerListesiYemekler.remove(row_no_int);
		}

		if (!secilenlerListesiIcecekler.isEmpty()
				&& secilenlerListesiIcecekler.get(row_no_int) == secilenlerListesi
						.get(row_no_int)) {
			secilenlerListesiIcecekler.remove(row_no_int);
		}
		if (!secilenlerListesiSalatalar.isEmpty()
				&& secilenlerListesiSalatalar.get(row_no_int) == secilenlerListesi
						.get(row_no_int)) {
			secilenlerListesiSalatalar.remove(row_no_int);
		}
		if (!secilenlerListesiTatlýlar.isEmpty()
				&& secilenlerListesiTatlýlar.get(row_no_int) == secilenlerListesi
						.get(row_no_int)) {
			secilenlerListesiTatlýlar.remove(row_no_int);
		}

		secilenlerListesi.remove(row_no_int);
		secilenlerFiyatlar.remove(row_no_int);
		secilenlerAdetler.remove(row_no_int);

		fillMaps.remove(row_no_int);
		sayaclar.put(silinecekdeger, 1);
		sayac = 0;
		adapter.notifyDataSetChanged();

		text2.setText("" + sonuc);

	}

	// Siparisleri goster mesi icin yordam .O Activity calýstýrýlýyor.
}
