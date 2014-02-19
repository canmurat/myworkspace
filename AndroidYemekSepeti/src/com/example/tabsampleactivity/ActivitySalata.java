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
	final List<Integer> salataFiyatlarýListesi = new ArrayList<Integer>();

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_salata);

		//tum degerler tutuluyor.
		SalatalarTumListe = new ArrayList<CharSequence>();
		SalatalarTumListe.add("Coban Salata - 10 TL");
		SalatalarTumListe.add("Meyve Salatasý - 10 TL");
		SalatalarTumListe.add("Yogurtlu Salata - 10 TL");
		SalatalarTumListe.add("Rus Salatasý - 10 TL");
		SalatalarTumListe.add("Börülce Salatasý - 10 TL");
		// salata isimleri
		salatalarListesi.add(0, "Coban Salatasý");
		salatalarListesi.add(1, "Meyve Salatasý");
		salatalarListesi.add(2, "Yogurtlu Salata");
		salatalarListesi.add(3, "Rus Salatasý");
		salatalarListesi.add(4, "Börülce Salatasý");
		//fiyatlarý
		salataFiyatlarýListesi.add(10);
		salataFiyatlarýListesi.add(10);
		salataFiyatlarýListesi.add(10);
		salataFiyatlarýListesi.add(10);
		salataFiyatlarýListesi.add(10);
		//adapter'a deger aktarýlýyor.
		
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
					"Adet miktarý en az 1 dir !", Toast.LENGTH_SHORT);
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
		// salata ve fiyatýný spinner'dan secilen degere gore al.
		int salataIndex = spinnersalata.getSelectedItemPosition();
		secilenSalata = (String) salatalarListesi.get(salataIndex);
		secilenSalataFiyati = salataFiyatlarýListesi.get(salataIndex);

		Intent intent = new Intent(ActivitySalata.this, SiparisVer.class);
		Bundle newActivityInfo = new Bundle();
		// degerleri aktar.
		newActivityInfo.putString("secilenSalata", secilenSalata);
		newActivityInfo.putInt("secilenSalataFiyati", secilenSalataFiyati);
		newActivityInfo.putInt("secilenSalataAdeti", secilenSalataAdeti);
		newActivityInfo.putInt("butonDegeri", butonDegeri);
		intent.putExtras(newActivityInfo);

		Toast toast = Toast.makeText(ActivitySalata.this,
				"Seçilen Yemekler Sepete eklendi !", Toast.LENGTH_SHORT);

		toast.show();

		startActivity(intent);

	}
//	//Geri Tusuna basýldýgýnda uygulama sonlanmasý amaclý
//	public boolean onKeyDown(int keyCode, KeyEvent event)
//	{
//	    if ((keyCode == KeyEvent.KEYCODE_BACK))
//	    {
//	        finish();
//	    }
//	    return super.onKeyDown(keyCode, event);
//	}

}
