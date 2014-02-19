package com.example.atomlistadapterdeneme;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.ListView;
import com.example.atomlistadapterdeneme.PersonalInfo;

public class ListActivity1 extends ListActivity {
	/** Called when the activity is first created. */
	ArrayList<PersonalInfo> newList = null;

	SimpleAdapter adpt = null;

	private Button btnSave = null;

	private EditText txtName = null;
	private EditText txtMobile = null;
	private CustomListAdapter newAdpt = null;
	private int i = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_item);
		newList = new ArrayList<PersonalInfo>();

		newAdpt = new CustomListAdapter(this, R.layout.list_item, newList);
		setListAdapter(this.newAdpt);

		txtName = (EditText) findViewById(R.id.lst_item_Name);
		txtMobile = (EditText) findViewById(R.id.lst_item_Mobile);
		btnSave = (Button) findViewById(R.id.lst_item_Delete);

		btnSave.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				newList = new ArrayList<PersonalInfo>();
				PersonalInfo info = new PersonalInfo();
				info.SetName(txtName.getText().toString());
				info.SetMobile(txtMobile.getText().toString());
				newList.add(info);
				// Thread.sleep(2000);

				if (newList != null && newList.size() > 0) {
					newAdpt.notifyDataSetChanged();
					newAdpt.add(newList.get(0));
					i++;
					// for(int i=0;i<newList.size();i++)
					// newAdpt.add(newList.get(i));
				}

				newAdpt.notifyDataSetChanged();

			}
		});
	}
}