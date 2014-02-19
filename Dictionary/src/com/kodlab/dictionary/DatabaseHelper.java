package com.kodlab.dictionary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	public static final String DATABASE_CREATE = 
			"CREATE TABLE " + DictionaryContract.TABLE_NAME + " (" +
			DictionaryContract.Kelime._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            DictionaryContract.Kelime.COLUMN_AD + " TEXT NOT NULL, " +
            DictionaryContract.Kelime.COLUMN_ACIKLAMA + " TEXT);";
	
	public static final String DATABASE_DROP = "DROP TABLE IF EXISTS " + DictionaryContract.TABLE_NAME;
	
	public DatabaseHelper(Context context) {
		super(context, DictionaryContract.DATABASE_NAME, null, DictionaryContract.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		Log.w("DatabaseHelper", "Veritabani " + oldVersion + "\'dan" + newVersion + "\'a guncelleniyor");
		
		db.execSQL(DATABASE_DROP);
		onCreate(db);
		
	}

}
