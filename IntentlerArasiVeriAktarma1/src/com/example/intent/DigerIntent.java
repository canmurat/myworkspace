package com.example.intent;

import java.util.ArrayList;

import com.example.intentlerarasiveriaktarma1.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class DigerIntent extends Activity{

	String deger3;
	TextView textview;
	TextView textview2;
	static String degerLove;
	ArrayList<String> liste;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.digerxml);

		Bundle a = getIntent().getExtras();
		
		
		textview = (TextView)findViewById(R.id.textView1);
		textview2 = (TextView)findViewById(R.id.textView2);
		
		deger3 =a.getString(IntentlerArasiAktarma.deger);
		liste = a.getStringArrayList("common");
		
		
		for (int i = 0; i < 5 ; i++) {
			
			textview2.setText("i= "+i);
			
		}
	
		for (int i = 0; i < liste.size(); i++) {
			
			degerLove = degerLove + liste.get(i);
			textview.setText(degerLove + "\n" + "1.lÝSTEnin size'ý "+liste.size()+" Liste = " +liste.get(i) +"\n");
			
		}
		for (int i = 0; i < 4; i++) {
			textview2.append("Text");
		}
		Toast.makeText(DigerIntent.this, "Tamamdýr", Toast.LENGTH_SHORT).show();
		
		
		
	
	}	
	public void geriDon(View v)
	{
		Intent intent = new Intent(DigerIntent.this,IntentlerArasiAktarma.class);
		startActivity(intent);
	}

}
