package com.example.atomlistadapterdeneme2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class CreatList extends Activity {
	ArrayList<String> list;
	HashMap<String, String> map;
	SimpleAdapter adapter;
	ListView lv;
	String[] from;
	int[] to;
	List<HashMap<String, String>> fillMaps;
	List<HashMap<TextView, TextView>> texter;
	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_item);
		lv = (ListView) findViewById(R.id.listview);
		list = new ArrayList<String>(); // list of product id
		list.add("pr100");
		list.add("pr10");
		list.add("pr1000");
		list.add("px100");

		map = new HashMap<String, String>();
		map.put("pr100", "300");
		map.put("pr10", "30");
		map.put("pr1000", "400");
		map.put("px100", "230");

		// create the grid item mapping
		String[] from = new String[] { "rowid", "col_1", "col_2", "col_3" };
		int[] to = new int[] { R.id.item1, R.id.item2, R.id.item3, R.id.item4 };

		// prepare the list of all records
		
		fillMaps = new ArrayList<HashMap<String, String>>();
		texter = new ArrayList<HashMap<TextView, TextView>>();
		for (int i = 1; i <= list.size(); i++) {
			HashMap<String, String> map1 = new HashMap<String, String>();
			map1.put("rowid", "" + i);
			map1.put("col_1", list.get(i - 1));
			map1.put("col_2", map.get(list.get(i - 1)));
			map1.put("col_3", "X");
			
			
			fillMaps.add(map1);
		}

		// fill in the grid_item layout
		adapter = new SimpleAdapter(this, fillMaps, R.layout.grid_item, from,
				to);
		lv.setAdapter(adapter);
	}

	public void DeleteRow(View v) {
		
		LinearLayout llMain = (LinearLayout) v.getParent();
		TextView row = (TextView) llMain.getChildAt(0);
		String row_no = row.getText().toString();
		int row_no_int = Integer.parseInt(row_no);// get row number of deleted
													// item from list
		list.remove(row_no_int - 1 );
		fillMaps.remove(row_no_int - 1);
		adapter.notifyDataSetChanged();

	}

	public void OnAdding() {
		list.add("pz11");// adding new product id into list
		map.put("pz11", "30");// putting price of product id
		int size = list.size();
		for (int i = 1; i <= list.size(); i++) {
			HashMap<String, String> map1 = new HashMap<String, String>();
			map1.put("rowid", "" + i);
			map1.put("col_1", list.get(i - 1));
			map1.put("col_2", map.get(list.get(i - 1)));
			map1.put("col_3", "X");
			fillMaps.add(map1);

			adapter.notifyDataSetChanged();// refreshing adapter
		}

	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

	}

}