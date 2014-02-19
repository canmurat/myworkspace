package com.example.VeriTabani;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class VeriTabanýSiparisler extends SQLiteOpenHelper implements BaseColumns {

	public static final String DATABASE_NAME = "yemeksepeti";
	public static final String TABLE_NAME = "siparisler";
	public static final String SIPARIS_ID = "SiparisId";
	public static final String KULLANICI_ID = "KullaniciId";
	public static final String YEMEKLER = "Yemekler";
	public static final String ICECEKLER = "Icecekler";
	public static final String SALATALAR = "Salatalar";
	public static final String TATLILAR = "Tatlilar";
	public static final String TOPLAM = "Toplam";
	public static final String TARIH = "Tarih";
//	public static final String[] FULL_PROJECTION = new String[] {_ID, URUN_ADI, URUN_FIYATI};

	public static final int DATABASE_VERSION = 2;
	public static final String DATABASE_DROP = "DROP TABLE IF EXISTS "
			+ TABLE_NAME;
	public static final String sql = " CREATE TABLE " + TABLE_NAME + " ("
			+ SIPARIS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KULLANICI_ID + " TEXT, " + YEMEKLER
			+ " TEXT, " + ICECEKLER + " TEXT, " + SALATALAR +" TEXT, "+ TATLILAR +" TEXT, "+ TOPLAM+" TEXT,"+TARIH+" TEXT);";

	public void deleteTable() {
		SQLiteDatabase db = this.getWritableDatabase();
		// db.delete(TABLE_NAME,null,null);
		db.execSQL(DATABASE_DROP);
		onCreate(db);
	}

	public VeriTabanýSiparisler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
//	 deleteTable();
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
