package com.example.bundleornegi;

import android.app.Activity;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

public class BundleOrnegi extends Activity {

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_bundle_ornegi);

		Button tikla = (Button) findViewById(R.id.tikla);

		final Intent intent = new Intent(BundleOrnegi.this, ac2.class);

		// ac2 activitysine gidecek intenti burda tan�ml�yorum. ��nk� i�erisine
		// bundle verisini de yerle�tirece�iz.

		final Bundle bundle = new Bundle();

		// bundle � olu�turuyoruz

		tikla.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				// TODO Auto-generated method stub

				bundle.putString("veri", "Deneme");

				// bundledaki veri adl� de�i�kene de�er aktar�ld�.

				intent.putExtras(bundle);

				// intent adl� nesnemize bundle � da ekliyoruz.

			}

		});

		Button git = (Button) findViewById(R.id.git);

		git.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				// TODO Auto-generated method stub

				// Activity 2ye giden kodlar yaz�lacak.

				startActivity(intent);

			}

		});

	}
}
