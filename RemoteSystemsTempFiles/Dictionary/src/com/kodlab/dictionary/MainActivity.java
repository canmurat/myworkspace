package com.kodlab.dictionary;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private DatabaseHelper helper;
	private SelectableSimpleCursorAdapter adapter;
	private Cursor kelimeListesiCursor;

	private String[] projection = new String[] {
										DictionaryContract.Kelime._ID,
										DictionaryContract.Kelime.COLUMN_AD,
										DictionaryContract.Kelime.COLUMN_ACIKLAMA
							      };
	
	private String[] from = new String[] {
								DictionaryContract.Kelime.COLUMN_AD,
					    		DictionaryContract.Kelime.COLUMN_ACIKLAMA
					    	};
    
    private int [] to = new int[] {
		    				R.id.adListItemTextView,
		    				R.id.aciklamaListItemTextView
	    				};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ekranKontrolleriniOlustur();
    }
    
    private void ekranKontrolleriniOlustur() {

        helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        
        kelimeListesiCursor = butunKelimeleriSorgula();
        adapter = new SelectableSimpleCursorAdapter(this,R.layout.list_item, kelimeListesiCursor, from, to, 0);
        
        
        final EditText adEditText = (EditText) findViewById(R.id.adEditText);
        final EditText aciklamaEditText = (EditText) findViewById(R.id.aciklamaEditText);

        Button kaydetButton = (Button) findViewById(R.id.kaydetButton);
        kaydetButton.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			String ad = adEditText.getText().toString();
    			String aciklama = aciklamaEditText.getText().toString();
    			kelimeEkle(ad, aciklama);
    		}
    	});
        
        Button guncelleButton = (Button) findViewById(R.id.guncelleButton);
        guncelleButton.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			String ad = adEditText.getText().toString();
    			String aciklama = aciklamaEditText.getText().toString();
    			kelimeGuncelle(ad, aciklama);
    		}
    	});
        
        Button silButton = (Button) findViewById(R.id.silButton);
        silButton.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			String ad = adEditText.getText().toString();
    			kelimeSil(ad);
    		}
    	});
        
        ListView sozlukListView = (ListView) findViewById(R.id.sozlukListView);
        sozlukListView.setAdapter(adapter);
        
        sozlukListView.setOnItemClickListener(new OnItemClickListener() {
        	
			public void onItemClick(AdapterView arg0, View view, int position, long id) {
				adapter.setSelectedPosition(position);
				editTextGuncelle(kelimeListesiCursor, position, adEditText, aciklamaEditText);
			}
        });
    	
    }
    
    
    private int getKelimeId(String ad) {
    	
    	Cursor cursor = kelimeSorgula(ad);
    	
    	if(cursor == null)
    		return -1;
    	
    	int count = cursor.getCount();
    	
    	if(count != 1)
    		return -1;
    	
    	cursor.moveToNext();
    	
    	int idIndex = cursor.getColumnIndex(DictionaryContract.Kelime._ID);
    	
    	return cursor.getInt(idIndex); 
    	
    }
    
    private Cursor kelimeSorgula(String ad) {
    	
    	if(ad == null)
    		throw new RuntimeException("Kelime adý sorgulama iþlemi için boþ býrakýlamaz");
    	
    	String where = DictionaryContract.Kelime.COLUMN_AD + "=?";
    	String [] whereArgs = new String [] {ad};
    	
    	SQLiteDatabase db = helper.getReadableDatabase();
    	Cursor cursor = db.query(DictionaryContract.TABLE_NAME, projection, where, whereArgs, null, null, null);
    	
    	return cursor;
    	
    }
    
    private long kelimeEkle(String ad, String aciklama) {
    	
    	int kelimeId = getKelimeId(ad);
    	
    	if(kelimeId != -1) {
    		Toast.makeText(getApplicationContext(), "Bu kelime daha önce eklenmiþtir", Toast.LENGTH_LONG).show();
    		return -1;
    	}
    	
    	ContentValues satir = new ContentValues();
    	satir.put("ad", ad);
    	satir.put("aciklama", aciklama);
    	
    	SQLiteDatabase db = helper.getWritableDatabase();
    	long eklenenKelimeId = db.insert(DictionaryContract.TABLE_NAME, null, satir);
    	listeGuncelle();
    	return eklenenKelimeId;
    	
    }
    
    private void kelimeGuncelle(String ad, String aciklama) {
    	
    	ContentValues guncelSatir = new ContentValues();
    	guncelSatir.put("ad", ad);
    	guncelSatir.put("aciklama", aciklama);
    	
    	int kelimeId = getKelimeId(ad);
    	
    	if(kelimeId == -1) {
    		Toast.makeText(getApplicationContext(), "Güncellenecek kelime bulunamadý", Toast.LENGTH_LONG).show();
    		return;
    	}
    	
    	SQLiteDatabase db = helper.getWritableDatabase();
    	String where = DictionaryContract.Kelime._ID + "=" + kelimeId;
    	db.update(DictionaryContract.TABLE_NAME, guncelSatir, where, null);
    	listeGuncelle();
    	
    }
    
    private void kelimeSil(String ad) {
    	
    	int kelimeId = getKelimeId(ad);
    	
    	if(kelimeId == -1) {
    		Toast.makeText(getApplicationContext(), "Silinecek kelime bulunamadý", Toast.LENGTH_LONG).show();
    		return;
    	}
    	
    	SQLiteDatabase db = helper.getWritableDatabase();
    	String where = DictionaryContract.Kelime._ID + "=" + kelimeId;
    	db.delete(DictionaryContract.TABLE_NAME, where, null);
    	listeGuncelle();
    	
    }
    
    private void editTextGuncelle(Cursor cursor, int position, EditText adEditText, EditText aciklamaEditText) {
    	
    	cursor.moveToPosition(position);
    	
    	int adIndex = cursor.getColumnIndex(DictionaryContract.Kelime.COLUMN_AD);
    	int aciklamaIndex = cursor.getColumnIndex(DictionaryContract.Kelime.COLUMN_ACIKLAMA);
    	
    	String ad = cursor.getString(adIndex);
    	String aciklama = cursor.getString(aciklamaIndex);
    	
    	adEditText.setText(ad);
    	aciklamaEditText.setText(aciklama);
    	
    }
    
    private void listeGuncelle() {
    	
    	kelimeListesiCursor.requery();
    	adapter.notifyDataSetChanged();
    	
    }
    
    private Cursor butunKelimeleriSorgula() {
    	
    	SQLiteDatabase db = helper.getReadableDatabase();
        return db.query(DictionaryContract.TABLE_NAME, projection, null, null, null, null, null);
        
    }

}
