
package com.example.separatelistenerclass;

import android.view.View;
import android.view.View.OnClickListener;


public class Mesaj2 implements OnClickListener{
	
	SeparateExample sepexample2 =  new SeparateExample();
	
		String goodbye;
		public Mesaj2(String goodbye,SeparateExample sepexample2)
		{
			this.goodbye = goodbye;
			this.sepexample2 = sepexample2;
		}
		public void onClick(View v) {
			
			sepexample2.setGoodbye(goodbye);
		}
	}

