package com.example.imagebutton02;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ImageButton02 extends Activity {

	private String StringxmldenAldigim;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_button02);
		
		StringxmldenAldigim = getString(R.string.image_button_message_template);
		
	}
	public void ResimbutonuBilgileriniGoster1(View v)
	{
		resimbutonGoster(R.string.image_button_1_image);
		
	}
	public void ResimbutonuBilgileriniGoster2(View v)
	{
		resimbutonGoster(R.string.image_button_2_image);
	}
	public void ResimbutonuBilgileriniGoster3(View v)
	{
		resimbutonGoster(R.string.image_button_3_image);
	}

	public void resimbutonGoster(int imageId)
	{
		String mesaj = getString(imageId);
		String message = String.format(StringxmldenAldigim, mesaj);
		makeToast(message);
	}
	
	public void makeToast(String string)
	{
		Toast toast = Toast.makeText(this,string,Toast.LENGTH_SHORT);
		toast.show();
	}

}
