package com.example.tabsampleactivity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
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

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_salata);

		//tum degerler tutuluyor.
		SalatalarTumListe = new ArrayList<CharSequence>();
		SalatalarTumListe.add("Coban Salata - 10 TL");
		SalatalarTumListe.add("Meyve Salatas� - 10 TL");
		SalatalarTumListe.add("Yogurtlu Salata - 10 TL");
		SalatalarTumListe.add("Rus Salatas� - 10 TL");
		SalatalarTumListe.add("B�r�lce Salatas� - 10 TL");
		// salata isimleri
		salatalarListesi.add(0, "Coban Salatas�");
		salatalarListesi.add(1, "Meyve Salatas�");
		salatalarListesi.add(2, "Yogurtlu Salata");
		salatalarListesi.add(3, "Rus Salatas�");
		salatalarListesi.add(4, "B�r�lce Salatas�");
		//fiyatlar�
		salataFiyatlar�Listesi.add(10);
		salataFiyatlar�Listesi.add(10);
		salataFiyatlar�Listesi.add(10);
		salataFiyatlar�Listesi.add(10);
		salataFiyatlar�Listesi.add(10);
		//adapter'a deger aktar�l�yor.
		
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
//	//Geri Tusuna bas�ld�g�nda uygulama sonlanmas� amacl�
//	public boolean onKeyDown(int keyCode, KeyEvent event)
//	{
//	    if ((keyCode == KeyEvent.KEYCODE_BACK))
//	    {
//	        finish();
//	    }
//	    return super.onKeyDown(keyCode, event);
//	}

}
