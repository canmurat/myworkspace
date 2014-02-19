package com.example.separatelistenerclass;

import android.view.View;
import android.view.View.OnClickListener;

public class Mesaj1 implements OnClickListener {
	
	SeparateExample sepexample =  new SeparateExample();
	
	String hello;
	public Mesaj1(String hello,SeparateExample sepexample)
		{
			this.hello = hello;
			this.sepexample = sepexample;
		}

	public void onClick(View v) {
			
			sepexample.setHello(hello);
		}
		
	}



	
	
