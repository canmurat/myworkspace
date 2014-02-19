package com.example.AndroidYemekSepetiDb;


import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.VeriTabani.VeriTaban�Yiyecekler;

public class PanelYemekler extends Activity {

	private VeriTaban�Yiyecekler veritaban�;
	private SimpleCursorAdapterClass adapter;
	private Cursor kelimeListesiCursor;
	// Kelimeleri tutacak olan Cursor nesnesi.
	
	// Database icindeki verileri ListView'e map ederken from ve to 'yu kullan�yoruz .
	private String[] from = new String[] {

	// Adapter'a eklenmek uzere from ve to Listeleri tan�mlan�yor.
	// From'da nelerin eklenecegi bilgisi var.
	VeriTaban�Yiyecekler.URUN_ADI, 
	VeriTaban�Yiyecekler.URUN_FIYATI };

	// to icerisinde eklenecek itemlerin hangi TextView'e eklenecegi bilgisi var .
	private int[] to = new int[] { 
			R.id.AdListItemTextView,
			R.id.FiyatListItemTextView };

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.yemekler);
		
		// Ekrana ac�l�sta goruntunun gelmesi icin onCreate metoduna yaz�ld�.
		ekranKontrolleriniOlustur();
	}

	private void ekranKontrolleriniOlustur() {

		// Veritaban� nesnesi , ilgili Class'dan olusturuluyor.
		veritaban� = new VeriTaban�Yiyecekler(this);
		// butun urunler sorgulan�p sonuclar� tutuluyor.Bu nesne adapter'a ekleniyor.
		kelimeListesiCursor = butunUrunleriSorgula();
		adapter = new SimpleCursorAdapterClass(this, R.layout.listtextviews,
				kelimeListesiCursor, from, to, 0);

		final EditText AdEditText = (EditText) findViewById(R.id.YemeklerAdEditText);
		final EditText FiyatEditText = (EditText) findViewById(R.id.YemeklerFiyatEditText);

		// Urun ekleme isleminde kaydet butonuna bas�lacag� zaman gerceklesek olaylar. 
		
		Button kaydetButton = (Button) findViewById(R.id.kaydetBtnYemekler);
		kaydetButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Urun ekle butonuna bas�ld�g� zaman UrunEkle methodu gerceklestiriliyor.
				String ad = AdEditText.getText().toString();
				String fiyat = FiyatEditText.getText().toString();
				
				UrunEkle(ad, fiyat);
				
			}
		});

		Button guncelleButton = (Button) findViewById(R.id.gnclleBtnYemekler);
		guncelleButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String ad = AdEditText.getText().toString();
				String fiyat = FiyatEditText.getText().toString();
				// Urun Guncelle butonuna bas�ld�g�nda ad,fiyat bilgileriyle UrunGuncelle methodu gerceklestiriliyor.
				UrunGuncelle(ad, fiyat);
			}
		});

		Button silButton = (Button) findViewById(R.id.silBtnYemekler);
		silButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String ad = AdEditText.getText().toString();
				// Urun sil denildigi zaman ismi verilen �r�n siliniyor. Her isim'den birtane kay�t ediliyordu
				UrunSil(ad);
			}
		});
		//ListView Olusturuldu ve adapter set edildi.
		ListView YemeklerListView = (ListView) findViewById(R.id.SiparislerListView);
		YemeklerListView.setAdapter(adapter);
		// YemeklerListesinde item secilmesi an�nda gerceklesecek olaylar burda yer almakta.
		YemeklerListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView arg0, View view, int position,
					long id) {
				//Adapter, secilmis olan pozisyona getiriliyor.
				adapter.setSelectedPosition(position);
				// Listede herhangi bir yemek secildigi zaman o yemekler EditText'lere aktar�l�yor.
				editTextGuncelle(kelimeListesiCursor, position, AdEditText,
						FiyatEditText);
			}
		});

	}

	// yemek listesinde ad� verilen yemegin Id degeri getiriliyor.
	private int getUrunId(String ad) {

		// Id degerini tutmak icin isime gore urun sorgulan�yor.Sonuc Cursor'da tutuluyor.
		Cursor cursor = UrunSorgula(ad);
		//Cursor bos ise -1 dondur.
		if (cursor == null)
			return -1;
		// secilmis item say�s� sadece 1 olmal�. Onun kontrolu yap�l�yor.
		int count = cursor.getCount();

		if (count != 1)
			return -1;
		
		// cursor ilk basta sat�r'�n ilk eleman�n� degil, 1 geriyi tutuyor.Ilk elemana gelmesi saglan�yor.
		cursor.moveToNext();
		// Urun ad�n�n bulundugu sat�rda, Urun_Id degerinin bulundugu sutundaki deger degiskene at�l�yor
		int idIndex = cursor.getColumnIndex(VeriTaban�Yiyecekler.URUN_ID);

		// Id degeri return edliyor.
		return cursor.getInt(idIndex);

	}

	private Cursor UrunSorgula(String ad) {

		if (ad == null)
			throw new RuntimeException(
					"Kelime ad� sorgulama i�lemi i�in bo� b�rak�lamaz");


		String[] whereArgs = new String[] { ad };

		SQLiteDatabase db = veritaban�.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from yiyecekler where yiyecekler.Ad=?",whereArgs);
//		VeriTaban�Yiyecekler.TABLE_NAME, projection,
//		where, whereArgs, null, null, null
		return cursor;

	}

	private long UrunEkle(String Ad, String Fiyat) {

		// Urun Id degeri getUrunId methodu ile al�nd�.Bunun icin id'si al�nacak yemegin ad� verildi.
		int UrunId = getUrunId(Ad);

		// getUrunId methoduyla Ism� verilen yemekte bir urun bulundu ise daha once eklenmistir deniliyor.
		// ve C�k�l�yor..
		if (UrunId != -1) {
			Toast.makeText(getApplicationContext(),
					"Bu kelime daha �nce eklenmi�tir", Toast.LENGTH_LONG)
					.show();
			return -1;
		}
		// Urun olmamas� durumunda verilen Ad ve Soyad degerlerine gore Db'ye ekleme yap�l�yor.
		// Bunun icin ContentValues s�n�f� kullan�l�yor.

		ContentValues satir = new ContentValues();
		satir.put("Ad", Ad);
		satir.put("Fiyat", Fiyat);
		
		// Daha once olusturulmus ilgili veritaban� referans�ndan bir database nesnesi olusturuluyor.
		// Ve Ekleme islemleri bu nesne uzerinden yap�l�yor.
		SQLiteDatabase db = veritaban�.getWritableDatabase();
		// Veritaban�na urun eklendigi zaman (basaril� ise eklenen satir'in Id'si donuyor.)
		long eklenenKelimeId = db.insert(VeriTaban�Yiyecekler.TABLE_NAME, null,
				satir);
		//Yeni bir urun eklendigi icin Liste guncelleniyor.
		listeGuncelle();
		// Yiyecegin eklendigi mesaji bildiriliyor.
		Toast.makeText(this, "Yiyecek Eklenmistir", Toast.LENGTH_SHORT).show();
		return eklenenKelimeId;

	}
	// Urun,verilen Ad ve Fiyat bilgilerine guncelleniyor.Icra eden yordam. 
	private void UrunGuncelle(String Ad, String Fiyat) {

		ContentValues guncelSatir = new ContentValues();
		guncelSatir.put("Ad", Ad);
		guncelSatir.put("Fiyat", Fiyat);

		int kelimeId = getUrunId(Ad);
		// Veritaban�ndan verilen isme gore bir Id degeri elde edilemiyor ise guncellenecek kelime bulunamad� deniyor.
	
		if (kelimeId == -1) {
			Toast.makeText(getApplicationContext(),
					"G�ncellenecek kelime bulunamad�", Toast.LENGTH_LONG)
					.show();
			return;
		}
		// Urun'un yeni bilgileri getUrunId'si ile al�nm�st� ve o degere gore guncelleme gerceklestirilyor.
		SQLiteDatabase db = veritaban�.getWritableDatabase();
		// Hangi tablo'nun , hangi sat�r�n ne olarak guncellenecegi bilgilerini veriyoruz.
		String where = VeriTaban�Yiyecekler.URUN_ID + "=" + kelimeId;
		db.update(VeriTaban�Yiyecekler.TABLE_NAME, guncelSatir, where, null);
		//Urun guncellemesi oldugu icin Listede Guncelleniyor.
		listeGuncelle();
	}
	// Listedeki bir urun'u silecegimiz zaman Id degerini tutuyoruz.
	private void UrunSil(String ad) {

		int kelimeId = getUrunId(ad);
		// verilen isim degerinde bir yemek bulunamad� ise mesaj donduruluyor.
		if (kelimeId == -1) {
			Toast.makeText(getApplicationContext(),
					"Silinecek yemek bulunamad�", Toast.LENGTH_LONG).show();
			return;
		}
		// Al�nan Id degerine gore Veritaban�nda silme islemi gerceklestirilyor.
		SQLiteDatabase db = veritaban�.getWritableDatabase();
		String where = VeriTaban�Yiyecekler.URUN_ID + "=" + kelimeId;
		db.delete(VeriTaban�Yiyecekler.TABLE_NAME, where, null);
		listeGuncelle();

	}

	// ListView'de se�im yap�ld��� zaman EditText'lere yap�lan se�imler ekleniyor. 
	private void editTextGuncelle(Cursor cursor, int position,
			EditText AdEditText, EditText FiyatEditText) {
		// cursor nesnesine secilmis olan position getiriliyor.
		cursor.moveToPosition(position);
		/* cursor nesnesinde secilmis sat�r'a gelindi. O sat�rdaki Ad degerinin Column index'i tutuluyor
		 * ve Fiyat degerinin column index'i tutuluyor. */
		int adIndex = cursor.getColumnIndex(VeriTaban�Yiyecekler.URUN_ADI);
		int FiyatIndex = cursor
				.getColumnIndex(VeriTaban�Yiyecekler.URUN_FIYATI);
		// Ad ve Fiyat degerleri ilgili nesneden cekiliyor.
		String Ad = cursor.getString(adIndex);
		String Fiyat = cursor.getString(FiyatIndex);
		// EditText'e secilmis olan bilgilerin getirilmesi saglan�yor.
		AdEditText.setText(Ad);
		FiyatEditText.setText(Fiyat);

	}
	// Listesi guncelle diyerek adapter'�n yenilenmesi saglan�yor.
	private void listeGuncelle() {
		
		kelimeListesiCursor.requery();
		adapter.notifyDataSetChanged();

	}
	// L�steyi ilk dolduracag�m�z zaman tum urunler sorgulan�yor ve donen cursor nesnesi adapter'a ekleniyor.
	private Cursor butunUrunleriSorgula() {
		
		SQLiteDatabase db = veritaban�.getReadableDatabase();
		return db.rawQuery("select Id as _id, Ad, Fiyat from yiyecekler", new String[] {});
		
//		VeriTaban�Yiyecekler.TABLE_NAME, projection, null,
//		null, null, null, null
//		
	


	}

}
