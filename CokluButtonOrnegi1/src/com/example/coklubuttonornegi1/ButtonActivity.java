package com.example.coklubuttonornegi1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ButtonActivity extends Activity {
	
	private String mButtonMessageTemplate;
	ButtonActivity haydar = new ButtonActivity();
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buttons);
		
		mButtonMessageTemplate = getString(R.string.button_message_template);		
	}
	public void showButtonText(View clickedButton){
		
		Button button = (Button) clickedButton;
		CharSequence text = button.getText();
		String message = String.format(mButtonMessageTemplate, text);
		showToast(message);
		
	}
	private void showToast(String message) {
		
		Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
		Toast.makeText(haydar, "hello", Toast.LENGTH_SHORT).show();
		
	}


}
