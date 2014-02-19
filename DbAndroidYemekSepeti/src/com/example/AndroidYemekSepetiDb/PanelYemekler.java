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

import com.example.VeriTabani.VeriTabanýYiyecekler;

public class PanelYemekler extends Activity {

	private VeriTabanýYiyecekler veritabaný;
	private SimpleCursorAdapterClass adapter;
	private Cursor kelimeListesiCursor;
	// Kelimeleri tutacak olan Cursor nesnesi.
	
	// Database icindeki verileri ListView'e map ederken from ve to 'yu kullanýyoruz .
	private String[] from = new String[] {

	// Adapter'a eklenmek uzere from ve to Listeleri tanýmlanýyor.
	// From'da nelerin eklenecegi bilgisi var.
	VeriTabanýYiyecekler.URUN_ADI, 
	VeriTabanýYiyecekler.URUN_FIYATI };

	// to icerisinde eklenecek itemlerin hangi TextView'e eklenecegi bilgisi var .
	private int[] to = new int[] { 
			R.id.AdListItemTextView,
			R.id.FiyatListItemTextView };

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.yemekler);
		
		// Ekrana acýlýsta goruntunun gelmesi icin onCreate metoduna yazýldý.
		ekranKontrolleriniOlustur();
	}

	private void ekranKontrolleriniOlustur() {

		// Veritabaný nesnesi , ilgili Class'dan olusturuluyor.
		veritabaný = new VeriTabanýYiyecekler(this);
		// butun urunler sorgulanýp sonuclarý tutuluyor.Bu nesne adapter'a ekleniyor.
		kelimeListesiCursor = butunUrunleriSorgula();
		adapter = new SimpleCursorAdapterClass(this, R.layout.listtextviews,
				kelimeListesiCursor, from, to, 0);

		final EditText AdEditText = (EditText) findViewById(R.id.YemeklerAdEditText);
		final EditText FiyatEditText = (EditText) findViewById(R.id.YemeklerFiyatEditText);

		// Urun ekleme isleminde kaydet butonuna basýlacagý zaman gerceklesek olaylar. 
		
		Button kaydetButton = (Button) findViewById(R.id.kaydetBtnYemekler);
		kaydetButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Urun ekle butonuna basýldýgý zaman UrunEkle methodu gerceklestiriliyor.
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
				// Urun Guncelle butonuna basýldýgýnda ad,fiyat bilgileriyle UrunGuncelle methodu gerceklestiriliyor.
				UrunGuncelle(ad, fiyat);
			}
		});

		Button silButton = (Button) findViewById(R.id.silBtnYemekler);
		silButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String ad = AdEditText.getText().toString();
				// Urun sil denildigi zaman ismi verilen ürün siliniyor. Her isim'den birtane kayýt ediliyordu
				UrunSil(ad);
			}
		});
		//ListView Olusturuldu ve adapter set edildi.
		ListView YemeklerListView = (ListView) findViewById(R.id.SiparislerListView);
		YemeklerListView.setAdapter(adapter);
		// YemeklerListesinde item secilmesi anýnda gerceklesecek olaylar burda yer almakta.
		YemeklerListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView arg0, View view, int position,
					long id) {
				//Adapter, secilmis olan pozisyona getiriliyor.
				adapter.setSelectedPosition(position);
				// Listede herhangi bir yemek secildigi zaman o yemekler EditText'lere aktarýlýyor.
				editTextGuncelle(kelimeListesiCursor, position, AdEditText,
						FiyatEditText);
			}
		});

	}

	// yemek listesinde adý verilen yemegin Id degeri getiriliyor.
	private int getUrunId(String ad) {

		// Id degerini tutmak icin isime gore urun sorgulanýyor.Sonuc Cursor'da tutuluyor.
		Cursor cursor = UrunSorgula(ad);
		//Cursor bos ise -1 dondur.
		if (cursor == null)
			return -1;
		// secilmis item sayýsý sadece 1 olmalý. Onun kontrolu yapýlýyor.
		int count = cursor.getCount();

		if (count != 1)
			return -1;
		
		// cursor ilk basta satýr'ýn ilk elemanýný degil, 1 geriyi tutuyor.Ilk elemana gelmesi saglanýyor.
		cursor.moveToNext();
		// Urun adýnýn bulundugu satýrda, Urun_Id degerinin bulundugu sutundaki deger degiskene atýlýyor
		int idIndex = cursor.getColumnIndex(VeriTabanýYiyecekler.URUN_ID);

		// Id degeri return edliyor.
		return cursor.getInt(idIndex);

	}

	private Cursor UrunSorgula(String ad) {

		if (ad == null)
			throw new RuntimeException(
					"Kelime adý sorgulama iþlemi için boþ býrakýlamaz");


		String[] whereArgs = new String[] { ad };

		SQLiteDatabase db = veritabaný.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from yiyecekler where yiyecekler.Ad=?",whereArgs);
//		VeriTabanýYiyecekler.TABLE_NAME, projection,
//		where, whereArgs, null, null, null
		return cursor;

	}

	private long UrunEkle(String Ad, String Fiyat) {

		// Urun Id degeri getUrunId methodu ile alýndý.Bunun icin id'si alýnacak yemegin adý verildi.
		int UrunId = getUrunId(Ad);

		// getUrunId methoduyla Ismý verilen yemekte bir urun bulundu ise daha once eklenmistir deniliyor.
		// ve Cýkýlýyor..
		if (UrunId != -1) {
			Toast.makeText(getApplicationContext(),
					"Bu kelime daha önce eklenmiþtir", Toast.LENGTH_LONG)
					.show();
			return -1;
		}
		// Urun olmamasý durumunda verilen Ad ve Soyad degerlerine gore Db'ye ekleme yapýlýyor.
		// Bunun icin ContentValues sýnýfý kullanýlýyor.

		ContentValues satir = new ContentValues();
		satir.put("Ad", Ad);
		satir.put("Fiyat", Fiyat);
		
		// Daha once olusturulmus ilgili veritabaný referansýndan bir database nesnesi olusturuluyor.
		// Ve Ekleme islemleri bu nesne uzerinden yapýlýyor.
		SQLiteDatabase db = veritabaný.getWritableDatabase();
		// Veritabanýna urun eklendigi zaman (basarilý ise eklenen satir'in Id'si donuyor.)
		long eklenenKelimeId = db.insert(VeriTabanýYiyecekler.TABLE_NAME, null,
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
		// Veritabanýndan verilen isme gore bir Id degeri elde edilemiyor ise guncellenecek kelime bulunamadý deniyor.
	
		if (kelimeId == -1) {
			Toast.makeText(getApplicationContext(),
					"Güncellenecek kelime bulunamadý", Toast.LENGTH_LONG)
					.show();
			return;
		}
		// Urun'un yeni bilgileri getUrunId'si ile alýnmýstý ve o degere gore guncelleme gerceklestirilyor.
		SQLiteDatabase db = veritabaný.getWritableDatabase();
		// Hangi tablo'nun , hangi satýrýn ne olarak guncellenecegi bilgilerini veriyoruz.
		String where = VeriTabanýYiyecekler.URUN_ID + "=" + kelimeId;
		db.update(VeriTabanýYiyecekler.TABLE_NAME, guncelSatir, where, null);
		//Urun guncellemesi oldugu icin Listede Guncelleniyor.
		listeGuncelle();
	}
	// Listedeki bir urun'u silecegimiz zaman Id degerini tutuyoruz.
	private void UrunSil(String ad) {

		int kelimeId = getUrunId(ad);
		// verilen isim degerinde bir yemek bulunamadý ise mesaj donduruluyor.
		if (kelimeId == -1) {
			Toast.makeText(getApplicationContext(),
					"Silinecek yemek bulunamadý", Toast.LENGTH_LONG).show();
			return;
		}
		// Alýnan Id degerine gore Veritabanýnda silme islemi gerceklestirilyor.
		SQLiteDatabase db = veritabaný.getWritableDatabase();
		String where = VeriTabanýYiyecekler.URUN_ID + "=" + kelimeId;
		db.delete(VeriTabanýYiyecekler.TABLE_NAME, where, null);
		listeGuncelle();

	}

	// ListView'de seçim yapýldýðý zaman EditText'lere yapýlan seçimler ekleniyor. 
	private void editTextGuncelle(Cursor cursor, int position,
			EditText AdEditText, EditText FiyatEditText) {
		// cursor nesnesine secilmis olan position getiriliyor.
		cursor.moveToPosition(position);
		/* cursor nesnesinde secilmis satýr'a gelindi. O satýrdaki Ad degerinin Column index'i tutuluyor
		 * ve Fiyat degerinin column index'i tutuluyor. */
		int adIndex = cursor.getColumnIndex(VeriTabanýYiyecekler.URUN_ADI);
		int FiyatIndex = cursor
				.getColumnIndex(VeriTabanýYiyecekler.URUN_FIYATI);
		// Ad ve Fiyat degerleri ilgili nesneden cekiliyor.
		String Ad = cursor.getString(adIndex);
		String Fiyat = cursor.getString(FiyatIndex);
		// EditText'e secilmis olan bilgilerin getirilmesi saglanýyor.
		AdEditText.setText(Ad);
		FiyatEditText.setText(Fiyat);

	}
	// Listesi guncelle diyerek adapter'ýn yenilenmesi saglanýyor.
	private void listeGuncelle() {
		
		kelimeListesiCursor.requery();
		adapter.notifyDataSetChanged();

	}
	// Lýsteyi ilk dolduracagýmýz zaman tum urunler sorgulanýyor ve donen cursor nesnesi adapter'a ekleniyor.
	private Cursor butunUrunleriSorgula() {
		
		SQLiteDatabase db = veritabaný.getReadableDatabase();
		return db.rawQuery("select Id as _id, Ad, Fiyat from yiyecekler", new String[] {});
		
//		VeriTabanýYiyecekler.TABLE_NAME, projection, null,
//		null, null, null, null
//		
	


	}

}
