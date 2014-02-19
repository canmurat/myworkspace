package com.example.AndroidYemekSepetiDb;

import com.example.VeriTabani.VeriTabanıSiparisler;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

@SuppressLint("NewApi")
public class SimpleCursorAdapterClass2 extends SimpleCursorAdapter {

	private Activity context;
	private Cursor cursor;
	private int selectedPosition = -1; 
	
	// SimpleCursorAdapter sınıfından extends edilerek Yapılandırıcının ici dolduruldu
	// Yapılandırıcıda , Icerik Bilgisi , hangi Layout'un kullanılacagı , VeriTabanı sorgulari icin 
	// Tabloyu doldururken hangi cursor kullanılacagı , ve from-to ile hangi degerlerin Layout'da
	// hangi degerlere karsı geldigini soyluyoruz.
	public SimpleCursorAdapterClass2(Context context, int layout, Cursor c,
			String[] from, int[] to, int flags) {
		
		super(context, layout, c, from, to, flags);
		
		this.context = (Activity) context;
		this.cursor = c;

	}	
	// Adapter'da seçilmiş olan pozisyonu dondurur.
	public int getSelectedPosition() {
		return selectedPosition;
	}
	// Adapter icerisine veri yerlestirirken kullanılan yordam
	public void setSelectedPosition(int selectedPosition) {
		this.selectedPosition = selectedPosition;
		notifyDataSetChanged();
	}

	/*Simple Cursor Adapter sınıfındaki getView methodu OverRide edildi.
	 * */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View row=convertView;
		
		if (row==null) {
			LayoutInflater inflater=context.getLayoutInflater();
			row=inflater.inflate(R.layout.listtextviews2, null);
		}
		
		if(selectedPosition == position){
        	row.setBackgroundColor(Color.RED);
        }else{
        	row.setBackgroundColor(Color.WHITE);
        }
		
		cursor.moveToPosition(position);
		
//		TextView SiparisIdListItemTextView = (TextView)row.findViewById(R.id.SiparisIdListItemTextView);
//		int SiparisIdIndex = cursor.getColumnIndex(VeriTabanıSiparisler.SIPARIS_ID);
//		SiparisIdListItemTextView.setText(cursor.getString(SiparisIdIndex));
		
//		TextView KullaniciIdListItemTextView = (TextView)row.findViewById(R.id.KullaniciIdListItemTextView);
//		int KullaniciIdIndex = cursor.getColumnIndex(VeriTabanıSiparisler.KULLANICI_ID);
//		KullaniciIdListItemTextView.setText(cursor.getString(KullaniciIdIndex));
//		
//		
//		TextView YemekListItemTextView = (TextView)row.findViewById(R.id.YemekListItemTextView);
//		int YemekIndex = cursor.getColumnIndex(VeriTabanıSiparisler.YEMEKLER);
//		YemekListItemTextView.setText(cursor.getString(YemekIndex));
//		
//		TextView IcecekListItemTextView = (TextView)row.findViewById(R.id.IcecekListItemTextView);
//		int IcecekIndex = cursor.getColumnIndex(VeriTabanıSiparisler.ICECEKLER);
//		IcecekListItemTextView.setText(cursor.getString(IcecekIndex));
//		
//		TextView SalataListItemTextView = (TextView)row.findViewById(R.id.SalataListItemTextView);
//		int SalataIndex = cursor.getColumnIndex(VeriTabanıSiparisler.SALATALAR);
//		SalataListItemTextView.setText(cursor.getString(SalataIndex));
//		
//		TextView TatliListItemTextView = (TextView)row.findViewById(R.id.TatliListItemTextView);
//		int TatliIndex = cursor.getColumnIndex(VeriTabanıSiparisler.TATLILAR);
//		TatliListItemTextView.setText(cursor.getString(TatliIndex));
//		
//		TextView ToplamListItemTextView = (TextView)row.findViewById(R.id.ToplamListItemTextView);
//		int ToplamIndex = cursor.getColumnIndex(VeriTabanıSiparisler.TOPLAM);
//		ToplamListItemTextView.setText(cursor.getString(ToplamIndex));
		
		
		return row;
	}
	
}
