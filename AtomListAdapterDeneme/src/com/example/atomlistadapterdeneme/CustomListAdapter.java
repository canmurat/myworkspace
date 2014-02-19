package com.example.atomlistadapterdeneme;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.atomlistadapterdeneme.PersonalInfo;

public class CustomListAdapter extends ArrayAdapter {
	private Context appContext = null;
	private ArrayList items = null;

	public CustomListAdapter(Context context, int textViewResourceId,
			ArrayList items) {
		super(context, textViewResourceId, items);
		// TODO Auto-generated constructor stub
		this.appContext = context;
		this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) appContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.list_item, null);
		}
		PersonalInfo o = (PersonalInfo) items.get(position);
		if (o != null) {
			TextView name = (TextView) v.findViewById(R.id.lst_item_Name);
			TextView mobile = (TextView) v.findViewById(R.id.lst_item_Mobile);
			Button btnDelete = (Button) v.findViewById(R.id.lst_item_Delete);
			// Section: 1. set tag for tracking row number in list
			btnDelete.setTag(position);
			// Section 2. click delete button listener
			// add code here to remove rows
			btnDelete.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					// Section 3. remove rows in list
					String pos = v.getTag().toString();
					int _position = Integer.parseInt(pos);
					items.remove(_position);
					notifyDataSetChanged();
					// just replace the 3
					// add a command to remove record from sqlite and
					// call notifyDataSetChanged(); thats all
				}
			});
			if (name != null)
				name.setText(o.GetName());
			if (mobile != null)
				mobile.setText(o.GetMobile());
		}
		return v;
	}
}