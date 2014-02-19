package com.example.VeriTabani;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class VeriTabanýYiyecekler extends SQLiteOpenHelper implements BaseColumns {

	public static final String DATABASE_NAME = "yemeksepeti";
	public static final String TABLE_NAME = "yiyecekler";
	public static final String URUN_ID = "Id";
	public static final String URUN_ADI = "Ad";
	public static final String URUN_FIYATI = "Fiyat";
//	public static final String[] FULL_PROJECTION = new String[] {_ID, URUN_ADI, URUN_FIYATI};

	public static final int DATABASE_VERSION = 2;
	public static final String DATABASE_DROP = "DROP TABLE IF EXISTS "
			+ TABLE_NAME;
	// Tabloyu yaratacak sql cumlesi
	public static final String sql = " CREATE TABLE " + TABLE_NAME + " ("
			+ URUN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + URUN_ADI
			+ " TEXT, " + URUN_FIYATI + " INTEGER );";

	public void deleteTable() {
		SQLiteDatabase db = this.getWritableDatabase();
		// db.delete(TABLE_NAME,null,null);
		db.execSQL(DATABASE_DROP);
		onCreate(db);
	}

	public VeriTabanýYiyecekler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
//		 deleteTable();
	}

	// table ve alanlarini olsuturuyor.Dikkat + ile string eklediginizde
	// oncekinden ayirmak
	// icin bosluk birazkin. Mesela sql="CREATE TABLE bosluk"+TABLE_NAME gibi
	@Override
	public void onCreate(SQLiteDatabase dbObject) {

		dbObject.execSQL(sql);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		Log.w("yemeksepeti", "Veritabani " + oldVersion + "\'dan" + newVersion
				+ "\'a guncelleniyor");

		db.execSQL(DATABASE_DROP);

		onCreate(db);

	}
}
