package com.kodlab.dictionary;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivityWithContentProvider extends Activity {
	
	private SelectableSimpleCursorAdapter adapter;
	private Cursor kelimeListesiCursor;
	
	private String[] from = new String[] { DictionaryContract.Kelime.COLUMN_AD,
			DictionaryContract.Kelime.COLUMN_ACIKLAMA };

	private int[] to = new int[] { R.id.adListItemTextView,
			R.id.aciklamaListItemTextView };
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ekranKontrolleriniOlustur();
    }
	
	private void ekranKontrolleriniOlustur() {
		
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
	
	private Cursor kelimeSorgula(String ad) {

		ContentResolver resolver = getContentResolver();

		String where = DictionaryContract.Kelime.COLUMN_AD + "=?";
		String[] whereArgs = new String[] { ad };

		return resolver.query(DictionaryContract.Kelime.CONTENT_URI,
							  DictionaryContract.Kelime.FULL_PROJECTION, 
							  where, 
							  whereArgs,
							  null);

	}
	
	private int getKelimeId(String ad) {

		Cursor cursor = kelimeSorgula(ad);

		if (cursor == null || cursor.getCount() != 1)
			return -1;

		cursor.moveToNext();

		int idIndex = cursor.getColumnIndex(DictionaryContract.Kelime._ID);

		return cursor.getInt(idIndex);

	}
	
	private void kelimeEkle(String ad, String aciklama) {
		
		int kelimeId = getKelimeId(ad);
    	
    	if(kelimeId != -1) {
    		Toast.makeText(getApplicationContext(), "Bu kelime daha önce eklenmiþtir", Toast.LENGTH_LONG).show();
    		return;
    	}
    	
    	ContentValues satir = new ContentValues();
    	satir.put(DictionaryContract.Kelime.COLUMN_AD, ad);
    	satir.put(DictionaryContract.Kelime.COLUMN_ACIKLAMA, aciklama);
    	
    	ContentResolver resolver = getContentResolver();
    	resolver.insert(DictionaryContract.Kelime.CONTENT_URI, satir);
    	listeGuncelle();
    	
	}
	
	private void kelimeSil(String ad) {

		int kelimeId = getKelimeId(ad);

		if (kelimeId == -1) {
			Toast.makeText(getApplicationContext(),
					"Silinecek kelime bulunamadý", Toast.LENGTH_LONG).show();
			return;
		}

		Uri silinecekKelimeUri = ContentUris.withAppendedId(
				DictionaryContract.Kelime.CONTENT_URI, kelimeId);
		ContentResolver resolver = getContentResolver();
		resolver.delete(silinecekKelimeUri, null, null);
		listeGuncelle();

	}
	
	private void kelimeGuncelle(String ad, String aciklama) {
		
		int kelimeId = getKelimeId(ad);
    	
    	if(kelimeId == -1) {
    		Toast.makeText(getApplicationContext(), "Güncellenecek kelime bulunamadý", Toast.LENGTH_LONG).show();
    		return;
    	}
    	
    	ContentValues guncelSatir = new ContentValues();
    	guncelSatir.put(DictionaryContract.Kelime.COLUMN_AD, ad);
    	guncelSatir.put(DictionaryContract.Kelime.COLUMN_ACIKLAMA, aciklama);
    	
    	Uri guncellenecekKelimeUri = ContentUris.withAppendedId(DictionaryContract.Kelime.CONTENT_URI, kelimeId);
    	ContentResolver resolver = getContentResolver();
    	resolver.update(guncellenecekKelimeUri, guncelSatir, null, null);
    	listeGuncelle();
		
	}
	
	private void editTextGuncelle(Cursor cursor, int position,
			EditText adEditText, EditText aciklamaEditText) {

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
		
		ContentResolver resolver = getContentResolver();

		return resolver.query(DictionaryContract.Kelime.CONTENT_URI,
							  DictionaryContract.Kelime.FULL_PROJECTION, 
							  null, null, null);
		
	}
	
	

}
