package com.example.AndroidYemekSepetiDb;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
@SuppressWarnings("deprecation")
public class MainClass extends TabActivity 
{
	// Tab Olusturabilmek icin TabActivity Sýnýfý extends edildi.
 
    TabHost tabHost;
   
	public void onCreate(Bundle savedInstanceState)
     {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	
		//Ana Tab yapýsý Olusturuldu.
    	tabHost=(TabHost)findViewById(android.R.id.tabhost);
    	
    	
    	//Yemek , icecek , salata ve tatli icin tablar ayrý ayrý olusturuldu
        TabSpec spec1=tabHost.newTabSpec("Yemek");
        spec1.setIndicator("Yemek");
        Intent intentYemek = new Intent(MainClass.this,ActivityYemek.class);
        spec1.setContent(intentYemek);
       
      
      
        TabSpec spec2=tabHost.newTabSpec("Icecek");
        spec2.setIndicator("Icecek");
        Intent intentIcecek = new Intent(MainClass.this,ActivityIcecek.class);
        spec2.setContent(intentIcecek);
        
      
      
        TabSpec spec3=tabHost.newTabSpec("Salata");
        spec3.setIndicator("Salata");
        Intent intentSalata = new Intent(MainClass.this,ActivitySalata.class);
        spec3.setContent(intentSalata);
        
        
        TabSpec spec4=tabHost.newTabSpec("Tatli");
        spec4.setIndicator("Tatli");
        Intent intentTatli = new Intent(MainClass.this,ActivityTatli.class);
        spec4.setContent(intentTatli);
       
        //Olusturulan bu ayrý tablar ana tab'a eklendi.
        tabHost.addTab(spec1);
        tabHost.addTab(spec2);
        tabHost.addTab(spec3);
        tabHost.addTab(spec4);
      
       
        
        

         
		
		
	}	
}