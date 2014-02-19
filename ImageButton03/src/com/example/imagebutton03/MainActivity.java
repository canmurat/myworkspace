package com.example.imagebutton03;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

	private RadioGroup radioGroupId;
	private RadioButton radioGenderButton;
	private Button button;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
	}
	
	public void addOnClickListener(){
		
		radioGroupId = (RadioGroup)findViewById(R.id.radioGenderGroup);
		button =(Button)findViewById(R.id.button);
		button.setTextColor(Color.RED);
		radioGroupId.setBackgroundColor(Color.BLUE);
		button.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				int selectedOption = radioGroupId.getCheckedRadioButtonId();
				
				radioGenderButton = (RadioButton)findViewById(selectedOption);
				
				Toast.makeText(MainActivity.this, radioGenderButton.getText(), Toast.LENGTH_SHORT).show();
				
				
			}
		});
	}
	


}
