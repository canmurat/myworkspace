package com.example.imagebutton01;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ImageActivity extends Activity {

	private String resimMesaji;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_activity);
		
		resimMesaji = getString(R.string.image_button_message_template);
		
	}
	
	public void showImageButton1Info(View ClickedImageButton ){
		
		showImageButton(R.string.image_button_1_image);
		
	}

	private void showImageButton(int imageId ) {
		
		String girdi = getString(imageId);
		String mesaj = String.format(resimMesaji,girdi);
		
		Toast.makeText(this, mesaj, Toast.LENGTH_SHORT).show();
		
	}
	
	

}
