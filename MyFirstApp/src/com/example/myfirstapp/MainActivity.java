package com.example.myfirstapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final TextView textview = (TextView)findViewById(R.id.textView2);
    	final EditText textedit = (EditText)findViewById(R.id.textView1);
        Button Buton = (Button) findViewById(R.id.button1);
        
        Buton.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View v) {
				textview.setText(textedit.getText());
				
			}
		});
	
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
