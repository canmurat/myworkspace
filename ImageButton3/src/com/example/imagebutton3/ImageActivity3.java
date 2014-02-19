package com.example.imagebutton3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ImageActivity3 extends Activity {
	
	private String string1;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_activity3);
		
		string1  = getString(R.string.button_message_template);
	}

	public void Gosterim(View v){
		
		Button button = (Button)v;
		CharSequence butonMesaj = button.getText();
		String mesaj = String.format(string1,butonMesaj);
		MakeToast(mesaj);
	}
	public void MakeToast(String string)
	{
	
		Toast toast = Toast.makeText(this, string, Toast.LENGTH_SHORT);
		toast.show();
	}
	


}
