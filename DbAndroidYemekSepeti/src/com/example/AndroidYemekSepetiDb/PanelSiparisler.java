package com.example.AndroidYemekSepetiDb;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.VeriTabani.VeriTabanýKisiler;
import com.example.VeriTabani.VeriTabanýSiparisler;

public class PanelSiparisler extends Activity {

	private VeriTabanýSiparisler veritabaný;
	private ArrayAdapter<String> listAdapter;
	private Cursor kelimeListesiCursor;
	private Cursor kullaniciCursor;
	private ListView SiparislerListView;

	String SiparisId;
	int KullaniciId;

	public String Yemek1;
	public String Icecek1;
	public String Salata1;
	public String Tatli1;
	public String Yemek;
	public String Icecek;
	public String Salata;
	public String Tatli;
	public String Toplam = "0";
	public String Tarih;
	public String kullaniciAdi;
	public ArrayList<String> bigList;
	public String[] values;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.siparisler);
		bigList = new ArrayList<String>();
		veritabaný = new VeriTabanýSiparisler(this);

		// Siparisleri Goruntulemek icin kullanýldý.
		kelimeListesiCursor = butunUrunleriSorgula();
		// kelimeListesiCursor en basta -1 dondurur. moveToNext() ile satýr'ýn
		// basýna gelmesini saglýyoruz.

		int size = kelimeListesiCursor.getCount();
		System.out.println(size + " size");
		for (int i = 0; i < size; i++) {

			kelimeListesiCursor.moveToNext();

			// Siparis Id degeri cursor'dan cekiliyor.
			int SiparisIdIndex = kelimeListesiCursor
					.getColumnIndex(VeriTabanýSiparisler.SIPARIS_ID);

			SiparisId = "Siparis Id: "
					+ kelimeListesiCursor.getInt(SiparisIdIndex);
			bigList.add(SiparisId);

			// KullaniciId degerini cursor'dan cekiyoruz.
			int KullaniciIdIndex = kelimeListesiCursor
					.getColumnIndex(VeriTabanýSiparisler.KULLANICI_ID);
			KullaniciId = kelimeListesiCursor.getInt(KullaniciIdIndex);
			bigList.add("KullaniciId: " + String.valueOf(KullaniciId));
			kullaniciCursor = KullaniciAdiBelirle();
			// kullaniciCursor en basta -1 dondurur. moveToNext() ile satýr'ýn
			// basýna gelmesini saglýyoruz.
			kullaniciCursor.moveToNext();

			int kullaniciAdiIndex = kullaniciCursor
					.getColumnIndex(VeriTabanýKisiler.KISI_ADI);
			// kullanici Adi , cursor nesnesinden cekiliyor.
			kullaniciAdi = kullaniciCursor.getString(kullaniciAdiIndex);

			// Liste Tanýmlandý.
			SiparislerListView = (ListView) findViewById(R.id.listview1);

			// yemek degeri cursor'dan cekiliyor.
			int YemekIndex = kelimeListesiCursor
					.getColumnIndex(VeriTabanýSiparisler.YEMEKLER);
			
			Yemek1 = kelimeListesiCursor.getString(YemekIndex);
			Yemek1 = (Yemek1 == null) ? "Yok" : Yemek1;
			Yemek = "Yemekler: " + Yemek1;
			
			bigList.add(Yemek);
			// icecek degeri cursor'dan cekiliyor.
			int IcecekIndex = kelimeListesiCursor
					.getColumnIndex(VeriTabanýSiparisler.ICECEKLER);
			
			Icecek1 = kelimeListesiCursor.getString(IcecekIndex);
			Icecek1 = (Icecek1 == null) ? "Yok" : Icecek1;
			Icecek = "Icecekler: " + Icecek1;
			
			bigList.add(Icecek);
			// salata degeri cursor'dan cekiliyor.
			int SalataIndex = kelimeListesiCursor
					.getColumnIndex(VeriTabanýSiparisler.SALATALAR);
			Salata1 = kelimeListesiCursor.getString(SalataIndex);
			Salata1 = (Salata1 == null) ? "Yok" : Salata1;
			Salata = "Salatalar: " + Salata1;
			
			bigList.add(Salata);
			// tatli degeri cursor'dan cekiliyor.
			int TatliIndex = kelimeListesiCursor
					.getColumnIndex(VeriTabanýSiparisler.TATLILAR);
			Tatli1 = kelimeListesiCursor.getString(TatliIndex);
			Tatli1 = (Tatli1 == null) ? "Yok" : Tatli1;
			Tatli = "Tatlilar: " + Tatli1;
			
			bigList.add(Tatli);
			// toplam degeri cursor'dan cekiliyor.
			int ToplamIndex = kelimeListesiCursor
					.getColumnIndex(VeriTabanýSiparisler.TOPLAM);
			Toplam = "Toplam: " + kelimeListesiCursor.getInt(ToplamIndex);
			bigList.add(Toplam);
			// tarih degeri cursor'dan cekiliyor.
			int TarihIndex = kelimeListesiCursor
					.getColumnIndex(VeriTabanýSiparisler.TARIH);
			Tarih = "Tarih: " + kelimeListesiCursor.getString(TarihIndex);
			bigList.add(Tarih);
			// Tum degerler Adapter'a konulmak uzere values String listesine
			// atýlýyor.

			bigList.add("--------");

			// adapter olusturuluyor.

		}

		listAdapter = new ArrayAdapter<String>(this, R.layout.listtextviews2,
				bigList);

		SiparislerListView.setAdapter(listAdapter);

	}

	// Butun siparisleri sorgulayýp onlarýn ListView'e düsmesini saglýyoruz.
	private Cursor butunUrunleriSorgula() {

		SQLiteDatabase db = veritabaný.getReadableDatabase();
		return db.rawQuery("select * from siparisler", new String[] {});
		// SiparisId as
		// _id,KullaniciId,Yemekler,Icecekler,Salatalar,Tatlilar,Toplam
	}

	// Kullanici Adi degeri , siparisler tablosundaki Id degerine gore
	// belirleniyor.
	public Cursor KullaniciAdiBelirle() {
		SQLiteDatabase db = veritabaný.getReadableDatabase();
		return db.rawQuery("select * from kullanicilar where KullaniciId=?",
				new String[] { String.valueOf(KullaniciId) });
	}

}
