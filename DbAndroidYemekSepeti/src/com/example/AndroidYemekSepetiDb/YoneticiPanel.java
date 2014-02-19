package com.example.AndroidYemekSepetiDb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class YoneticiPanel extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yonetici_panel);
	}

	public void btnSiparisler(View v)
	{
		Intent intent = new Intent(YoneticiPanel.this, PanelSiparisler.class);
		startActivity(intent);

	}
	
	public void btnYemekler(View v) {
		Intent intent = new Intent(YoneticiPanel.this, PanelYemekler.class);
		startActivity(intent);
	}

	public void btnIcecekler(View v) {
		Intent intent = new Intent(YoneticiPanel.this, PanelIcecekler.class);
		startActivity(intent);

	}

	public void btnSalatalar(View v) {
		Intent intent = new Intent(YoneticiPanel.this,PanelSalatalar.class);
		startActivity(intent);

	}

	public void btnTatlilar(View v) {
		Intent intent = new Intent(YoneticiPanel.this,PanelTatlilar.class);
		startActivity(intent);

	}
	public void btnCikis(View v)
	{
		Intent intent = new Intent(YoneticiPanel.this,KullaniciGiris.class);
		startActivity(intent);
	}

}
