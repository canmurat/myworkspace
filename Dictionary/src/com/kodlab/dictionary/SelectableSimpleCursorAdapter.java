package com.kodlab.dictionary;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class SelectableSimpleCursorAdapter extends SimpleCursorAdapter {

	private Activity context;
	private Cursor cursor;
	private int selectedPosition = -1; 
	
	public SelectableSimpleCursorAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to, int flags) {
		
		super(context, layout, c, from, to, flags);
		
		this.context = (Activity) context;
		this.cursor = c;

	}	

	public int getSelectedPosition() {
		return selectedPosition;
	}

	public void setSelectedPosition(int selectedPosition) {
		this.selectedPosition = selectedPosition;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View row=convertView;
		
		if (row==null) {
			LayoutInflater inflater=context.getLayoutInflater();
			row=inflater.inflate(R.layout.list_item, null);
		}
		
		if(selectedPosition == position){
        	row.setBackgroundColor(Color.RED);
        }else{
        	row.setBackgroundColor(Color.WHITE);
        }
		
		cursor.moveToPosition(position);
		
		TextView adListItemTextView = (TextView)row.findViewById(R.id.adListItemTextView);
		int adIndex = cursor.getColumnIndex(DictionaryContract.Kelime.COLUMN_AD);
		adListItemTextView.setText(cursor.getString(adIndex));
		
		TextView aciklamaListItemTextView = (TextView)row.findViewById(R.id.aciklamaListItemTextView);
		int aciklamaIndex = cursor.getColumnIndex(DictionaryContract.Kelime.COLUMN_ACIKLAMA);
		aciklamaListItemTextView.setText(cursor.getString(aciklamaIndex));
		
		return row;
	}

}
