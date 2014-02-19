package com.example.hesapmakinesi;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Button;

@SuppressWarnings("unused")
public class HesapMakinem extends Activity implements OnClickListener {
	
	private float ilkDeger = 0;
	private double sonuc = 0;
	private String islem;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hesap_makinem);
		
		final TextView textview = (TextView)findViewById(R.id.textView1);
		Button btn1 = (Button)findViewById(R.id.btn1);
		Button btn2 = (Button)findViewById(R.id.btn2);
		Button btn3 = (Button)findViewById(R.id.btn3);
		Button btn4 = (Button)findViewById(R.id.btn4);
		Button btn5 = (Button)findViewById(R.id.btn5);
		Button btn6 = (Button)findViewById(R.id.btn6);
		Button btn7 = (Button)findViewById(R.id.btn7);
		Button btn8 = (Button)findViewById(R.id.btn8);
		Button btn9 = (Button)findViewById(R.id.btn9);
		Button btn0 = (Button)findViewById(R.id.btn0);
		Button btntopla = (Button)findViewById(R.id.btntopla);
		Button btncikar = (Button)findViewById(R.id.btncikar);
		Button btncarp = (Button)findViewById(R.id.btncarp);
		Button btnbol = (Button)findViewById(R.id.btnbol);
	    Button btnsil = (Button)findViewById(R.id.btnsil);
		Button btnesittir = (Button)findViewById(R.id.btnesittir);
		
		btn1.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) {
				textview.setText(textview.getText() + "1");
				
			}
		});
		
		btn2.setOnClickListener(new View.OnClickListener()
		{
			
			public void onClick(View v) {
				textview.setText(textview.getText() + "2");
				
			}
		});
		
		btn3.setOnClickListener(new View.OnClickListener()
		{
			
			public void onClick(View v) {
				textview.setText(textview.getText() + "3");
				
			}
		});
		btn4.setOnClickListener(new View.OnClickListener()
		{
			
			public void onClick(View v) {
				textview.setText(textview.getText() + "4");
				
			}
		});
		btn5.setOnClickListener(new View.OnClickListener()
		{
			
			public void onClick(View v) {
				textview.setText(textview.getText() + "5");
				
			}
		});
		btn6.setOnClickListener(new View.OnClickListener()
		{
			
			public void onClick(View v) {
				textview.setText(textview.getText() + "6");
				
			}
		});
		btn7.setOnClickListener(new View.OnClickListener()
		{
			
			public void onClick(View v) {
				textview.setText(textview.getText() + "7");
				
			}
		});
		btn8.setOnClickListener(new View.OnClickListener()
		{
			
			public void onClick(View v) {
				textview.setText(textview.getText() + "8");
				
			}
		});
		btn9.setOnClickListener(new View.OnClickListener()
		{
			
			public void onClick(View v) {
				textview.setText(textview.getText() + "9");
				
			}
		});
		btn0.setOnClickListener(new View.OnClickListener()
		{
			
			public void onClick(View v) {
				textview.setText(textview.getText() + "0");
				
			}
		});
		
		try{
			btnsil.setOnClickListener(new View.OnClickListener()
			{

				@Override
				public void onClick(View v) {
				ilkDeger=0;
				sonuc=0;
				textview.setText("");
					
				}
			});
				
			}
		catch(Exception ex) 
		{
		
		}
		try{
			btntopla.setOnClickListener(new View.OnClickListener()
			{

				@Override
				public void onClick(View v) {
				if(!textview.getText().toString().equals(""))
				{
					ilkDeger = Float.parseFloat(textview.getText().toString());
					textview.setText("");
					islem = "Toplama";
					
				}
					
				}
			});
				
			}
		catch(Exception ex) 
		{
		
		}
		
		try{
			btncikar.setOnClickListener(new View.OnClickListener()
			{

				@Override
				public void onClick(View v) {
				if(!textview.getText().toString().equals(""))
				{
					ilkDeger = Float.parseFloat(textview.getText().toString());
					textview.setText("");
					islem = "Cikarma";
					
				}
					
				}
			});
				
			}
		catch(Exception ex) 
		{
		
		}
		try{
			btntopla.setOnClickListener(new View.OnClickListener()
			{

				@Override
				public void onClick(View v) {
				if(!textview.getText().toString().equals(""))
				{
					ilkDeger = Float.parseFloat(textview.getText().toString());
					textview.setText("");
					islem = "Toplama";
					
				}
					
				}
			});
				
			}
		catch(Exception ex) 
		{
		
		}
		try{
			btncarp.setOnClickListener(new View.OnClickListener()
			{

				@Override
				public void onClick(View v) {
				if(!textview.getText().toString().equals(""))
				{
					ilkDeger = Float.parseFloat(textview.getText().toString());
					textview.setText("");
					islem = "Carpma";
					
				}
					
				}
			});
				
			}
		catch(Exception ex) 
		{
		
		}
		try{
			btnbol.setOnClickListener(new View.OnClickListener()
			{

				@Override
				public void onClick(View v) {
				if(!textview.getText().toString().equals(""))
				{
					ilkDeger = Float.parseFloat(textview.getText().toString());
					textview.setText("");
					islem = "Bolme";
					
				}
					
				}
			});
				
			}
		catch(Exception ex) 
		{
		
		}
		
		
		
		btnesittir.setOnClickListener(new View.OnClickListener()
		{

			
			public void onClick(View v) {
				
				if(!textview.getText().toString().equals("") && ! String.valueOf(ilkDeger).equals("0"))
				{
					if (islem.equals("Toplama"))
					{
						sonuc = ilkDeger + Float.parseFloat(textview.getText().toString());
					}
					else if(islem.equals("Cikarma"))
					{
						sonuc = ilkDeger - Float.parseFloat(textview.getText().toString());
					}
					else if(islem.equals("Carpma"))
					{
						sonuc = ilkDeger * Float.parseFloat(textview.getText().toString());
					}
					else if(islem.equals("Bolme"))
					{
						sonuc = ilkDeger / Float.parseFloat(textview.getText().toString());
					}
					
					textview.setText(String.valueOf(sonuc));
					ilkDeger = 0 ;
					sonuc = 0;
					
					
				}
				else
				{
					textview.setText("Deger giriniz");
				}
				if(R.id.btnsil == v.getId())
				{
					ilkDeger  =0 ;
					sonuc = 0;
					textview.setText("");
					
				}
			}
			
		});
		
				
			
		
				
				
				
		
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

	

}
