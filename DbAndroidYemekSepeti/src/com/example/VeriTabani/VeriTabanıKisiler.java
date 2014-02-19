package com.example.VeriTabani;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class VeriTabanýKisiler extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "yemeksepeti";
	public static final String TABLE_NAME = "kullanicilar";
	public static final String KISI_ID = "KullaniciId";
	public static final String KISI_ADI = "Ad";
	public static final String KISI_SOYAD = "Soyad";
	public static final String KISI_DTARIH = "DTarih";
	public static final String KISI_ADRES = "Adres";
	public static final String KISI_TEL = "Tel";
	public static final String KISI_EPOSTA = "EPosta";
	public static final String KISI_SIFRE = "Sifre";
	public static final String KISI_ADMINMI = "Admin";
	public static final int DATABASE_VERSION = 2;
	public static final String DATABASE_DROP = "DROP TABLE IF EXISTS "
			+ TABLE_NAME;
	public static final String sql = " CREATE TABLE " + TABLE_NAME + " ("
			+ KISI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KISI_ADI
			+ " TEXT NOT NULL, " + KISI_SOYAD + " TEXT NOT NULL, "
			+ KISI_DTARIH + " TEXT, " + KISI_ADRES + " TEXT, " + KISI_EPOSTA
			+ " TEXT, " + KISI_TEL + " INTEGER, " + KISI_SIFRE + " TEXT," + KISI_ADMINMI + " INTEGER);";

	public void deleteTable() {
		SQLiteDatabase db = this.getWritableDatabase();
		// db.delete(TABLE_NAME,null,null);
		db.execSQL(DATABASE_DROP);
		onCreate(db);
	}
	


	public VeriTabanýKisiler(Context context)
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
		
		onCreate(db);

	}

}
