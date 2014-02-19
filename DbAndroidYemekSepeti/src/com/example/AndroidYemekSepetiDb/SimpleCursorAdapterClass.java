package com.example.AndroidYemekSepetiDb;

import com.example.VeriTabani.VeriTabanýYiyecekler;

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
public class SimpleCursorAdapterClass extends SimpleCursorAdapter {

	private Activity context;
	private Cursor cursor;
	private int selectedPosition = -1; 
	
	// SimpleCursorAdapter sýnýfýndan extends edilerek Yapýlandýrýcýnýn ici dolduruldu
	// Yapýlandýrýcýda , Icerik Bilgisi , hangi Layout'un kullanýlacagý , VeriTabaný sorgulari icin 
	// Tabloyu doldururken hangi cursor kullanýlacagý , ve from-to ile hangi degerlerin Layout'da
	// hangi degerlere karsý geldigini soyluyoruz.
	public SimpleCursorAdapterClass(Context context, int layout, Cursor c,
			String[] from, int[] to, int flags) {
		
		super(context, layout, c, from, to, flags);
		
		// SimpleCUrsor Sýnýfý herhanagi bir Activity'de cagýrýldýgý zaman degisken o Activitiy'nin ismi ile dolacak.
		// SimpleCursor Sýnýfý çaðrýldýðý zaman , O sýnýftaki cursor nesnesini kullanacak.
		this.context = (Activity) context;
		this.cursor = c;

	}	
	// Adapter'da seçilmiþ olan pozisyonu dondurur.
	public int getSelectedPosition() {
		return selectedPosition;
	}
	// Adapter icerisine veri yerlestirirken kullanýlan yordam
	public void setSelectedPosition(int selectedPosition) {
		this.selectedPosition = selectedPosition;
		notifyDataSetChanged();
	}

	/*Simple Cursor Adapter sýnýfýndaki getView methodu OverRide edildi.
	 * Bu sekilde adapter icerisinde goruntu alýmý isleminde neyin baz alýnacagý bilgilerini veriyoruz.*/
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View row=convertView;
		
		if (row==null) {
			LayoutInflater inflater=context.getLayoutInflater();
			row=inflater.inflate(R.layout.listtextviews, null);
		}
		// Liste'de yemek secildi zaman kýrmýzý satir'in kirmizi olmasý saglandý
		if(selectedPosition == position){
        	row.setBackgroundColor(Color.RED);
        }else{
        	row.setBackgroundColor(Color.WHITE);
        }
		// cursor'un belirlenmis position'a gitmesi saglanýyor.
		cursor.moveToPosition(position);
		
		/*Layout icin listtextviews xml dosyasý kullanýlacagý bilgisini vermistk.
		 * O Layout'da cursor'da secilmis olan degerlerin aktarýmýný saglýyoruz.
		 * Ad degeri cursor'dan cekilerek Ad text'ine, Fiyat degeri cursor'dan cekilerek fiyat text'ine 
		 * ekleniyor */
		TextView adListItemTextView = (TextView)row.findViewById(R.id.AdListItemTextView);
		int AdIndex = cursor.getColumnIndex(VeriTabanýYiyecekler.URUN_ADI);
		adListItemTextView.setText(cursor.getString(AdIndex));
		
		TextView FiyatListItemTextView = (TextView)row.findViewById(R.id.FiyatListItemTextView);
		int FiyatIndex = cursor.getColumnIndex(VeriTabanýYiyecekler.URUN_FIYATI);
		FiyatListItemTextView.setText(cursor.getString(FiyatIndex));
		
		return row;
	}
	
}
