package com.example.AndroidYemekSepetiDb;

import com.example.VeriTabani.VeriTaban�Salatalar;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast; 
import android.widget.AdapterView.OnItemClickListener;

public class PanelSalatalar extends Activity {

	private VeriTaban�Salatalar veritaban�;
	private SimpleCursorAdapterClass adapter;
	private Cursor kelimeListesiCursor;
	
	private String[] projection = new String[] {
			VeriTaban�Salatalar.URUN_ID,
			VeriTaban�Salatalar.URUN_ADI,
			VeriTaban�Salatalar.URUN_FIYATI
      };
	
	private String[] from = new String[] {
			
			VeriTaban�Salatalar.URUN_ADI,
			VeriTaban�Salatalar.URUN_FIYATI
    	};

	private int [] to = new int[] {
		R.id.AdListItemTextView,
		R.id.FiyatListItemTextView
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.salatalar);
		ekranKontrolleriniOlustur();
	}
	
	private void ekranKontrolleriniOlustur() {

        veritaban� = new VeriTaban�Salatalar(this);
        SQLiteDatabase db = veritaban�.getReadableDatabase();
        
        kelimeListesiCursor = butunUrunleriSorgula();
        adapter = new SimpleCursorAdapterClass(this,R.layout.list_item, kelimeListesiCursor, from, to, 0);
        
        
        final EditText AdEditText = (EditText) findViewById(R.id.SalatalarAdEditText);
        final EditText FiyatEditText = (EditText) findViewById(R.id.SalatalarFiyatEditText);

        Button kaydetButton = (Button) findViewById(R.id.SalatalarkaydetButton);
        kaydetButton.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			String ad = AdEditText.getText().toString();
    			String fiyat = FiyatEditText.getText().toString();
    			UrunEkle(ad, fiyat);
    		}
    	});
    	
        Button guncelleButton = (Button) findViewById(R.id.SalatalarguncelleButton);
        guncelleButton.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			String ad = AdEditText.getText().toString();
    			String fiyat = FiyatEditText.getText().toString();
    			UrunGuncelle(ad, fiyat);
    		}
    	});
        
        Button silButton = (Button) findViewById(R.id.SalatalarsilButton);
        silButton.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			String ad = AdEditText.getText().toString();
    			kelimeSil(ad);
    		}
    	});
        
        ListView sozlukListView = (ListView) findViewById(R.id.SalatalarListView);
        sozlukListView.setAdapter(adapter);
        
        sozlukListView.setOnItemClickListener(new OnItemClickListener() {
        	
			public void onItemClick(AdapterView arg0, View view, int position, long id) {
				adapter.setSelectedPosition(position);
				editTextGuncelle(kelimeListesiCursor, position, AdEditText, FiyatEditText);
			}
        });
        
	}
	private int getUrunId(String ad) {
    	
    	Cursor cursor = UrunSorgula(ad);
    	
    	if(cursor == null)
    		return -1;
    	
    	int count = cursor.getCount();
    	
    	if(count != 1)
    		return -1;
    	
    	cursor.moveToNext();
    	
    	int idIndex = cursor.getColumnIndex(VeriTaban�Salatalar.URUN_ID);
    	
    	return cursor.getInt(idIndex); 
    	
    }
        
        private long UrunEkle(String Ad, String Fiyat) {
        	
        	int kelimeId = getUrunId(Ad);
        	
        	if(kelimeId != -1) {
        		Toast.makeText(getApplicationContext(), "Bu kelime daha �nce eklenmi�tir", Toast.LENGTH_LONG).show();
        		return -1;
        	}
        	
        	ContentValues satir = new ContentValues();
        	satir.put("Ad", Ad);
        	satir.put("Fiyat", Fiyat);
        	
        	SQLiteDatabase db = veritaban�.getWritableDatabase();
        	long eklenenKelimeId = db.insert(VeriTaban�Salatalar.TABLE_NAME, null, satir);
        	listeGuncelle();
        	return eklenenKelimeId;
        	
        }
        private void UrunGuncelle(String Ad, String Fiyat) {
        	
        	ContentValues guncelSatir = new ContentValues();
        	guncelSatir.put("Ad", Ad);
        	guncelSatir.put("Fiyat", Fiyat);
        	
        	int kelimeId = getUrunId(Ad);
        	
        	if(kelimeId == -1) {
        		Toast.makeText(getApplicationContext(), "G�ncellenecek kelime bulunamad�", Toast.LENGTH_LONG).show();
        		return;
        	}
        	
        	SQLiteDatabase db = veritaban�.getWritableDatabase();
        	String where = VeriTaban�Salatalar.URUN_ID + "=" + kelimeId;
        	db.update(VeriTaban�Salatalar.TABLE_NAME, guncelSatir, where, null);
        	listeGuncelle();
        	
        }
        
        private void kelimeSil(String ad) {
        	
        	int kelimeId = getUrunId(ad);
        	
        	if(kelimeId == -1) {
        		Toast.makeText(getApplicationContext(), "Silinecek kelime bulunamad�", Toast.LENGTH_LONG).show();
        		return;
        	}
        	
        	SQLiteDatabase db = veritaban�.getWritableDatabase();
        	String where = VeriTaban�Salatalar.URUN_ID  + "=" + kelimeId;
        	db.delete(VeriTaban�Salatalar.TABLE_NAME, where, null);
        	listeGuncelle();
        	
        }
        
        private void editTextGuncelle(Cursor cursor, int position, EditText adEditText, EditText aciklamaEditText) {
        	
        	cursor.moveToPosition(position);
        	
        	int AdIndex = cursor.getColumnIndex(VeriTaban�Salatalar.URUN_ADI);
        	int FiyatIndex = cursor.getColumnIndex(VeriTaban�Salatalar.URUN_FIYATI);
        	
        	String Ad = cursor.getString(AdIndex);
        	String Fiyat = cursor.getString(FiyatIndex);
        	
        	adEditText.setText(Ad);
        	aciklamaEditText.setText(Fiyat);
        	
        }
        
        private void listeGuncelle() {
        	
        	kelimeListesiCursor.requery();
        	adapter.notifyDataSetChanged();
        	
        }
        private Cursor UrunSorgula(String ad) {
        	
        	if(ad == null)
        		throw new RuntimeException("Kelime ad� sorgulama i�lemi i�in bo� b�rak�lamaz");
        	
        	String where =VeriTaban�Salatalar.URUN_ADI+ "=?";
        	String [] whereArgs = new String [] {ad};
        	
        	SQLiteDatabase db = veritaban�.getReadableDatabase();
        	Cursor cursor = db.query(VeriTaban�Salatalar.TABLE_NAME, projection, where, whereArgs, null, null, null);
        	
        	return cursor;
        	
        }
        
        private Cursor butunUrunleriSorgula() {
        	
        	SQLiteDatabase db = veritaban�.getReadableDatabase();
        	return db.rawQuery("select Id as _id, Ad, Fiyat from salatalar", new String[] {});
            
        }
        

}
