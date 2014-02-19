package com.example.buttongroups1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	Button buton1,buton2;
	RadioButton rdbuton1,rdbuton2;
	CheckBox checkbox1,checkbox2;
	ToggleButton tgbuton1,tgbuton2;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		buton1 = (Button)findViewById(R.id.button1);
		buton2 = (Button)findViewById(R.id.button2);
		rdbuton1 = (RadioButton)findViewById(R.id.radioButton1);
		rdbuton2 = (RadioButton)findViewById(R.id.radioButton2);
		checkbox1 = (CheckBox)findViewById(R.id.checkBox1);
		checkbox2 = (CheckBox)findViewById(R.id.checkBox2);
		tgbuton1 = (ToggleButton)findViewById(R.id.toggleButton1);
		tgbuton2 = (ToggleButton)findViewById(R.id.toggleButton2);
		
		RelativeLayout rl = (RelativeLayout)findViewById(R.id.relativeLayout);
		rl.setBackgroundColor(Color.LTGRAY);
		
		buton1.setBackgroundColor(Color.GREEN);
		buton2.setBackgroundColor(Color.BLUE);
		rdbuton1.setBackgroundColor(Color.CYAN);
		rdbuton2.setBackgroundColor(Color.GRAY);
		checkbox1.setBackgroundColor(Color.BLUE);
		checkbox2.setBackgroundColor(Color.MAGENTA);
		tgbuton1.setBackgroundColor(Color.YELLOW);
		tgbuton2.setBackgroundColor(Color.GREEN);
		
		
		
		
	}
	
	public void makeToast(String mesaj)
	{
		Toast toast = Toast.makeText(this, mesaj, Toast.LENGTH_SHORT);
		toast.show();
	}
	public void Kontrol(View v){
			
		if(rdbuton1.isChecked() && checkbox1.isChecked() && tgbuton1.isChecked() )
		{
			
			startActivity(new Intent("android.intent.action.DIGER"));
			
		}
		else
		{
			makeToast("Wrong way ! ");
		}
	}
	public void YanlisYol(View v )
	{
		makeToast("Wrong way ! ");
	}
	

	


}
