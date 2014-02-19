package com.example.AndroidYemekSepetiDb;

import java.util.ArrayList;
import java.util.List;

import com.example.VeriTabani.VeriTaban�Salatalar;
import com.example.VeriTabani.VeriTaban�Yiyecekler;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class ActivitySalata extends Activity {

	Spinner spinnersalata;
	Button btnsalatasiparis;
	Button btnsalatasepet;
	TextView adettextsalata;
	EditText edittextsalata;

	int butonDegeri;
	List<CharSequence> SalatalarTumListe;

	public static String secilenSalata;
	public static int secilenSalataFiyati;
	public static int secilenSalataAdeti;

	final List<CharSequence> salatalarListesi = new ArrayList<CharSequence>();
	final List<Integer> salataFiyatlar�Listesi = new ArrayList<Integer>();

	VeriTaban�Salatalar veritaban�;
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_salata);

		veritaban� = new VeriTaban�Salatalar(this);
		SalatalarTumListe = new ArrayList<CharSequence>();
		SQLiteDatabase db = veritaban�.getReadableDatabase(); 
		int sayac=0;
		// Veritaban�n�n Salatalar tablosundaki t�m veriler Spinner'a eklenmek uzere Listeye at�l�yor.
		Cursor cur = db.rawQuery("SELECT * FROM " + VeriTaban�Salatalar.TABLE_NAME, null);
		if (cur != null) {
		    if (cur.moveToFirst()) {
		        do {
		        	String salatan�nAdi = cur.getString(cur.getColumnIndex("Ad"));
		        	int salatan�nFiyat� = cur.getInt(cur.getColumnIndex("Fiyat"));
		        	salatalarListesi.add(sayac,salatan�nAdi);
		        	SalatalarTumListe.add(salatan�nAdi + " " +salatan�nFiyat� + " TL");
		        	salataFiyatlar�Listesi.add(salatan�nFiyat�);
		        	sayac++;
		        } while (cur.moveToNext());
		    }
		}
		sayac=0;
		ArrayAdapter<CharSequence> adapter3 = new ArrayAdapter<CharSequence>(
				this, android.R.layout.simple_list_item_1, SalatalarTumListe);

		spinnersalata = (Spinner) findViewById(R.id.spinnersalata);
		btnsalatasiparis = (Button) findViewById(R.id.btnsalatasiparis);
		btnsalatasepet = (Button) findViewById(R.id.btnsalatasepet);
		adettextsalata = (TextView) findViewById(R.id.adettextsalata);
		edittextsalata = (EditText) findViewById(R.id.edittextsalata);

		spinnersalata.setAdapter(adapter3);

	}
	// yemek sepetine git.
	public void yemekSepeti(View v) {
		Intent intent = new Intent(ActivitySalata.this, SiparisVer.class);
		startActivity(intent);

	}

	public void btnSiparis(View v) {
		// hangi Tab'dan geldigini anlayabilmek icin buto degerleri belirlendi.
		butonDegeri = 3;

		if (edittextsalata.getText().toString() == null
				|| edittextsalata.getText().toString().trim().equals("")
				|| edittextsalata.getText().toString().trim().isEmpty()) {
			secilenSalataAdeti = 1;
			Toast toast = Toast.makeText(ActivitySalata.this,
					"Adet miktar� en az 1 dir !", Toast.LENGTH_SHORT);
			toast.show();
		} else if (edittextsalata.getText().toString() == "0") {
			Toast toast = Toast.makeText(ActivitySalata.this,
					"En az 1 degerini girmeniz gerekmektedir. !",
					Toast.LENGTH_SHORT);
			toast.show();
		} else {
			String no = edittextsalata.getText().toString();
			secilenSalataAdeti = Integer.parseInt(no);
		}
		// salata ve fiyat�n� spinner'dan secilen degere gore al.
		int salataIndex = spinnersalata.getSelectedItemPosition();
		secilenSalata = (String) salatalarListesi.get(salataIndex);
		secilenSalataFiyati = salataFiyatlar�Listesi.get(salataIndex);

		Intent intent = new Intent(ActivitySalata.this, SiparisVer.class);
		Bundle newActivityInfo = new Bundle();
		// degerleri aktar.
		newActivityInfo.putString("secilenSalata", secilenSalata);
		newActivityInfo.putInt("secilenSalataFiyati", secilenSalataFiyati);
		newActivityInfo.putInt("secilenSalataAdeti", secilenSalataAdeti);
		newActivityInfo.putInt("butonDegeri", butonDegeri);
		intent.putExtras(newActivityInfo);

		Toast toast = Toast.makeText(ActivitySalata.this,
				"Se�ilen Yemekler Sepete eklendi !", Toast.LENGTH_SHORT);

		toast.show();

		startActivity(intent);

	}
	public void btnCikis(View v)
	{
		Intent intent = new Intent(ActivitySalata.this, KullaniciGiris.class);
		startActivity(intent);
	}
}
