package com.example.VeriTabani;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class VeriTabanýSalatalar extends SQLiteOpenHelper{

	public static final String DATABASE_NAME = "yemeksepeti";
	public static final String TABLE_NAME = "salatalar";
	public static final String URUN_ID = "Id";
	public static final String URUN_ADI = "Ad";
	public static final String URUN_FIYATI = "Fiyat";

	public static final int DATABASE_VERSION = 2;
	public static final String DATABASE_DROP = "DROP TABLE IF EXISTS "
			+ TABLE_NAME;
	public static final String sql = " CREATE TABLE " + TABLE_NAME + " ("
			+ URUN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + URUN_ADI
			+ " TEXT NOT NULL, " + URUN_FIYATI + " REAL NOT NULL );";

	public void deleteTable() {
		SQLiteDatabase db = this.getWritableDatabase();
		// db.delete(TABLE_NAME,null,null);
//		db.execSQL(DATABASE_DROP);
		onCreate(db);
	}

	public VeriTabanýSalatalar(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
//		deleteTable();
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
		db.execSQL(sql);
		onCreate(db);

	}
}
