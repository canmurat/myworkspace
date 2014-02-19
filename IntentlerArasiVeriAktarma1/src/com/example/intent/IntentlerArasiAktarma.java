package com.example.intent;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.example.intentlerarasiveriaktarma1.R;

public class IntentlerArasiAktarma extends Activity {

	public static String deger1;
	public static String deger = "dd";
	EditText edit;
	 public ArrayList<String> liste = new ArrayList<String>();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intentler_arasi_aktarma);
		
		edit = (EditText)findViewById(R.id.editText1);	
		
	}
	public void Aktar(View v)
	{

		
		deger1 = edit.getText().toString();
		liste.add(deger1);
		
		Bundle extra = new Bundle();
		extra.putString(deger,deger1);
		
		extra.putStringArrayList("common",liste);
	
		Intent intent = new Intent(IntentlerArasiAktarma.this,DigerIntent.class);
		intent.putExtras(extra);
//		intent.putExtra("deger1", deger1);  
//		deger1 = edit.getText().toString();
		startActivity(intent);
		
	}

	

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.intentler_arasi_aktarma, menu);
		return true;
	}

}
