package com.example.anonymousclassornek;

import android.view.View;
import android.view.View.OnClickListener;

public class ColorRandomizer implements OnClickListener
{
	MainActivity mn = new MainActivity();
	String Text;
	public ColorRandomizer(String Text)
	{
		this.Text = Text;
	}
	
	public void onClick(View v) {
		
		mn.setHello("mERMetcan");
	}
	
}