package com.example.androne;

import android.app.Activity;
import android.os.Bundle;
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
        
        Button buton = (Button) (findViewById(R.id.button1));
        final TextView tview = (TextView) (findViewById(R.id.textView1));
        final EditText etext = (EditText) (findViewById(R.id.editText1));
        
        buton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				tview.setText(etext.getText());
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
