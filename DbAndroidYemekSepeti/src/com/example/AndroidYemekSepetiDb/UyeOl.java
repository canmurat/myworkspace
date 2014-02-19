package com.example.AndroidYemekSepetiDb;



import com.example.VeriTabani.VeriTabanýKisiler;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UyeOl extends Activity {

	
	EditText editAd;
	EditText editSoyad;
	EditText editDogumTarih;
	EditText editTel;
	EditText editAdres;
	EditText editEPosta;
	EditText editSifre;
	EditText editSifreTekrar;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uyeol);
		
		editAd = (EditText)findViewById(R.id.editAd);
		editSoyad = (EditText)findViewById(R.id.editSoyad);
		editDogumTarih = (EditText)findViewById(R.id.editDTarih);
		editTel = (EditText)findViewById(R.id.editTel);
		editAdres = (EditText)findViewById(R.id.editAdres);
		editEPosta = (EditText)findViewById(R.id.editEposta);
		editSifre = (EditText)findViewById(R.id.editSifre);
		editSifreTekrar = (EditText)findViewById(R.id.editSifreTekrar);
	}
	
	public void UyeOlButton(View v)
	{
		if(SifrelerAynimi() != false )
		{
			try{
				UyeEkle();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			Toast.makeText(this, "Uye eklemede sorun var", Toast.LENGTH_LONG).show();
			}
			Intent intent = new Intent(UyeOl.this,MainClass.class);
			startActivity(intent);
		}
	}
	
	public void GeriDon(View v)
	{
		Intent intent = new Intent(UyeOl.this,KullaniciGiris.class);
		startActivity(intent);
	}
	
	public boolean SifrelerAynimi()
	{
		Boolean sifrelerAynimi=false;
		String sifre1 = editSifre.getText().toString();
		String sifre2 = editSifreTekrar.getText().toString();
		
		if(sifre1.equals(sifre2))
			sifrelerAynimi = true;
		
		return sifrelerAynimi;
	}
	public long UyeEkle()
	{
		VeriTabanýKisiler veritabaný = new VeriTabanýKisiler(this);
		
		String ad = editAd.getText().toString();
		String soyad = editSoyad.getText().toString();
		String dogumTarih = editDogumTarih.getText().toString();
		String tel = editTel.getText().toString();
		String adres = editAdres.getText().toString();
		String ePosta = editEPosta.getText().toString();
		String sifre = editSifre.getText().toString();
		
		ContentValues satir = new ContentValues();
    	satir.put("Ad",ad);
    	satir.put("Soyad", soyad);
    	satir.put("DTarih",dogumTarih);
    	satir.put("Tel", tel);
    	satir.put("Adres", adres);
    	satir.put("EPosta", ePosta);
    	satir.put("Sifre",sifre);
    	satir.put("Admin",0);
    	
    	SQLiteDatabase db = veritabaný.getWritableDatabase();
    	long eklenenKelimeId = db.insert(VeriTabanýKisiler.TABLE_NAME, null, satir);
    	Toast.makeText(this, "Uye Basarý ile eklendi !", Toast.LENGTH_SHORT).show();
    	return eklenenKelimeId;
    	
    	
	}

}
