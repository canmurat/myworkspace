package com.example.AndroidYemekSepetiDb;

import com.example.VeriTabani.VeriTaban�Yiyecekler;

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
	
	// SimpleCursorAdapter s�n�f�ndan extends edilerek Yap�land�r�c�n�n ici dolduruldu
	// Yap�land�r�c�da , Icerik Bilgisi , hangi Layout'un kullan�lacag� , VeriTaban� sorgulari icin 
	// Tabloyu doldururken hangi cursor kullan�lacag� , ve from-to ile hangi degerlerin Layout'da
	// hangi degerlere kars� geldigini soyluyoruz.
	public SimpleCursorAdapterClass(Context context, int layout, Cursor c,
			String[] from, int[] to, int flags) {
		
		super(context, layout, c, from, to, flags);
		
		// SimpleCUrsor S�n�f� herhanagi bir Activity'de cag�r�ld�g� zaman degisken o Activitiy'nin ismi ile dolacak.
		// SimpleCursor S�n�f� �a�r�ld��� zaman , O s�n�ftaki cursor nesnesini kullanacak.
		this.context = (Activity) context;
		this.cursor = c;

	}	
	// Adapter'da se�ilmi� olan pozisyonu dondurur.
	public int getSelectedPosition() {
		return selectedPosition;
	}
	// Adapter icerisine veri yerlestirirken kullan�lan yordam
	public void setSelectedPosition(int selectedPosition) {
		this.selectedPosition = selectedPosition;
		notifyDataSetChanged();
	}

	/*Simple Cursor Adapter s�n�f�ndaki getView methodu OverRide edildi.
	 * Bu sekilde adapter icerisinde goruntu al�m� isleminde neyin baz al�nacag� bilgilerini veriyoruz.*/
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View row=convertView;
		
		if (row==null) {
			LayoutInflater inflater=context.getLayoutInflater();
			row=inflater.inflate(R.layout.listtextviews, null);
		}
		// Liste'de yemek secildi zaman k�rm�z� satir'in kirmizi olmas� sagland�
		if(selectedPosition == position){
        	row.setBackgroundColor(Color.RED);
        }else{
        	row.setBackgroundColor(Color.WHITE);
        }
		// cursor'un belirlenmis position'a gitmesi saglan�yor.
		cursor.moveToPosition(position);
		
		/*Layout icin listtextviews xml dosyas� kullan�lacag� bilgisini vermistk.
		 * O Layout'da cursor'da secilmis olan degerlerin aktar�m�n� sagl�yoruz.
		 * Ad degeri cursor'dan cekilerek Ad text'ine, Fiyat degeri cursor'dan cekilerek fiyat text'ine 
		 * ekleniyor */
		TextView adListItemTextView = (TextView)row.findViewById(R.id.AdListItemTextView);
		int AdIndex = cursor.getColumnIndex(VeriTaban�Yiyecekler.URUN_ADI);
		adListItemTextView.setText(cursor.getString(AdIndex));
		
		TextView FiyatListItemTextView = (TextView)row.findViewById(R.id.FiyatListItemTextView);
		int FiyatIndex = cursor.getColumnIndex(VeriTaban�Yiyecekler.URUN_FIYATI);
		FiyatListItemTextView.setText(cursor.getString(FiyatIndex));
		
		return row;
	}
	
}
