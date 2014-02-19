package com.example.AndroidYemekSepetiDb;

import com.example.VeriTabani.VeriTabanýKisiler;
import com.example.VeriTabani.VeriTabanýTatlýlar;

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

public class PanelTatlilar extends Activity {

	private VeriTabanýTatlýlar veritabaný;
	private SimpleCursorAdapterClass adapter;
	private Cursor kelimeListesiCursor;
	
	private String[] projection = new String[] {
			VeriTabanýTatlýlar.URUN_ID,
			VeriTabanýTatlýlar.URUN_ADI,
			VeriTabanýTatlýlar.URUN_FIYATI
      };
	
	private String[] from = new String[] {
			
			VeriTabanýTatlýlar.URUN_ADI,
			VeriTabanýTatlýlar.URUN_FIYATI
    	};

	private int [] to = new int[] {
		R.id.AdListItemTextView,
		R.id.FiyatListItemTextView
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tatlilar);
		ekranKontrolleriniOlustur();
	}
	
	private void ekranKontrolleriniOlustur() {

        veritabaný = new VeriTabanýTatlýlar(this);
        SQLiteDatabase db = veritabaný.getReadableDatabase();
        
        kelimeListesiCursor = butunUrunleriSorgula();
        adapter = new SimpleCursorAdapterClass(this,R.layout.list_item, kelimeListesiCursor, from, to, 0);
        
        
        final EditText AdEditText = (EditText) findViewById(R.id.TatlilarAdEditText);
        final EditText FiyatEditText = (EditText) findViewById(R.id.TatlilarFiyatEditText);

        Button kaydetButton = (Button) findViewById(R.id.TatlilarkaydetButton);
        kaydetButton.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			String ad = AdEditText.getText().toString();
    			String fiyat = FiyatEditText.getText().toString();
    			UrunEkle(ad, fiyat);
    		}
    	});
    	
        Button guncelleButton = (Button) findViewById(R.id.TatlilarguncelleButton);
        guncelleButton.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			String ad = AdEditText.getText().toString();
    			String fiyat = FiyatEditText.getText().toString();
    			UrunGuncelle(ad, fiyat);
    		}
    	});
        
        Button silButton = (Button) findViewById(R.id.TatlilarsilButton);
        silButton.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			String ad = AdEditText.getText().toString();
    			kelimeSil(ad);
    		}
    	});
        
        ListView sozlukListView = (ListView) findViewById(R.id.TatlilarListView);
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
    	
    	int idIndex = cursor.getColumnIndex(VeriTabanýTatlýlar.URUN_ID);
    	
    	return cursor.getInt(idIndex); 
    	
    }
        
        private long UrunEkle(String Ad, String Fiyat) {
        	
        	int kelimeId = getUrunId(Ad);
        	
        	if(kelimeId != -1) {
        		Toast.makeText(getApplicationContext(), "Bu kelime daha önce eklenmiþtir", Toast.LENGTH_LONG).show();
        		return -1;
        	}
        	
        	ContentValues satir = new ContentValues();
        	satir.put("Ad", Ad);
        	satir.put("Fiyat", Fiyat);
        	
        	SQLiteDatabase db = veritabaný.getWritableDatabase();
        	long eklenenKelimeId = db.insert(VeriTabanýTatlýlar.TABLE_NAME, null, satir);
        	listeGuncelle();
        	return eklenenKelimeId;
        	
        }
        private void UrunGuncelle(String Ad, String Fiyat) {
        	
        	ContentValues guncelSatir = new ContentValues();
        	guncelSatir.put("Ad", Ad);
        	guncelSatir.put("Fiyat", Fiyat);
        	
        	int kelimeId = getUrunId(Ad);
        	
        	if(kelimeId == -1) {
        		Toast.makeText(getApplicationContext(), "Güncellenecek kelime bulunamadý", Toast.LENGTH_LONG).show();
        		return;
        	}
        	
        	SQLiteDatabase db = veritabaný.getWritableDatabase();
        	String where = VeriTabanýTatlýlar.URUN_ID + "=" + kelimeId;
        	db.update(VeriTabanýTatlýlar.TABLE_NAME, guncelSatir, where, null);
        	listeGuncelle();
        	
        }
        
        private void kelimeSil(String ad) {
        	
        	int kelimeId = getUrunId(ad);
        	
        	if(kelimeId == -1) {
        		Toast.makeText(getApplicationContext(), "Silinecek kelime bulunamadý", Toast.LENGTH_LONG).show();
        		return;
        	}
        	
        	SQLiteDatabase db = veritabaný.getWritableDatabase();
        	String where = VeriTabanýTatlýlar.URUN_ID  + "=" + kelimeId;
        	db.delete(VeriTabanýTatlýlar.TABLE_NAME, where, null);
        	listeGuncelle();
        	
        }
        
        private void editTextGuncelle(Cursor cursor, int position, EditText AdEditText, EditText FiyatEditText) {
        	
        	cursor.moveToPosition(position);
        	
        	int AdIndex = cursor.getColumnIndex(VeriTabanýTatlýlar.URUN_ADI);
        	int FiyatIndex = cursor.getColumnIndex(VeriTabanýTatlýlar.URUN_FIYATI);
        	
        	String Ad = cursor.getString(AdIndex);
        	String Fiyat = cursor.getString(FiyatIndex);
        	
        	AdEditText.setText(Ad);
        	FiyatEditText.setText(Fiyat);
        	
        }
        
        private void listeGuncelle() {
        	
        	kelimeListesiCursor.requery();
        	adapter.notifyDataSetChanged();
        	
        }
        private Cursor UrunSorgula(String ad) {
        	
        	if(ad == null)
        		throw new RuntimeException("Kelime adý sorgulama iþlemi için boþ býrakýlamaz");
        	
        	String where =VeriTabanýTatlýlar.URUN_ADI+ "=?";
        	String [] whereArgs = new String [] {ad};
        	
        	SQLiteDatabase db = veritabaný.getReadableDatabase();
        	Cursor cursor = db.query(VeriTabanýTatlýlar.TABLE_NAME, projection, where, whereArgs, null, null, null);
        	
        	return cursor;
        	
        }
        
        private Cursor butunUrunleriSorgula() {
        	
        	SQLiteDatabase db = veritabaný.getReadableDatabase();
        	return db.rawQuery("select Id as _id, Ad, Fiyat from tatlilar", new String[] {});
            
        }
        

}
