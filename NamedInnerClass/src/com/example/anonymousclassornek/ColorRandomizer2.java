package com.example.anonymousclassornek;

import android.view.View;
import android.view.View.OnClickListener;

public class ColorRandomizer2 implements OnClickListener
{
	MainActivity mn2 = new MainActivity();
	String Text2;

	public ColorRandomizer2(String Text2)
	{
		this.Text2 = Text2;
	}

	public void onClick(View v) {
		
		mn2.setGoodbye("mehmetcan2");
	}
	
}