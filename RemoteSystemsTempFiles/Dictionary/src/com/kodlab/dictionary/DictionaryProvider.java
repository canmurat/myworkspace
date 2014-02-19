package com.kodlab.dictionary;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class DictionaryProvider extends ContentProvider {
	
	private static final UriMatcher uriMatcher;
	private static final int TEK_KELIME = 1;
	private static final int COK_KELIME = 2;
	private DatabaseHelper helper;
	
	static {	
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(DictionaryContract.AUTHORITY, "kelime", COK_KELIME);
		uriMatcher.addURI(DictionaryContract.AUTHORITY, "kelime/#", TEK_KELIME);
	}

	@Override
	public boolean onCreate() {
		helper = new DatabaseHelper(getContext());
		return true;
	}
	
	@Override
	public String getType(Uri uri) {
		
		switch(uriMatcher.match(uri)) {
			case TEK_KELIME :
				return DictionaryContract.Kelime.CONTENT_ITEM_TYPE;
			case COK_KELIME :
				return DictionaryContract.Kelime.CONTENT_TYPE;
			default:
				throw new IllegalArgumentException("Hatali URI" + uri);
		}
		
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		queryBuilder.setTables(DictionaryContract.TABLE_NAME);
		
		switch(uriMatcher.match(uri)) {
			case TEK_KELIME :
				String kelimeId = uri.getPathSegments().get(1);
				queryBuilder.appendWhere(DictionaryContract.Kelime._ID + "=" + kelimeId);
				break;
			case COK_KELIME :
				break;
			default:
				throw new IllegalArgumentException("Hatali URI" + uri);
		}
		
		if(TextUtils.isEmpty(sortOrder))
			sortOrder = DictionaryContract.Kelime.DEFAULT_SORT_ORDER;
		
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);

		return cursor;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		
		SQLiteDatabase db = helper.getWritableDatabase();
		
		long kelimeId = db.insert(DictionaryContract.TABLE_NAME, null, values);
		
		if(kelimeId != -1) {
			
			Uri eklenenKelimeUri = ContentUris.
					withAppendedId(DictionaryContract.Kelime.CONTENT_URI, kelimeId);
			getContext().getContentResolver().notifyChange(eklenenKelimeUri, null);
			
			return eklenenKelimeUri;
		}
		
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		
		switch(uriMatcher.match(uri)) {
			case TEK_KELIME :
				String kelimeId = uri.getPathSegments().get(1);
				
				String kelimeIdCondition = DictionaryContract.Kelime._ID + "=" + kelimeId; 
				
				if(TextUtils.isEmpty(selection))
					selection = kelimeIdCondition;
				else
					selection = kelimeIdCondition + " AND (" + selection + ")";
				
				break;
			case COK_KELIME :
				break;
			default:
				throw new IllegalArgumentException("Hatali URI" + uri);
		}
		
		SQLiteDatabase db = helper.getWritableDatabase();
		int silinenSatirSayisi = db.delete(DictionaryContract.TABLE_NAME, selection, selectionArgs);
		getContext().getContentResolver().notifyChange(uri, null);
		
		return silinenSatirSayisi;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		
		switch(uriMatcher.match(uri)) {
			case TEK_KELIME :
				String kelimeId = uri.getPathSegments().get(1);
				
				String kelimeIdCondition = DictionaryContract.Kelime._ID + "=" + kelimeId; 
				
				if(TextUtils.isEmpty(selection))
					selection = kelimeIdCondition;
				else
					selection = kelimeIdCondition + " AND (" + selection + ")";
				
				break;
			case COK_KELIME :
				break;
			default:
				throw new IllegalArgumentException("Hatali URI" + uri);
		}
		
		SQLiteDatabase db = helper.getWritableDatabase();
		int guncellenenSatirSayisi = db.update(DictionaryContract.TABLE_NAME, values, selection, selectionArgs);
		getContext().getContentResolver().notifyChange(uri, null);
		
		return guncellenenSatirSayisi;
	}
	
	

}
